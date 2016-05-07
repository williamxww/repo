/**  
 * @FileName: OrganizationUtils.java 
 * @Package com.bow.utils.business 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.utils.business;

import java.util.ArrayList;
import java.util.List;

import com.bow.model.organization.Company;
import com.bow.model.organization.Department;
import com.bow.model.organization.Duty;
import com.bow.model.organization.Employee;
import com.bow.model.organization.Organization;

/**
 * @ClassName: OrganizationUtils
 * @Description: 组织机构工具
 * @author ViVi
 * @date 2015年9月27日 上午11:47:11
 */

public class OrganizationUtils {

    public static Company toCompany(Organization organization) {
        if (organization == null) {
            return null;
        }
        Company company = new Company();
        company.setId(organization.getId());
        company.setPid(organization.getPid());
        company.setCode(organization.getCode());
        company.setName(organization.getName());
        company.setType(organization.getType());
        company.setStatus(organization.getStatus());
        company.setDescription(organization.getDescription());
        return company;
    }

    public static Department toDepartment(Organization organization) {
        if (organization == null) {
            return null;
        }
        Department department = new Department();
        department.setId(organization.getId());
        department.setPid(organization.getPid());
        department.setCode(organization.getCode());
        department.setName(organization.getName());
        department.setType(organization.getType());
        department.setStatus(organization.getStatus());
        department.setDescription(organization.getDescription());
        return department;
    }

    public static List<Department> toDepartments(List<Organization> organizations) {
        if (organizations == null || organizations.size() == 0) {
            return null;
        }

        List<Department> departments = new ArrayList<Department>();
        for (Organization organization : organizations) {
            departments.add(OrganizationUtils.toDepartment(organization));
        }
        return departments;
    }

    public static Duty toDuty(Organization organization) {
        if (organization == null) {
            return null;
        }
        Duty duty = new Duty();
        duty.setId(organization.getId());
        duty.setPid(organization.getPid());
        duty.setCode(organization.getCode());
        duty.setName(organization.getName());
        duty.setType(organization.getType());
        duty.setStatus(organization.getStatus());
        duty.setDescription(organization.getDescription());
        return duty;
    }

    public static List<Duty> toDuties(List<Organization> organizations) {
        if (organizations == null || organizations.size() == 0) {
            return null;
        }

        List<Duty> duties = new ArrayList<Duty>();
        for (Organization organization : organizations) {
            duties.add(OrganizationUtils.toDuty(organization));
        }
        return duties;
    }


    public static Employee toEmployee(Organization organization) {
        if (organization == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(organization.getId());
        employee.setPid(organization.getPid());
        employee.setCode(organization.getCode());
        employee.setName(organization.getName());
        employee.setType(organization.getType());
        employee.setStatus(organization.getStatus());
        employee.setDescription(organization.getDescription());
        employee.setDutyFlag(organization.getDutyFlag());
        employee.setManagerCode(organization.getManagerCode());
        return employee;
    }

    public static List<Employee> toEmployees(List<Organization> organizations) {
        if (organizations == null || organizations.size() == 0) {
            return null;
        }

        List<Employee> employees = new ArrayList<Employee>();
        for (Organization organization : organizations) {
            employees.add(OrganizationUtils.toEmployee(organization));
        }
        return employees;
    }

    public static Organization fromEmployee(Employee employee) {
        if (employee == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(employee.getId());
        organization.setPid(employee.getPid());
        organization.setCode(employee.getCode());
        organization.setName(employee.getName());
        organization.setType(employee.getType());
        organization.setStatus(employee.getStatus());
        organization.setDescription(employee.getDescription());
        organization.setDutyFlag(employee.getDutyFlag());
        organization.setManagerCode(employee.getManagerCode());
        return organization;
    }

    public static Organization fromDuty(Duty duty) {
        if (duty == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(duty.getId());
        organization.setPid(duty.getPid());
        organization.setCode(duty.getCode());
        organization.setName(duty.getName());
        organization.setType(duty.getType());
        organization.setStatus(duty.getStatus());
        organization.setDescription(duty.getDescription());
        return organization;
    }

    public static Organization fromDepartment(Department department) {
        if (department == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(department.getId());
        organization.setPid(department.getPid());
        organization.setCode(department.getCode());
        organization.setName(department.getName());
        organization.setType(department.getType());
        organization.setStatus(department.getStatus());
        organization.setDescription(department.getDescription());
        return organization;
    }

    public static Organization fromCompany(Company company) {
        if (company == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(company.getId());
        organization.setPid(company.getPid());
        organization.setCode(company.getCode());
        organization.setName(company.getName());
        organization.setType(company.getType());
        organization.setStatus(company.getStatus());
        organization.setDescription(company.getDescription());
        return organization;
    }
}
