/**  
 * @FileName: PermissionServiceImpl.java 
 * @Package conf.mybatis.permission.impl 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.permission.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bow.component.exception.ParameterException;
import com.bow.dao.permission.PermissionDao;
import com.bow.model.organization.Duty;
import com.bow.model.permission.Permission;
import com.bow.model.permission.User;
import com.bow.service.organization.DutyService;
import com.bow.service.permission.PermissionService;
import com.bow.service.permission.UserService;

/**
 * @ClassName: PermissionServiceImpl
 * @Description: 与权限有关的服务
 * @author ViVi
 * @date 2015年7月2日 下午9:15:24
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionDao dao;

    @Autowired
    private UserService userService;

    @Autowired
    private DutyService dutyService;

    @Override
    public List<Permission> getPermissions(User user) {
        List<Permission> results = new ArrayList<Permission>();
        if (user == null) {
            return results;
        }
        results.addAll(dao.getPrevileges(user));
        List<Duty> duties = userService.getDuties(user);
        for (Duty duty : duties) {
            results.addAll(dao.getPermissions(duty));
        }
        logger.debug("userId:{},permission:{}", user.getId(), results);
        return results;
    }


    @Override
    public List<Permission> getPermissions(Duty duty) {
        List<Permission> results = dao.getPermissions(duty);
        logger.debug("duty:{},permissions:{}", duty, results);
        return results;
    }


    @Override
    public int addPrevilege(User user, Permission permission) throws ParameterException {
        if (userService.existsUser(user)) {
            throw new ParameterException("user should be exists");
        }
        logger.debug("add permission:{} for {}", permission, user.getUsername());
        return dao.addPrevilege(user, permission);
    }


    @Override
    public int addPermission(Duty duty, Permission permission) throws ParameterException {
        if (dutyService.hasDuty(duty)) {
            throw new ParameterException("duty should be exists");
        }
        logger.debug("add duty:{} for {}", permission, duty.getName());
        return dao.addPermission(duty, permission);
    }


    @Override
    public int deletePermission(Permission permission) {
        logger.debug("delete permission {}", permission);
        return dao.deletePermission(permission);
    }

    @Override
    public boolean hasPermission(Set<String> ownedPermissions, String requiredPermission) {
        for (String permission : ownedPermissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(requiredPermission);
            // 如果p1包含p2则返回true
            if (p1.implies(p2)) {
                return true;
            }
        }
        return false;
    }

}
