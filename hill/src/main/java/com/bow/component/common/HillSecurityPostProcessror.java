/**  
 * @FileName: HillSecurityPostProcessror.java 
 * @Package com.bow.component.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;

import com.bow.utils.common.EncryptUtils;

/**
 * @ClassName: HillSecurityPostProcessror
 * @Description: 没有测试 用来解开密码的
 * @author ViVi
 * @date 2015年9月12日 上午11:01:26
 */

public class HillSecurityPostProcessror implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition bd = beanFactory.getBeanDefinition("myDataSource");
        MutablePropertyValues mpvs = bd.getPropertyValues();
        if (mpvs.contains("password")) {
            PropertyValue pv = mpvs.getPropertyValue("password");
            Object source = pv.getSource();
            TypedStringValue value = (TypedStringValue) pv.getValue();
            String oldValue = value.getValue();
            mpvs.removePropertyValue("password");

            // 解密
            String newValue = EncryptUtils.decodeBase64String(oldValue);

            PropertyValue npv = new PropertyValue("password", newValue);
            npv.setSource(source);
            // 修改后重新添加
            mpvs.add("password", npv);
        }
    }

}
