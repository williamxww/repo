/**  
 * @FileName: CompanyService.java 
 * @Package com.bow.service.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization;

import com.bow.component.exception.ParameterException;
import com.bow.model.organization.Company;

/**
 * @ClassName: CompanyService
 * @Description: 与company有关的操作
 * @author ViVi
 * @date 2015年6月12日 下午10:05:44
 */

public interface CompanyService {

    public Company getCompany(long companyId);

    /**
     * 
     * @Description: 增加一个顶级公司
     * @return
     * @return boolean
     * @throws ParameterException
     */
    public int addCompany(Company company) throws ParameterException;

    public int updateCompany(Company company);

    public int deleteCompany(long companyId);

}
