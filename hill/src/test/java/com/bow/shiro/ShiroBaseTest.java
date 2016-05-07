/**  
 * @FileName: ShiroBaseTest.java 
 * @Package shiro 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: ShiroBaseTest
 * @Description: Shiro的初始化
 * @author ViVi
 */

public class ShiroBaseTest {
    private static boolean initFlag = false;

    org.apache.shiro.mgt.SecurityManager securityManager;

    // 访问shiro的对象
    protected static Subject subject;

    protected static ApplicationContext context;

    @Before
    public void setUp() {
        synchronized (ShiroBaseTest.class) {
            if (initFlag) {
                return;
            }
            initSpring();
            initShiro();

            initFlag = true;
        }
        System.out.println("=======容器初始化完毕========");
    }

    private void initShiro() {
        securityManager = (SecurityManager) context.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);
        subject = SecurityUtils.getSubject();
    }

    private void initSpring() {
        context = new ClassPathXmlApplicationContext(new String[] { "conf/spring/applicationContext.xml",
                "conf/spring/applicationContext-mybatis.xml", "conf/spring/applicationContext-shiro.xml",
                "conf/spring/applicationContext-cache.xml" });
    }

    /**
     * 
     * @Description: 还要验证是否拥有角色(duty)
     * 
     *               登陆后将该用户所有信息包括权限，缓存着
     */
    @Test
    public void test() {
        System.out.println("----------------------------");
        UsernamePasswordToken token = new UsernamePasswordToken("E1", "1234");
        subject.login(token);
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.checkPermission("organization:create");
        Assert.assertTrue(subject.isPermitted("organization:create"));
    }

}
