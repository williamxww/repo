/**  
 * @FileName: UserDaoTest.java 
 * @Package com.bow.dao 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao;

import org.junit.Test;

import com.bow.dao.permission.UserDao;

/** 
 * @ClassName: UserDaoTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月8日 下午8:58:58  
 */

public class UserDaoTest extends DaoBaseTest {

    @Test
    public void testGetByUsername() {
        UserDao userDao = (UserDao) context.getBean("userDao");
        userDao.getByUsername("1E");
    }
}
