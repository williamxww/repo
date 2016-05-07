/**  
 * @FileName: IbatisBaseDao.java 
 * @Package com.bow.dao.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao.common;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bow.model.common.Page;

/**
 * @ClassName: IbatisBaseDao
 * @Description: 通过此DAO，直接用SQL ID进行查询
 * 
 *               <pre>
 * 为防止命名冲突 最好带上命名空间
 * </pre>
 * @author ViVi
 * @date 2015年7月11日 上午10:39:32
 */

@Repository
public class IbatisBaseDao {

    @Autowired
    private SqlSession sqlSession;

    public List<Object> getByPage(String sqlId, Object parameter, Page page) {
        RowBounds bounds = new RowBounds((page.getCurrentPage() - 1) * page.getPageSize() + 1, page.getPageSize());
        return sqlSession.selectList(sqlId, parameter, bounds);
    }

    public Page calculatePage(String sqlId, Object parameter, Page page) {
        return null;
    }

}
