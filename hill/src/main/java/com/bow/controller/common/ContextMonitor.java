/**  
 * @FileName: ContextMonitor.java 
 * @Package com.bow.controller.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bow.utils.common.SpringUtils;

/**
 * @ClassName: ContextMonitor
 * @Description: 容器监控器
 * @author ViVi
 * @date 2015年8月15日 上午11:54:14
 */
@Controller
@RequestMapping("/common")
public class ContextMonitor {

    @Autowired
    private SpringUtils springUtils;

    @RequestMapping("/configurableListableBeanFactory.do")
    public String configurableListableBeanFactory(HttpServletRequest request) {
        ConfigurableListableBeanFactory factory = springUtils.getConfigurableListableBeanFactory();
        String[] beanNames = factory.getBeanDefinitionNames();
        int beanCount = factory.getBeanDefinitionCount();
        request.setAttribute("beanNames", beanNames);
        request.setAttribute("beanCount", beanCount);
        return "common/beanList";
    }

    @RequestMapping("/toBeanList.do")
    public String beanList(HttpServletRequest request) {
        ConfigurableListableBeanFactory factory = springUtils.getConfigurableListableBeanFactory();
        String[] beanNames = factory.getBeanDefinitionNames();
        Map<String, BeanDefinition> map = new HashMap<String, BeanDefinition>();
        for (String beanName : beanNames) {
            BeanDefinition bd = factory.getBeanDefinition(beanName);
            map.put(beanName, bd);
        }
        request.setAttribute("beanDefinitions", map);
        return "common/beanList";
    }

    @RequestMapping("/toBeanDetail.do")
    public String getBeanDefinitionDetail(HttpServletRequest request) {
        String beanName = request.getParameter("beanName");
        ConfigurableListableBeanFactory factory = springUtils.getConfigurableListableBeanFactory();
        BeanDefinition bd = factory.getBeanDefinition(beanName);
        MutablePropertyValues mpvs = bd.getPropertyValues();
        PropertyValue[] pvs = mpvs.getPropertyValues();
        request.setAttribute("propertyValues", pvs);
        return "common/beanDetail";
    }
}
