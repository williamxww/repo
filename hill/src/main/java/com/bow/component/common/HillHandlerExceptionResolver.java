/**  
 * @FileName: HillHandlerExceptionResolver.java 
 * @Package com.bow.component.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @ClassName: HillHandlerExceptionResolver 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月27日 上午10:14:16  
 */

public class HillHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ModelAndView mav = new ModelAndView("jsonView");
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "error");
        result.put("message", "系统异常");
        mav.addObject(result);
        return mav;
    }

}
