package environment;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
    public void test() {

        @SuppressWarnings("resource")
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                new String[] { "conf/spring/applicationContext*.xml" });
        ac.getBean("jdbcTemplate");
        // StandardEnvironment

	}

}
