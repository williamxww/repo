package com.bow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @ClassName: SpringContextUtil
 * @Description: 通过监听容器初始化
 * @author ViVi
 * @date 2015年8月15日 上午11:57:48
 */
public class SpringContextUtil implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);

    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * 
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("SpringContextUtil获取到applicationContext");
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return SpringContextUtil.applicationContext;
    }


}
