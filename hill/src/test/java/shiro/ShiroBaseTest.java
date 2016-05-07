/**  
 * @FileName: ShiroBaseTest.java 
 * @Package shiro 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: ShiroBaseTest
 * @Description: Shiro的初始化
 * @author ViVi
 * @date 2015年6月2日 下午10:11:22
 */

public class ShiroBaseTest {
    private static boolean initFlag = false;

    org.apache.shiro.mgt.SecurityManager securityManager;

    // 访问shiro的对象
    protected static Subject subject;

    // 执行计划
    protected static Scheduler scheduler;

    protected static ApplicationContext context;

    @Before
    public void setUp() {
        synchronized (ShiroBaseTest.class) {
            if (initFlag) {
                return;
            }

            initSpring();
            initShiro();
            // initScheduler();


            initFlag = true;
        }
        System.out.println("=======容器初始化完毕========");
    }

    private void initShiro() {
        // 这两句 放到配置文件中去了
        // Factory<org.apache.shiro.mgt.SecurityManager> factory = new
        // IniSecurityManagerFactory(
        // "classpath:shiro/shiro.ini");
        // securityManager = factory.getInstance();
        securityManager = (SecurityManager) context.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);
        subject = SecurityUtils.getSubject();
    }

    private void initSpring() {
        context = new ClassPathXmlApplicationContext(
                new String[] { "classpath:shiro/applicationContext.xml" });

        /*
         * 动态的用代码向容器注册bean ConfigurableApplicationContext
         * configurableApplicationContext = (ConfigurableApplicationContext)
         * context; DefaultListableBeanFactory defaultListableBeanFactory =
         * (DefaultListableBeanFactory) configurableApplicationContext
         * .getBeanFactory();
         * defaultListableBeanFactory.registerSingleton("securityManager",
         * securityManager);
         */
    }

    private void initScheduler() {
        try {
            scheduler = new MyQuartzSessionValidationScheduler().getScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /*
     * @After public void tearDown() { LifecycleUtils.destroy(scheduler); }
     */
}
