/**  
 * @FileName: HillGroupManager.java 
 * @Package com.bow.component.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bow.component.exception.DataException;
import com.bow.model.organization.Employee;
import com.bow.model.organization.OrgaNode;
import com.bow.service.organization.OrganizationService;

/**
 * @ClassName: HillGroupManager
 * @Description: 将组织机构与activiti绑定
 * @author ViVi
 * @date 2015年7月11日 上午9:21:40
 */

public class HillGroupEntityManager extends GroupEntityManager {

    private static final Logger logger = LoggerFactory.getLogger(HillGroupEntityManager.class);

    @Autowired
    private OrganizationService organizationService;

    @Override
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<Group> findGroupsByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {

        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findGroupCountByNativeQuery(Map<String, Object> parameterMap) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    /**
     * 根据EmployeeCode查找职务组
     */
    @Override
    public List<Group> findGroupsByUser(String userId) {
        List<Group> groups = new ArrayList<Group>();
        Employee employee = new Employee();
        employee.setCode(userId);
        List<Employee> employees = organizationService.getEmployees(employee);
        List<OrgaNode> duties = new ArrayList<OrgaNode>();
        for (Employee e : employees) {
            if (e.getPid() == null) {
                logger.error("{} 没有PID", e);
                throw new DataException(String.format("[%s] 没有PID", e));
            }
            duties.add(organizationService.getDuty(e.getPid()));
        }

        for (OrgaNode d : duties) {
            GroupEntity g = new GroupEntity();
            g.setId(d.getCode());
            g.setName(d.getName());
            g.setType(String.valueOf(d.getType()));
            groups.add(g);
        }
        return groups;
    }

}
