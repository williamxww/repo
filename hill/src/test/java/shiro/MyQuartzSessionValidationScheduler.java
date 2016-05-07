/**  
 * @FileName: MyQuartzSessionValidationScheduler.java 
 * @Package shiro 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package shiro;

import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/** 
 * @ClassName: MyQuartzSessionValidationScheduler 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年6月2日 下午11:22:47  
 */

public class MyQuartzSessionValidationScheduler extends QuartzSessionValidationScheduler {
    public Scheduler getScheduler() throws SchedulerException {
        return super.getScheduler();
    }
}
