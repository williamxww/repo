/**  
 * @FileName: DaoBaseTest.java 
 * @Package com.bow.dao 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package activiti;

import org.activiti.engine.ProcessEngine;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** 
 * @ClassName: DaoBaseTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年6月13日 上午9:59:45  
 */

public class BaseTest {

    private Logger logger = LoggerFactory.getLogger(BaseTest.class);

    private static boolean initFlag = false;

    protected static ApplicationContext context;

    protected static ProcessEngine processEngine;

    @Before
    public void setUp() {
        synchronized (BaseTest.class) {
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
                "conf/spring/applicationContext-activiti.xml" });
        processEngine = (ProcessEngine) context.getBean("processEngine");
    }

    @Test
    public void test() {
        System.out.println("--------");
    }

}
