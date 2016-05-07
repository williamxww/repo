/**  
 * @FileName: MemSessionDao.java 
 * @Package com.bow.component.session 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: MemSessionDao
 * @Description: session具体信息存放在内存的hashMap中
 * @author ViVi
 * @date 2015年7月5日 下午10:41:37
 */

public class MemSessionDao extends AbstractSessionDAO {

    private static final Logger logger = LoggerFactory.getLogger(MemSessionDao.class);

    private Map<Serializable, Session> map = new HashMap<Serializable, Session>();

    @Override
    public void update(Session session) throws UnknownSessionException {
        logger.debug("update session:{}", session.getId());
        map.put(session.getId(), session);
    }

    @Override
    public void delete(Session session) {
        logger.debug("delete session:{}", session.getId());
        map.remove(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        logger.debug("getActiveSessions");
        return map.values();
    }

    @Override
    protected Serializable doCreate(Session session) {
        logger.debug("createsession");
        // 给session生成一个ID
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("read session:{}", sessionId);
        return map.get(sessionId);
    }

}
