/**  
 * @FileName: DepartmentServiceImpl.java 
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
import com.bow.model.organization.Department;
import com.bow.model.organization.Organization;
import com.bow.service.organization.DepartmentService;
import com.bow.utils.business.OrganizationUtils;

/**
 * @ClassName: DepartmentServiceImpl
 * @Description: departmentService的实现
 * @author ViVi
 * @date 2015年6月24日 下午10:34:22
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    private Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private OrganizationDao dao;

    @Override
    public boolean hasDepartment(Department department) {

        // 参数校验
        if (null == department || department.getId() == 0) {
            return false;
        }
        logger.debug("has department{}?", department.getId());
        Organization result = dao.getOrganization(department.getId());
        if (null == result) {
            return false;
        }
        return true;
    }



    @Override
    public int addDepartment(Department department) throws ParameterException {

        // 参数校验
        if (department == null) {
            return 0;
        }
        logger.debug("parameter:{}", department.getId());
        if (StringUtils.isEmpty(department.getName()) || StringUtils.isEmpty(department.getCode())) {
            logger.error("department's name:{},code:{} can not be empty", department.getName(), department.getCode());
            throw new ParameterException("department's name and code can not be empty");
        }
        return dao.addOrganization(OrganizationUtils.fromDepartment(department));
    }

    @Override
    public int addDepartment(Department parent, Department department) throws ParameterException {

        // 参数校验
        if (null == department) {
            return 0;
        }
        if (null == parent) {
            return addDepartment(department);
        }
        logger.debug("add sub-department: {} to parent-department:{}", department.getId(), parent.getId());
        // 当父公司的ID不存在，就要抛出错误
        if (parent.getId() == null || !hasDepartment(parent)) {
            logger.error("parent department should be specified");
            throw new ParameterException("parent department should be specified");
        }
        department.setPid(parent.getId());
        return addDepartment(department);
    }


    @Override
    public int deleteDepartment(long departmentId) {
        logger.debug("ready to delete department:{}", departmentId);
        return dao.deleteOrganization(departmentId);
    }

    @Override
    public int updateDepartment(Department department) {
        Organization organization = OrganizationUtils.fromDepartment(department);
        return dao.updateOrganization(organization);
    }

}
