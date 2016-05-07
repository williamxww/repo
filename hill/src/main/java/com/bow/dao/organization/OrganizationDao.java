/**  
 * @FileName: OrganizationDao.java 
 * @Package com.bow.dao.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao.organization;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bow.model.organization.Organization;

/**
 * @ClassName: OrganizationDao
 * @Description: 对组织机构表的所有操作
 * 由于NULL和0还是有很大的区别，因而在DAO层，参数统一用包装类型
 * @author ViVi
 * @date 2015年6月12日 下午10:46:01
 */

public interface OrganizationDao {

    /*----------------------------------------------------------
     *      COMPANY
     *----------------------------------------------------------*/
    // public Company getCompany(Long companyId);
    //
    // public int addCompany(Company company);
    //
    // public int updateCompany(Company company);
    //
    // public int deleteCompany(Long companyId);
    //
    // /*----------------------------------------------------------
    // * DEPARTMENT
    // *----------------------------------------------------------*/
    // public Department getDepartment(Long departmentId);
    //
    // public int addDepartment(Department department);
    //
    // public int updateDepartment(Department department);
    //
    // public int deleteDepartment(Long departmentId);
    //
    // /*----------------------------------------------------------
    // * DUTY
    // *----------------------------------------------------------*/
    // public Duty getDuty(Long dutyId);
    //
    // public int addDuty(Duty duty);
    //
    // public int updateDuty(Duty duty);
    //
    // public int deleteDuty(Long dutyId);
    //
    // /*----------------------------------------------------------
    // * EMPLOYEE
    // *----------------------------------------------------------*/
    // public Employee getEmployee(Long employeeId);
    //
    // public Employee getEmployeeByCode(String code);
    //
    // public int addEmployee(Employee employee);
    //
    // public int updateEmployee(Employee employee);
    //
    // public int deleteEmployee(Long employeeId);
    //
    // public int addBatchEmployee(List<Employee> employees);
    //
    // public List<Employee> getEmployees(Employee employee);
    //
    //
    // /*----------------------------------------------------------
    // * 联合查询
    // *----------------------------------------------------------*/
    // public List<Department> getDepartments(Long companyId);
    //
    // public List<Duty> getDuties(Long departmentId);
    //
    // public List<Employee> getEmployees(Long dutyId);
    //
    //
    // /*----------------------------------------------------------
    // * 业务需要
    // *----------------------------------------------------------*/
    //
    // /**
    // *
    // * @Description: 获取节点
    // * @param id
    // * @return
    // */
    // public OrgaNode getOrgaNode(Long id);
    // /**
    // *
    // * @Description: 获取到上级节点
    // * @param id
    // * @return
    // */
    // public OrgaNode getParent(Long id);
    //
    //
    // /**
    // * @Description: 添加节点
    // * @param node
    // * @return
    // */
    // public int addOrgaNode(OrgaNode node);
    //
    // public int deleteOrgaNode(Long id);
    
    /**************************** 组织机构改进API ******************************/

    public Organization getOrganization(Long id);

    
    /**
     * 
     * @Description: 条件查询
     * @param organization
     * @return
     */
    public List<Organization> getOrganizations(Organization organization);

    public List<Organization> getChildren(@Param("id") Long id);

    /**
     * @Description: 查询子节点直到某个层级 如toLevel-->3即查到职务层级，此时不可能查到人员
     * @param id
     * @param toLevel
     * @return 子节点
     */
    public List<Organization> getDescendant(@Param("id") Long id, @Param("toLevel") Integer toLevel);

    public int addOrganization(Organization organization);

    public int updateOrganization(Organization organization);

    public int deleteOrganization(Long id);

    /**
     * 
     * @Description: 从某个节点id开始级联删除所有项
     * @param id
     * @return
     */
    public int deleteCascade(Long id);

    public int getTest();
}
