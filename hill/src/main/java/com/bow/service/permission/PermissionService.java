/**  
 * @FileName: PermissionService.java 
 * @Package com.bow.service.permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.permission;

import java.util.List;
import java.util.Set;

import com.bow.component.exception.ParameterException;
import com.bow.model.organization.Duty;
import com.bow.model.permission.Permission;
import com.bow.model.permission.User;

/** 
 * @ClassName: PermissionService 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月2日 下午8:51:24  
 */

public interface PermissionService {

    /**
     * 
     * @Description: 用户所有的权限包括用户特权+用户职务的权限
     * @param user
     * @return
     */
    public List<Permission> getPermissions(User user);

    /**
     * 
     * @Description: 获取该职务对应的权限
     * @param duty
     * @return
     */
    public List<Permission> getPermissions(Duty duty);

    /**
     * 
     * @Description: 给某用户添加特权
     * @param user
     * @param permission
     * @return
     * @throws ParameterException
     */
    public int addPrevilege(User user, Permission permission) throws ParameterException;

    public int addPermission(Duty duty, Permission permission) throws ParameterException;

    public int deletePermission(Permission permission);

    /**
     * @Description: 验证拥有的权限ownedPermissions中，是否包含权限requiredPermission
     * @param ownedPermissions
     * @param requiredPermission
     * @return
     */
    public boolean hasPermission(Set<String> ownedPermissions, String requiredPermission);
}
