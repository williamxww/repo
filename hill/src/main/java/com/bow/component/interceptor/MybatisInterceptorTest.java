/**  
 * @FileName: MybatisInterceptorTest.java 
 * @Package com.bow.component.interceptor 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.interceptor;

import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/** 
 * @ClassName: MybatisInterceptorTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年6月28日 下午1:46:23  
 */
@Intercepts({ @Signature(type = Map.class, method = "get", args = { Object.class }) })
public class MybatisInterceptorTest implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("hi  vv");
        return "vv";
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /*
     * (非 Javadoc)
     * 
     * 
     * @param properties
     * 
     * @see
     * org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    @Override
    public void setProperties(Properties properties) {
        // TODO Auto-generated method stub

    }

}
