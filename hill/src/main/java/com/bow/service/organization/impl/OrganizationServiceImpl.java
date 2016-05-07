/**  
 * @FileName: OrganizationServiceImpl.java 
 * @Package com.bow.service.organization.impl 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bow.component.exception.ParameterException;
import com.bow.dao.organization.OrganizationDao;
import com.bow.model.organization.Company;
import com.bow.model.organization.Department;
import com.bow.model.organization.Duty;
import com.bow.model.organization.Employee;
import com.bow.model.organization.Organization;
import com.bow.service.organization.CompanyService;
import com.bow.service.organization.DepartmentService;
import com.bow.service.organization.DutyService;
import com.bow.service.organization.EmployeeService;
import com.bow.service.organization.OrganizationService;
import com.bow.utils.business.OrganizationUtils;

/**
 * @ClassName: OrganizationServiceImpl
 * @Description: 对组织机构的操作，尽量都使用这个类,不要去用employeeService dutyService etc;
 * @author ViVi
 * @date 2015年6月12日 下午10:21:24
 */
@Service("OrganizationService")
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DutyService dutyService;

    @Autowired
    private EmployeeService employeeService;

    /*----------------------------------------------------------
     *      COMPANY
     *----------------------------------------------------------*/
    @Override
    public Company getCompany(long companyId) {
        return companyService.getCompany(companyId);
    }

    @Override
    public int addCompany(Company company) throws ParameterException {
        return companyService.addCompany(company);
    }

    @Override
    public int updateCompany(Company company) {
        return companyService.updateCompany(company);
    }

    @Override
    public int deleteCompany(long companyId, boolean cascade) {

        if (cascade) {
            return organizationDao.deleteCascade(companyId);
        } else {
            return companyService.deleteCompany(companyId);
        }

    }


    @Override
    public int deleteCompany(long companyId) {
        return companyService.deleteCompany(companyId);
    }

    /*----------------------------------------------------------
     *      DEPARTMENT
     *----------------------------------------------------------*/

    @Override
    public boolean hasDepartment(Department department) {
        return departmentService.hasDepartment(department);
    }


    @Override
    public int addDepartment(Department parent, Department department) throws ParameterException {
        return departmentService.addDepartment(parent, department);
    }

    @Override
    public int updateDepartment(Department department) {
        return departmentService.updateDepartment(department);
    }

    @Override
    public int deleteDepartment(long departmentId) {
        return departmentService.deleteDepartment(departmentId);
    }

    @Override
    public int addDepartment(Department department) throws ParameterException {
        return departmentService.addDepartment(department);
    }

    @Override
    public int deleteDepartment(long departmentId, boolean cascade) {
        if (cascade) {
            return organizationDao.deleteCascade(departmentId);
        } else {
            return departmentService.deleteDepartment(departmentId);
        }
    }


    /*----------------------------------------------------------
     *      DUTY
     *----------------------------------------------------------*/

    @Override
    public boolean hasDuty(Duty duty) {
        return dutyService.hasDuty(duty);
    }

    @Override
    public Duty getDuty(long dutyId) {
        return dutyService.getDuty(dutyId);
    }

    @Override
    public int updateDuty(Duty duty) {
        return dutyService.updateDuty(duty);
    }


    @Override
    public int deleteDuty(long dutyId) {
        return dutyService.deleteDuty(dutyId);
    }


    @Override
    public int addDuty(Duty duty) throws ParameterException {
        return dutyService.addDuty(duty);
    }

    @Override
    public int deleteDuty(long dutyId, boolean cascade) {
        if (cascade) {
            return organizationDao.deleteCascade(dutyId);
        } else {
            return dutyService.deleteDuty(dutyId);
        }
    }

    /*----------------------------------------------------------
     *      EMPLOYEE
     *----------------------------------------------------------*/
    @Override
    public boolean hasEmployee(Employee employee) {
        return employeeService.hasEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees(Employee employee) {
        return employeeService.getEmployees(employee);
    }

    @Override
    public int deleteEmployee(long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @Override
    public int addEmployee(Employee employee) throws ParameterException {
        return employeeService.addEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }


    @Override
    public int deleteEmployee(long employeeId, boolean cascade) {

        if (cascade) {
            return organizationDao.deleteCascade(employeeId);
        } else {
            return employeeService.deleteEmployee(employeeId);
        }
    }

    /*----------------------------------------------------------
     *      联合查询
     *----------------------------------------------------------*/
    @Override
    public List<Department> getDepartments(long companyId) {
        return OrganizationUtils.toDepartments(organizationDao.getChildren(companyId));
    }


    @Override
    public List<Duty> getDutiesByDeparmentId(long departmentId) {
        return OrganizationUtils.toDuties(organizationDao.getChildren(departmentId));
    }

    @Override
    public Duty getDutiesByEmployeeId(long employeeId) {
        Organization node = organizationDao.getOrganization(employeeId);
        Organization parent = organizationDao.getOrganization(node.getPid());
        return OrganizationUtils.toDuty(parent);
    }

    @Override
    public List<Employee> getEmployees(long dutyId) {
        return OrganizationUtils.toEmployees(organizationDao.getChildren(dutyId));
    }





}
