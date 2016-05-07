/**  
 * @FileName: UserService.java 
 * @Package com.bow.service.permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.permission;

import java.util.List;

import com.bow.model.organization.Duty;
import com.bow.model.organization.Employee;
import com.bow.model.permission.User;

/** 
 * @ClassName: UserService 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月2日 下午8:52:33  
 */

public interface UserService {

    /**
     * 
     * @Description: user 这个对象在数据库存在吗
     * @param user
     * @return
     */
    public boolean existsUser(User user);

    public int addUser(User user);

    public int updateUser(User user);

    public int deleteUser(User user);

    public User getByUsername(String username);

    /*
     * 批量添加删除
     */
    public int addBatchUsers(List<User> users);

    public int deleteBatchUsers(List<User> users);

    /*
     * 用户的组织机构属性
     */

    /**
     * 
     * @Description: 根据user获取其对应的组织机构中的员工属性, 
     * 其有可能有几个员工属性，因为一个人可以身兼数职
     * @param user
     * @return
     */
    public List<Employee> getEmployees(User user);

    /**
     * 
     * @Description: 根据用户查询该人的职务
     * @param user
     * @return
     */
    public List<Duty> getDuties(User user);

}
