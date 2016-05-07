/**  
 * @FileName: CacheEventListenerFactoryImpl.java 
 * @Package com.bow.component.cache 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.cache;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * @ClassName: CacheEventListenerFactoryImpl
 * @Description: 通过该工厂获取缓存监听器
 * @author ViVi
 * @date 2015年7月5日 上午9:59:48
 */

public class CacheEventListenerFactoryImpl extends CacheEventListenerFactory {

    @Override
    public CacheEventListener createCacheEventListener(Properties properties) {
        return new CacheEventListenerImpl();
    }

}
