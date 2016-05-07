/**  
 * @FileName: OrganizationDaoTest.java 
 * @Package com.bow.dao 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bow.component.interceptor.MybatisInterceptorTest;
import com.bow.component.interceptor.PageHelper;
import com.bow.dao.organization.OrganizationDao;
import com.bow.model.common.Node;
import com.bow.model.common.Page;
import com.bow.model.organization.Company;
import com.bow.model.organization.Department;
import com.bow.model.organization.Duty;
import com.bow.model.organization.Employee;
import com.bow.model.organization.OrgaNode;
import com.bow.utils.business.OrganizationUtils;

/** 
 * @ClassName: OrganizationDaoTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年6月13日 上午9:57:35  
 */

public class OrganizationDaoTest extends DaoBaseTest {

    private Logger logger = LoggerFactory.getLogger(OrganizationDaoTest.class);


    @Test
    public void testAddCompany() {
        logger.debug("{} and {}", "a", "b");
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        // System.out.println(dao.getCompany(0));
        Company company = new Company();
        company.setName("bow");
        company.setCode("C1");

        company.setPid(0l);
        company.setStatus(1);
        dao.addOrganization(OrganizationUtils.fromCompany(company));
    }

    @Test
    public void testGetCompany() {
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        Company company = dao.getCompany(1L);
        System.out.println(company);
    }

    @Test
    public void testCompanyDao() {
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        Company company = new Company();
        company.setId(105l);
        company.setDescription("updateTest2");
        int a = dao.deleteCompany(14l);
        // System.out.println(a);
        // dao.updateCompany(company);
    }

    @Test
    public void testDepartmentDao() {
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        Department department = new Department();
        department.setPid(0l);
        department.setDescription("department");
        // dao.addDepartment(department);
        // Department d = dao.getDepartment(2l);
        // System.out.println(d);
        // department.setId(2l);
        // dao.deleteDepartment(department);
        department.setId(3l);
        department.setDescription("department update");
        dao.updateDepartment(department);
    }

    @Test
    public void testDutyDao() {
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        Duty duty = new Duty();
        duty.setPid(0l);
        duty.setDescription("duty");
        // dao.addDuty(duty);
        Duty d = dao.getDuty(4l);
        System.out.println(d);
        // duty.setId(4l);
        // dao.deleteDuty(duty);
    }

    @Test
    public void testEmployeeDao() {
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        Employee employee = new Employee();
        employee.setPid(0l);
        employee.setDescription("employee1");
        // dao.addEmployee(employee);
        // dao.getEmployee(4l);

        // employee.setId(4l);
        // dao.deleteEmployee(employee);
        Employee employee2 = new Employee();
        employee2.setPid(0l);
        employee2.setDescription("employee2");
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        employees.add(employee2);
        dao.addBatchEmployee(employees);
        // dao.updateEmployee(employee2);
    }

    @Test
    public void testQueryBatch() {
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        Employee employee = new Employee();
        List<Employee> list = dao.getEmployees(employee);
        System.out.println(list.size());
    }

    @Test
    public void testGetNodes() {
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        List<OrgaNode> res = dao.getChildren(1L);
        System.out.println(res.get(0).getId());
    }

    @Test
    public void testPageHelper() {
        OrganizationDao dao = (OrganizationDao) context.getBean("organizationDao");
        Page<OrgaNode> page = new Page<OrgaNode>(1, 10);
        PageHelper.startPage(page);
        List<OrgaNode> res = dao.getDescendant(1L, 4);
        page.setRows(res);
        System.out.println(page.getJsonString());
    }

    @Test
    public void testInterceptor() {
        MybatisInterceptorTest test = new MybatisInterceptorTest();
        Map map = new HashMap();
        map = (Map) test.plugin(map);
        System.out.println(map.get("a"));
    }

    /**
     * 
     * @Description: 内存分页
     */
    @Test
    public void testIBatis() {
        SqlSession sqlSession = (SqlSession) context.getBean("sqlSession");
        RowBounds bounds = new RowBounds(0, 5);
        List<Node> nodes = sqlSession.selectList("com.bow.dao.organization.OrganizationDao.getAllTest", 1L, bounds);
        for (Node node : nodes) {
            System.out.println(node);
        }

    }

}
