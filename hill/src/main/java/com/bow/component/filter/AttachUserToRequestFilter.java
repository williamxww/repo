package com.bow.component.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.bow.service.permission.UserService;

/**
 * 会进行url模式与请求url进行匹配，如果匹配会调用onPreHandle；
 * 如果没有配置url模式/没有url模式匹配，默认直接返回true
 * @author vivid
 * 2015-2-5
 */
public class AttachUserToRequestFilter extends PathMatchingFilter {
	private static final Log log = LogFactory.getLog(AttachUserToRequestFilter.class);
    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String)SecurityUtils.getSubject().getPrincipal();
        log.info("将用户 "+username+"绑定到客户机 "+request.getRemoteAddr());
        request.setAttribute("user", userService.getByUsername(username));
        return true;
    }
}
