/**  
 * @FileName: PermissionDaoTest.java 
 * @Package com.bow.dao 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bow.dao.permission.PermissionDao;
import com.bow.model.permission.Permission;
import com.bow.model.permission.User;

/** 
 * @ClassName: PermissionDaoTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月1日 下午11:07:28  
 */

public class PermissionDaoTest extends DaoBaseTest {

    private Logger logger = LoggerFactory.getLogger(PermissionDaoTest.class);

    @Test
    public void testAddCompany() {
        PermissionDao dao = (PermissionDao) context.getBean("permissionDao");
        User user = new User();
        user.setId(1L);

        Permission permission = new Permission();
        permission.setOperationId(1L);
        permission.setResourceId(1L);
        int a = dao.addPrevilege(user, permission);
        logger.debug(String.valueOf(a));
    }

}
