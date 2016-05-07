/**  
 * @FileName: ModelControllerTest.java 
 * @Package com.bow.controller.activiti 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.activiti;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.bow.controller.BaseTest;

/** 
 * @ClassName: ModelControllerTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年8月16日 下午3:50:45  
 */

public class ModelControllerTest extends BaseTest {

    @Test
    public void testModelList() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        ModelController c = (ModelController) context.getBean("modelController");
        System.out.println(c.modelList(request));
    }

}
