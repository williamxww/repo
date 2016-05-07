/**  
 * @FileName: JsonView.java 
 * @Package com.bow.component.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.common;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.bow.utils.common.JsonUtils;

/**
 * @ClassName: JsonView
 * @Description: 用于返回json格式的modelAndView
 * @author ViVi
 * @date 2015年9月27日 上午10:23:40
 */
public class JsonView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        writer.println(JsonUtils.toJson(model));
    }

}
