package com.bow.component.session;

import java.io.Serializable;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bow.utils.SerializableUtils;


public class MySessionDao extends CachingSessionDAO {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        String sql = "insert into shiro_sessions(id, session_str) values(?,?)";
        jdbcTemplate.update(sql, sessionId, SerializableUtils.serialize(session));
        return session.getId();
    }
    @Override
    protected void doUpdate(Session session) {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
        String sql = "update shiro_sessions set session_str=? where id=?";
        jdbcTemplate.update(sql, SerializableUtils.serialize(session), session.getId());
    }
    @Override
    protected void doDelete(Session session) {
        String sql = "delete from shiro_sessions where id=?";
        jdbcTemplate.update(sql, session.getId());
    }
    @Override
    protected Session doReadSession(Serializable sessionId) {
        String sql = "select session_str from shiro_sessions where id=?";
        List<String> sessionStrList = jdbcTemplate.queryForList(sql, String.class, sessionId);
        if(sessionStrList.size() == 0) return null;
        return SerializableUtils.deserialize(sessionStrList.get(0));
    }
}
