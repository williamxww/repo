/**  
 * @FileName: HillGroupManagerFactory.java 
 * @Package com.bow.component.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.common;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 * @ClassName: HillGroupManagerFactory 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月11日 上午11:28:18  
 */

public class HillGroupEntityManagerFactory implements SessionFactory {

    @Autowired
    private GroupEntityManager groupEntityManager;

    public void setGroupEntityManager(GroupEntityManager groupEntityManager) {
        this.groupEntityManager = groupEntityManager;
    }

    @Override
    public Class<?> getSessionType() {
        return GroupEntityManager.class;
    }

    @Override
    public Session openSession() {
        return groupEntityManager;
    }

}
