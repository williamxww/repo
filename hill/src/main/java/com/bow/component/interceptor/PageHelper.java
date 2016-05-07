/**  
 * @FileName: PageHelper.java 
 * @Package com.bow.component.interceptor 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bow.model.common.Page;

/**
 * @ClassName: PageHelper
 * @Description: 分页帮助器，用法： 1. startPage(page); 2.调用查询DAO,返回的List可以强制转换为Page
 *
 *               <pre>
 * public void testPageHelper() {
 *     OrganizationDao dao = (OrganizationDao) context.getBean(&quot;organizationDao&quot;);
 *     Page&lt;Node&gt; page = new Page&lt;Node&gt;();
 *     page.setCurrentPage(1);
 *     page.setPageSize(10);
 *     PageHelper.startPage(page);
 *     List&lt;Node&gt; res = dao.getDescendant(1L, 4);
 *     System.out.println(&quot;size:&quot; + res.size() + &quot;total:&quot; + page.getTotalPage());
 * }
 * </pre>
 * @author ViVi
 * @date 2015年6月28日 上午9:02:55
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PageHelper implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(PageHelper.class);

    // 每个查询的线程都会保存一个Page对象
    public static final ThreadLocal<Page> localPage = new ThreadLocal<Page>();

    /**
     * 
     * @Description: 要想分页，第一步调用此方法.调用此方法后的第一个查询语句会被执行分页
     * @param page
     */
    public static void startPage(Page page) {
        localPage.set(page);
    }


    // 分页查询完后要将线程局部变量移除
    private void removeLocalPage() {
        localPage.remove();
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            return getResultByPage(invocation);
        } finally {
            // 移除该page
            removeLocalPage();
        }
    }

    /**
     * 选择要代理的类
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler || target instanceof ResultSetHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        // 在此设置从Config中取值

    }

    /**
     * 
     * @Description: 分页查询
     * @param invocation
     * @return
     * @throws Throwable
     */

    public Object getResultByPage(Invocation invocation) throws Throwable {
        // 当该线程设置了page,且拦截到了StatementHandler,就进行处理
        if ((localPage.get() != null) && (invocation.getTarget() instanceof StatementHandler)) {

            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
            // 可以分离出最原始的的目标类)
            while (metaStatementHandler.hasGetter("h")) {
                Object object = metaStatementHandler.getValue("h");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            // 分离最后一个代理对象的目标类
            while (metaStatementHandler.hasGetter("target")) {
                Object object = metaStatementHandler.getValue("target");
                metaStatementHandler = SystemMetaObject.forObject(object);
            }
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
                    .getValue("delegate.mappedStatement");


            Page page = localPage.get();

            // 重写分页sql
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            String sql = boundSql.getSql();
            String pageSql = buildPageSql(sql, page);
            metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);

            // 重设page内的总页数等
            Connection connection = (Connection) invocation.getArgs()[0];
            setPageTotal(sql, connection, mappedStatement, boundSql, page);
        }
        // 将执行权交给下一个拦截器
        return invocation.proceed();
    }

    /**
     * 
     * @Description:修改SQL
     * @param sql
     * @param page
     * @return
     */
    private String buildPageSql(String sql, Page page) {
        StringBuilder pageSql = new StringBuilder(200);
        pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
        pageSql.append(sql);
        // 小于currentPage*pageSize
        pageSql.append(" ) temp where rownum <= ").append(page.getCurrentPage() * page.getPageSize());
        // 大于(currentPage-1)*pageSize +1
        pageSql.append(") where row_id > ").append(((page.getCurrentPage()) - 1) * page.getPageSize());
        return pageSql.toString();
    }

    /**
     * 
     * @Description: 为了获取总记录数 和 总页数
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page
     */
    private void setPageTotal(String sql, Connection connection, MappedStatement mappedStatement,
            BoundSql boundSql, Page page) {
        // 记录总记录数
        String countSql = "select count(0) from (" + sql + ")";
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {

            // 计算总数的BoundSql
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            countStmt = connection.prepareStatement(countSql);

            // 将parameterHandler的设置信息给statement
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
                    boundSql.getParameterObject(), countBS);
            parameterHandler.setParameters(countStmt);
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            page.setTotal(totalCount);
            int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
            page.setTotalPage(totalPage);
        } catch (SQLException e) {
            logger.error("Ignore this exception", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
            try {
                countStmt.close();
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
        }
    }
}
