/**  
 * @FileName: CompanyServiceTest.java 
 * @Package com.bow.service 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service;

import org.junit.Test;

import com.bow.service.organization.CompanyService;

/** 
 * @ClassName: CompanyServiceTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年8月10日 下午11:30:15  
 */

public class CompanyServiceTest extends BaseTest {

    @Test
    public void testGetCompany() {
        CompanyService companyService = (CompanyService) context.getBean("companyService");
        companyService.getCompany(1l);
    }
}
