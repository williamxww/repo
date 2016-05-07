/**  
 * @FileName: PermissionTest.java 
 * @Package shiro 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package shiro;

import java.util.Date;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationJob;
import org.junit.Test;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;

/**
 * @ClassName: PermissionTest
 * @Description: 测试权限控制
 * @author ViVi
 * @date 2015年6月2日 下午10:29:20
 */

public class PermissionTest extends ShiroBaseTest {

    @Test
    public void testRole() {
        UsernamePasswordToken token = new UsernamePasswordToken("hill", "123");
        subject.login(token);
        if (subject.hasRole("admin")) {
            System.out.println("拥有权限");
        } else {
            System.out.println("无权限");
        }
        subject.logout();
    }

    @Test
    public void testAddUser() {
        UsernamePasswordToken token = new UsernamePasswordToken("hill", "123");
        subject.login(token);
        OperationPermission operation = (OperationPermission) context.getBean("operationPermission");
        operation.addUser();
        subject.logout();
    }

    // @Test
    public void testScheduleJob() {
        JobDetail job = new JobDetail("sessionJob", "Jobs", QuartzSessionValidationJob.class);
        try {
            scheduler.start();

            Trigger trigger = TriggerUtils.makeSecondlyTrigger(10);
            trigger.setStartTime(new Date());
            trigger.setName("trigger");
            scheduler.scheduleJob(job, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
