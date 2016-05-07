/**  
 * @FileName: DepartmentService.java 
 * @Package com.bow.service.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.organization;

import com.bow.component.exception.ParameterException;
import com.bow.model.organization.Department;

/** 
 * @ClassName: DepartmentService 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年6月12日 下午10:29:15  
 */

public interface DepartmentService {

    public boolean hasDepartment(Department department);

    public int addDepartment(Department parent, Department department) throws ParameterException;

    public int addDepartment(Department department) throws ParameterException;

    public int updateDepartment(Department department);

    int deleteDepartment(long departmentId);

}
