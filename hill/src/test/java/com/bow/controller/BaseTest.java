/**  
 * @FileName: BaseTest.java 
 * @Package com.bow.dao 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import shiro.ShiroBaseTest;

/**
 * @ClassName: BaseTest
 * @Description: service测试
 * @author ViVi
 * @date 2015年6月13日 上午9:59:45
 */

public class BaseTest {

    private Logger logger = LoggerFactory.getLogger(BaseTest.class);

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
        context = new ClassPathXmlApplicationContext(new String[] { 
                "conf/spring/applicationContext.xml",
                "conf/spring/applicationContext-springmvc.xml",
                "conf/spring/applicationContext-mybatis.xml",
                "conf/spring/applicationContext-shiro.xml",
                "conf/spring/applicationContext-cache.xml", 
                "conf/spring/applicationContext-activiti.xml" });

    }

    @Test
    public void test() {
        System.out.println("--------");
    }

}
