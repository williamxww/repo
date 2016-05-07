/**  
 * @FileName: EmployeeService.java 
 * @Package com.bow.service.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization;

import java.util.List;

import com.bow.component.exception.ParameterException;
import com.bow.model.organization.Employee;

/** 
 * @ClassName: EmployeeService 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年6月12日 下午10:34:29  
 */

public interface EmployeeService {

    public boolean hasEmployee(Employee employee);

    /**
     * 
     * @Description: 查询满足条件的Employee
     * @param employee
     * @return
     */
    public List<Employee> getEmployees(Employee employee);


    /**
     * @Description: TODO(describe in one sentence)
     * @param employee
     * @return
     * @throws ParameterException
     */

    public int addEmployee(Employee employee) throws ParameterException;

    public int updateEmployee(Employee employee);

    /**
     * @Description: TODO(describe in one sentence)
     * @param employeeId
     * @return
     */
    int deleteEmployee(long employeeId);
}
