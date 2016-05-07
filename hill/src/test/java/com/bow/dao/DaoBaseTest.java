/**  
 * @FileName: DaoBaseTest.java 
 * @Package com.bow.dao 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import shiro.ShiroBaseTest;

import com.bow.dao.organization.OrganizationDao;

/** 
 * @ClassName: DaoBaseTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年6月13日 上午9:59:45  
 */

public class DaoBaseTest {

    private static final Logger logger = LoggerFactory.getLogger(DaoBaseTest.class);

    private static boolean initFlag = false;

    protected static ApplicationContext context;

    @Before
    public void setUp() {
        synchronized (ShiroBaseTest.class) {
            if (initFlag) {
                return;
            }

            initSpring();
            initFlag = true;
        }
        logger.info("容器初始化完毕");
    }

    private void initSpring() {
        context = new ClassPathXmlApplicationContext(new String[] { "conf/spring/applicationContext.xml",
                "conf/spring/applicationContext-mybatis.xml" });
    }

    @Test
    public void test() {
        System.out.println("--------");
    }

    @Test
    public void test1() {
        OrganizationDao dao = context.getBean("organizationDao", OrganizationDao.class);
        System.out.println(dao.getTest());

    }

}
