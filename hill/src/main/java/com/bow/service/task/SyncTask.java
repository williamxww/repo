/**  
 * @FileName: SyncTask.java 
 * @Package com.bow.service.task 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/** 
 * @ClassName: SyncTask 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月11日 下午10:26:58  
 */

public class SyncTask extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(SyncTask.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("同步定时任务");
    }

}
