/**  
 * @FileName: PermissionDao.java 
 * @Package com.bow.dao.permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bow.model.organization.Duty;
import com.bow.model.permission.Permission;
import com.bow.model.permission.User;

/**
 * @ClassName: PermissionDao
 * @Description: 处理 用户 权限 操作 资源之间的关系 .注意：这个类中没有更新一说
 * @author ViVi
 * @date 2015年6月29日 下午10:44:33
 */

public interface PermissionDao {

    /**
     * @Description: 给某个用户单独的增加特权
     * @param user
     */
    public int addPrevilege(@Param("user") User user, @Param("permission") Permission permission);

    public int addPermission(@Param("duty") Duty duty, @Param("permission") Permission permission);
    /**
     * 
     * @Description: 用户私有的权利叫特权
     * @param user
     * @return
     */
    public List<Permission> getPrevileges(User user);

    public List<Permission> getPermissions(Duty duty);

    /**
     * 
     * @Description: 是根据权限的ID进行删除 某条数据
     * @param permission
     */
    public int deletePermission(Permission permission);

}
