/**  
 * @FileName: EmployeeServiceImpl.java 
 * @Package com.bow.service.organization.impl 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bow.component.exception.ParameterException;
import com.bow.dao.organization.OrganizationDao;
import com.bow.model.organization.Employee;
import com.bow.model.organization.Organization;
import com.bow.service.organization.EmployeeService;
import com.bow.utils.business.OrganizationUtils;

/**
 * @ClassName: EmployeeServiceImpl
 * @Description: employeeService的实现
 * @author ViVi
 * @date 2015年6月24日 下午10:34:22
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private OrganizationDao dao;

    @Override
    public boolean hasEmployee(Employee employee) {

        // 参数校验
        if (null == employee || employee.getId() == 0) {
            return false;
        }
        logger.debug("has employee{}?", employee.getId());
        Organization result = dao.getOrganization(employee.getId());
        if (null == result) {
            return false;
        }
        return true;
    }



    @Override
    public int addEmployee(Employee employee) throws ParameterException {

        // 参数校验
        if (employee == null) {
            return 0;
        }
        logger.debug("parameter:{}", employee.getId());
        if (StringUtils.isEmpty(employee.getName()) || StringUtils.isEmpty(employee.getCode())) {
            logger.error("employee's name:{},code:{} can not be empty", employee.getName(), employee.getCode());
            throw new ParameterException("employee's name and code can not be empty");
        }
        return dao.addOrganization(OrganizationUtils.fromEmployee(employee));
    }


    @Override
    public int deleteEmployee(long employeeId) {
        logger.debug("ready to delete employee:{}", employeeId);
        return dao.deleteOrganization(employeeId);
    }

    @Override
    public List<Employee> getEmployees(Employee employee) {
        if (employee == null) {
            return null;
        }
        List<Organization> Organizations = dao.getOrganizations(OrganizationUtils.fromEmployee(employee));
        if (Organizations == null || Organizations.size() == 0) {
            return null;
        }

        List<Employee> employees = new ArrayList<Employee>();
        for (Organization organization : Organizations) {
            employees.add(OrganizationUtils.toEmployee(organization));
        }
        return employees;
    }

    @Override
    public int updateEmployee(Employee employee) {
        Organization organization = OrganizationUtils.fromEmployee(employee);
        return dao.updateOrganization(organization);
    }

}
