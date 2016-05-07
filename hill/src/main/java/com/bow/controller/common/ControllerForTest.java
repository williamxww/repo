/**  
 * @FileName: ControllerForTest.java 
 * @Package com.bow.controller.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bow.utils.common.SpringUtils;

/**
 * @ClassName: ControllerForTest
 * @Description: 这个controller主要是为了验证一些新功能而用
 * @author ViVi
 * @date 2015年8月17日 下午10:09:49
 */
@Controller
public class ControllerForTest {
    @Autowired
    private SpringUtils springUtils;

    @RequestMapping("/testJstlFn.do")
    public String testJstlFn(HttpServletRequest request) {
        ApplicationContext factory = springUtils.getApplicationContext();
        String[] beanNames = factory.getBeanDefinitionNames();
        int beanCount = factory.getBeanDefinitionCount();
        request.setAttribute("beanNames", beanNames);
        request.setAttribute("beanCount", beanCount);

        Date date = new Date();
        request.setAttribute("date", date);
        return "common/testJstlFn";
    }




}
