/**  
 * @FileName: CacheManagerEventListenerFactoryImpl.java 
 * @Package com.bow.component.cache 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.cache;

import java.util.Properties;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.event.CacheManagerEventListener;
import net.sf.ehcache.event.CacheManagerEventListenerFactory;

/**
 * @ClassName: CacheManagerEventListenerFactoryImpl
 * @Description: 配置在ehcache.xml中
 * @author ViVi
 * @date 2015年9月10日 下午10:37:03
 */

public class CacheManagerEventListenerFactoryImpl extends CacheManagerEventListenerFactory {

    @Override
    public CacheManagerEventListener createCacheManagerEventListener(CacheManager cacheManager, Properties properties) {
        return new CacheManagerEventListenerImpl();
    }

}
