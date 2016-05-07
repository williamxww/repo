/**  
 * @FileName: UserDao.java 
 * @Package com.bow.dao.permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao.permission;

import java.util.List;

import com.bow.model.permission.User;

/**
 * @ClassName: UserDao
 * @Description: 操作hill_user表
 * @author ViVi
 * @date 2015年6月29日 下午10:32:31
 */

public interface UserDao {

    public User getUser(long id);

    public User getByUsername(String username);

    public int addUser(User user);

    public int deleteUser(User user);

    public int updateUser(User user);

    /*
     * 批量管理
     */
    public List<User> getUsers(User user);

    public int addBatchUsers(List<User> users);

    public int deleteBatchUsers(List<User> users);

}
