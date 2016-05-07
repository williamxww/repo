/**  
 * @FileName: HilluserManagerFactory.java 
 * @Package com.bow.component.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.common;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 * @ClassName: HilluserManagerFactory 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月11日 上午11:33:13  
 */

public class HillUserEntityManagerFactory implements SessionFactory {

    @Autowired
    private UserEntityManager userEntityManager;

    /**
     * @param userEntityManager
     *            the userEntityManager to set
     */
    public void setUserEntityManager(UserEntityManager userEntityManager) {
        this.userEntityManager = userEntityManager;
    }

    @Override
    public Class<?> getSessionType() {
        return UserEntityManager.class;
    }

    @Override
    public Session openSession() {
        return userEntityManager;
    }

}
