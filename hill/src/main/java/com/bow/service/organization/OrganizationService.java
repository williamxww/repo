/**  
 * @FileName: OrganizationService.java 
 * @Package com.bow.service.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization;

import java.util.List;

import com.bow.model.organization.Department;
import com.bow.model.organization.Duty;
import com.bow.model.organization.Employee;


/**
 * @ClassName: OrganizationService
 * @Description: 供外界使用；对 Organization 的所有操作都在此定义
 * @author ViVi
 * @date 2015年6月10日 下午11:55:28
 */

public interface OrganizationService extends CompanyService, DepartmentService, DutyService, EmployeeService {

    int deleteCompany(long companyId, boolean cascade);

    int deleteDepartment(long departmentId, boolean cascade);

    int deleteDuty(long dutyId, boolean cascade);

    /**
     * 
     * @Description: 以后扩展删除组织机构里的人对Person基础信息也要有影响
     * @param employee
     * @param cascade
     * @return
     */
    int deleteEmployee(long employeeId, boolean cascade);

    /*------------------------------------------------------------------
     * 查询
     *------------------------------------------------------------------*/
    public List<Department> getDepartments(long companyId);

    public List<Duty> getDutiesByDeparmentId(long departmentId);

    public List<Employee> getEmployees(long dutyId);

    public Duty getDutiesByEmployeeId(long employeeId);








}
