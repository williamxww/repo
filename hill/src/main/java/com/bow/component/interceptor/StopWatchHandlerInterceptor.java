package com.bow.component.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class StopWatchHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(StopWatchHandlerInterceptor.class);

    /**
     * NamedThreadLocal extends ThreadLocal
     */
    private NamedThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>("响应监控");
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        logger.debug("接受请求：" + request.getRequestURI());
        startTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        return true;//继续流程
    }
    
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();//2、结束时间
        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long consumeTime = endTime - beginTime;
        if(consumeTime > 500) {//此处认为处理时间超过500毫秒的请求为慢请求
            logger.warn("注意：{}花费了 {}毫秒", request.getRequestURI(), consumeTime);
        }
        
    }
    
}
