/**  
 * @FileName: TaskUtils.java 
 * @Package com.bow.service.task.component 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.task.component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bow.utils.common.SpringUtils;

/** 
 * @ClassName: TaskUtils 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月15日 下午9:42:24  
 */

public class TaskUtils {
    private static final Logger logger = LoggerFactory.getLogger(TaskUtils.class);

    /**
     * 通过反射调用scheduleJob中定义的方法
     * 
     * @param scheduleJob
     */
    public static void invokMethod(ScheduleJob scheduleJob) {
        Object object = null;
        Class clazz = null;
        // springId不为空先按springId查找bean
        if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
            object = SpringUtils.getApplicationContext().getBean(scheduleJob.getSpringId());
        } else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
            try {
                clazz = Class.forName(scheduleJob.getBeanClass());
                object = clazz.newInstance();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        if (object == null) {
            logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
            return;
        }
        clazz = object.getClass();
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
        } catch (NoSuchMethodException e) {
            logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (method != null) {
            try {
                method.invoke(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
}
