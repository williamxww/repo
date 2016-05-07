/**  
 * @FileName: CompanyServiceImpl.java 
 * @Package com.bow.service.organization.impl 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bow.component.exception.ParameterException;
import com.bow.dao.organization.OrganizationDao;
import com.bow.model.organization.Company;
import com.bow.model.organization.Organization;
import com.bow.service.organization.CompanyService;
import com.bow.utils.business.OrganizationUtils;

/**
 * @ClassName: CompanyServiceImpl
 * @Description: companyService的实现
 * @author ViVi
 * @date 2015年6月24日 下午10:34:22
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    private Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private OrganizationDao dao;




    @Override
    public int addCompany(Company company) throws ParameterException {

        // 参数校验
        if (company == null) {
            return 0;
        }
        logger.debug("parameter:{}", company.getId());
        if (StringUtils.isEmpty(company.getName()) || StringUtils.isEmpty(company.getCode())) {
            logger.error("company's name:{},code:{} can not be empty", company.getName(), company.getCode());
            throw new ParameterException("company's name and code can not be empty");
        }
        return dao.addOrganization(OrganizationUtils.fromCompany(company));
    }



    @Override
    public int deleteCompany(long companyId) {
        logger.debug("ready to delete company:{}", companyId);
        return dao.deleteOrganization(companyId);
    }

    @Override
    public Company getCompany(long companyId) {
        Organization organization = dao.getOrganization(companyId);
        return OrganizationUtils.toCompany(organization);
    }

    @Override
    public int updateCompany(Company company) {
        Organization organization = OrganizationUtils.fromCompany(company);
        return dao.updateOrganization(organization);
    }

}
