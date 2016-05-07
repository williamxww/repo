/**  
 * @FileName: HillUserManager.java 
 * @Package com.bow.component.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bow.component.exception.DataException;
import com.bow.model.organization.Employee;
import com.bow.model.organization.OrgaNode;
import com.bow.service.organization.OrganizationService;

/** 
 * @ClassName: HillUserManager 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月11日 上午11:26:33  
 */

public class HillUserEntityManager extends UserEntityManager {

    private static final Logger logger = LoggerFactory.getLogger(HillGroupEntityManager.class);

    @Autowired
    private OrganizationService organizationService;

    @Override
    public User findUserById(String userId) {
        UserEntity userEntity = null;

        Employee employee = new Employee();
        employee.setCode(userId);
        List<Employee> employees = organizationService.getEmployees(employee);

        if (employees != null && employees.size() > 0) {
            userEntity = new UserEntity();
            userEntity.setId(employees.get(0).getCode());
        }
        return null;
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        List<Group> groups = new ArrayList<Group>();
        Employee employee = new Employee();
        employee.setCode(userId);

        List<Employee> employees = organizationService.getEmployees(employee);
        List<OrgaNode> duties = new ArrayList<OrgaNode>();
        for (Employee e : employees) {
            if (e.getPid() == null) {
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

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<User> findPotentialStarterUsers(String proceDefId) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public List<User> findUsersByNativeQuery(Map<String, Object> parameterMap, int firstResult, int maxResults) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

    @Override
    public long findUserCountByNativeQuery(Map<String, Object> parameterMap) {
        logger.error("activiti 用户模块改造异常，此方法没有实现");
        throw new RuntimeException("not implement method.");
    }

}
