/**  
 * @FileName: UserServiceImpl.java 
 * @Package com.bow.service.permission.impl 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.permission.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bow.dao.permission.UserDao;
import com.bow.model.organization.Duty;
import com.bow.model.organization.Employee;
import com.bow.model.permission.User;
import com.bow.service.organization.OrganizationService;
import com.bow.service.permission.UserService;

/**
 * @ClassName: UserServiceImpl
 * @Description: UserService实现类
 * @author ViVi
 * @date 2015年7月5日 下午8:26:03
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrganizationService organizationService;

    @Override
    public boolean existsUser(User user) {
        if (user == null || user.getId() == null) {
            return false;
        }
        User res = userDao.getUser(user.getId());
        if (userDao.getUser(user.getId()) == null) {
            return false;
        }
        return true;
    }


    @Override
    public int addUser(User user) {
        if (user == null) {
            return 0;
        }
        return userDao.addUser(user);
    }


    @Override
    public int updateUser(User user) {
        if (user == null) {
            return 0;
        }
        return userDao.updateUser(user);
    }


    @Override
    public int deleteUser(User user) {
        if (user == null) {
            return 0;
        }
        return userDao.deleteUser(user);
    }

    @Override
    public User getByUsername(String username) {
        if (username == null) {
            return null;
        }
        return userDao.getByUsername(username);
    }

    @Override
    public int addBatchUsers(List<User> users) {
        if (users == null || users.size() == 0) {
            return 0;
        }
        return userDao.addBatchUsers(users);
    }

    @Override
    public int deleteBatchUsers(List<User> users) {
        if (users == null || users.size() == 0) {
            return 0;
        }
        return userDao.deleteBatchUsers(users);
    }

    @Override
    public List<Employee> getEmployees(User user) {
        if (user == null || user.getUsername() == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setCode(user.getUsername());
        return organizationService.getEmployees(employee);
    }


    @Override
    public List<Duty> getDuties(User user) {
        List<Employee> employees = this.getEmployees(user);

        // 当该用户没用查到其对应的组织机构中的员工信息，就不会有职务信息
        if (CollectionUtils.isEmpty(employees)) {
            return null;
        }

        // 此人身兼数职会导致有几个员工身份
        List<Duty> duties = new ArrayList<Duty>();
        for (Employee e : employees) {
            Duty d = organizationService.getDutiesByEmployeeId(e.getId());
            if (d != null) {
                duties.add(d);
            }
        }
        return duties;
    }


}
