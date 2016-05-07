/**  
 * @FileName: DispatcherController.java 
 * @Package com.bow.controller.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: DispatcherController 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月13日 下午11:15:44  
 */
@Controller
public class DispatcherController {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherController.class);

    /**
     * 
     * @Description: 方便测试 直接就能跳转到某个页面
     * @param request
     * @return
     */
    @RequestMapping("/to/*")
    public String dispatch(HttpServletRequest request) {
        String uri = request.getRequestURI();
        logger.info("请求地址{}", uri);
        String toUri = uri.substring(uri.indexOf("to/"));
        if (toUri.endsWith(".do")) {
            toUri = toUri.substring(0, toUri.lastIndexOf(".do"));
        }
        logger.debug("跳转地址{}", toUri);
        return toUri;
    }
}
