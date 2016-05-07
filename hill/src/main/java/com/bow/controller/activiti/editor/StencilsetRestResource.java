/**  
 * @FileName: StencilsetRestResource.java 
 * @Package com.bow.controller.activiti 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.activiti.editor;

import java.io.InputStream;

import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: StencilsetRestResource
 * @Description: activiti-modeler-5.17.0.jar jar包中不能接受请求莫名其妙
 * @author ViVi
 * @date 2015年8月11日 下午11:01:08
 */

@RestController
public class StencilsetRestResource {

    @RequestMapping(value = "/editor/stencilset", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String getStencilset() {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        try {
            return IOUtils.toString(stencilsetStream);
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
    }
    }
}
