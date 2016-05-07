/**  
 * @FileName: ScheduleJob.java 
 * @Package com.bow.model.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.task.component;

import java.util.Date;

/** 
 * @ClassName: ScheduleJob 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月15日 下午9:12:06  
 */

public class ScheduleJob {

    public static final String STATUS_RUNNING = "1";

    public static final String STATUS_NOT_RUNNING = "0";

    public static final String CONCURRENT_IS = "1";

    public static final String CONCURRENT_NOT = "0";

    private Long jobId;

    private Date createTime;

    private Date updateTime;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 任务状态 是否启动任务
     */
    private String jobStatus;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 描述
     */
    private String description;

    /**
     * 任务执行时调用哪个类的方法 包名+类名
     */
    private String beanClass;

    /**
     * 任务是否有状态
     */
    private String isConcurrent;

    /**
     * spring bean
     */
    private String springId;

    /**
     * 任务调用的方法名
     */
    private String methodName;

    /**
     * @return the jobId
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * @param jobId
     *            the jobId to set
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName
     *            the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return the jobGroup
     */
    public String getJobGroup() {
        return jobGroup;
    }

    /**
     * @param jobGroup
     *            the jobGroup to set
     */
    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    /**
     * @return the jobStatus
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * @param jobStatus
     *            the jobStatus to set
     */
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    /**
     * @return the cronExpression
     */
    public String getCronExpression() {
        return cronExpression;
    }

    /**
     * @param cronExpression
     *            the cronExpression to set
     */
    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the beanClass
     */
    public String getBeanClass() {
        return beanClass;
    }

    /**
     * @param beanClass
     *            the beanClass to set
     */
    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    /**
     * @return the isConcurrent
     */
    public String getIsConcurrent() {
        return isConcurrent;
    }

    /**
     * @param isConcurrent
     *            the isConcurrent to set
     */
    public void setIsConcurrent(String isConcurrent) {
        this.isConcurrent = isConcurrent;
    }

    /**
     * @return the springId
     */
    public String getSpringId() {
        return springId;
    }

    /**
     * @param springId
     *            the springId to set
     */
    public void setSpringId(String springId) {
        this.springId = springId;
    }

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName
     *            the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
