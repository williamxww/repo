/**  
 * @FileName: QuartzJobFactoryDisallowConcurrentExecution.java 
 * @Package com.bow.service.task.component 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.task.component;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** 
 * @ClassName: QuartzJobFactoryDisallowConcurrentExecution 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月15日 下午9:14:49  
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobFactoryDisallowConcurrentExecution.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        TaskUtils.invokMethod(scheduleJob);

    }

}
