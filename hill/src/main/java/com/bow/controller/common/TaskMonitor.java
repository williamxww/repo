/**  
 * @FileName: TaskMonitor.java 
 * @Package com.bow.controller.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bow.utils.common.SpringUtils;

/**
 * @ClassName: TaskMonitor
 * @Description: 监控任务状态，启动或关闭所选任务
 * @author ViVi
 * @date 2015年9月11日 下午10:43:01
 */

@Controller
public class TaskMonitor {

    @Autowired
    private SpringUtils springUtils;

    @RequestMapping("toTaskList")
    public String toTaskList() {
        return "common/toTaskList";
    }

    @RequestMapping("toggleTask")
    public String toggleTask(HttpServletRequest request) {
        SchedulerFactoryBean schedule = springUtils.getApplicationContext().getBean("SchedulerFactoryBean",
                SchedulerFactoryBean.class);
        return "common/toTaskList";
    }
}
