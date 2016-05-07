/**  
 * @FileName: ResourceDaoTest.java 
 * @Package com.bow.dao 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bow.dao.permission.ResourceDao;
import com.bow.model.permission.Resource;

/** 
 * @ClassName: ResourceDaoTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月1日 下午11:58:49  
 */

public class ResourceDaoTest extends DaoBaseTest {
    private Logger logger = LoggerFactory.getLogger(ResourceDaoTest.class);

    @Test
    public void testGetResource() {
        ResourceDao dao = (ResourceDao) context.getBean("resourceDao");
        dao.getResource(1L);
    }

    @Test
    public void testAddResource() {
        ResourceDao dao = (ResourceDao) context.getBean("resourceDao");
        Resource resource = new Resource();
        resource.setName("rname");
        dao.addResource(null);
    }

    @Test
    public void testDeleteResource() {
        ResourceDao dao = (ResourceDao) context.getBean("resourceDao");
        dao.deleteResource(null);
    }

}
