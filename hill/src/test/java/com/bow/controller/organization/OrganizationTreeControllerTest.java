/**  
 * @FileName: OrganizationTreeControllerTest.java 
 * @Package com.bow.controller.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.organization;

import org.junit.Test;

import com.bow.controller.BaseTest;
import com.fasterxml.jackson.core.JsonProcessingException;

/** 
 * @ClassName: OrganizationTreeControllerTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月13日 下午8:12:57  
 */

public class OrganizationTreeControllerTest extends BaseTest {

    @Test
    public void testGetOrganizationTree() throws JsonProcessingException {
        OrganizationTreeController c = context.getBean("organizationTreeController", OrganizationTreeController.class);
    }
}
