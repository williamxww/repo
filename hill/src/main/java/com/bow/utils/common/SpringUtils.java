package com.bow.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;

/**
 * 
 * @ClassName: SpringUtils
 * @Description: 实现了BeanFactoryPostProcessor即可以在beanFactory初始化完成后对其进行更改
 *               实现ApplicationContextAware接口，可以获取到applicationContext
 * @author ViVi
 * @date 2015年5月30日 下午4:55:55
 */
public final class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringUtils.class);

    private static ApplicationContext applicationContext;

    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("SpringUtils初始化了ConfigurableListableBeanFactory");
        SpringUtils.beanFactory = beanFactory;
    }

    public ConfigurableListableBeanFactory getConfigurableListableBeanFactory() {
        return beanFactory;
    }

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * 
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("SpringUtils初始化了applicationContext");
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @Description: 通过ContextLoader的静态方法可以获取ApplicationContext
     * @return
     */
    public ApplicationContext getApplicationContextByContextLoader() {
        return ContextLoader.getCurrentWebApplicationContext();
    }



}
