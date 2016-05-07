/**  
 * @FileName: DutyServiceImpl.java 
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
import com.bow.model.organization.Duty;
import com.bow.model.organization.Organization;
import com.bow.service.organization.DutyService;
import com.bow.utils.business.OrganizationUtils;

/**
 * @ClassName: DutyServiceImpl
 * @Description: dutyService的实现
 * @author ViVi
 * @date 2015年6月24日 下午10:34:22
 */
@Service("dutyService")
public class DutyServiceImpl implements DutyService {

    private Logger logger = LoggerFactory.getLogger(DutyServiceImpl.class);

    @Autowired
    private OrganizationDao dao;

    @Override
    public boolean hasDuty(Duty duty) {

        // 参数校验
        if (null == duty || duty.getId() == 0) {
            return false;
        }
        logger.debug("has duty{}?", duty.getId());
        Organization result = dao.getOrganization(duty.getId());
        if (null == result) {
            return false;
        }
        return true;
    }


    @Override
    public int addDuty(Duty duty) throws ParameterException {

        // 参数校验
        if (duty == null) {
            return 0;
        }
        logger.debug("parameter:{}", duty.getId());
        if (StringUtils.isEmpty(duty.getName()) || StringUtils.isEmpty(duty.getCode())) {
            logger.error("duty's name:{},code:{} can not be empty", duty.getName(), duty.getCode());
            throw new ParameterException("duty's name and code can not be empty");
        }
        return dao.addOrganization(OrganizationUtils.fromDuty(duty));
    }

    


    @Override
    public int deleteDuty(long dutyId) {
        logger.debug("ready to delete duty:{}", dutyId);
        return dao.deleteOrganization(dutyId);
    }

    @Override
    public Duty getDuty(long dutyId) {

        return OrganizationUtils.toDuty(dao.getOrganization(dutyId));
    }

    @Override
    public int updateDuty(Duty duty) {
        Organization organization = OrganizationUtils.fromDuty(duty);
        return dao.updateOrganization(organization);
    }

}
