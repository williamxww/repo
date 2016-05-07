/**  
 * @FileName: CacheManagerEventListenerImpl.java 
 * @Package com.bow.component.cache 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @ClassName: CacheManagerEventListenerImpl 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月10日 下午10:35:44  
 */

public class CacheManagerEventListenerImpl implements CacheManagerEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CacheManagerEventListenerImpl.class);

    @Override
    public void init() throws CacheException {
        logger.info("初始化CacheManager");
    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public void dispose() throws CacheException {
        logger.info("删除CacheManager");
    }

    @Override
    public void notifyCacheAdded(String cacheName) {
        logger.info("新增cache" + cacheName);
    }

    @Override
    public void notifyCacheRemoved(String cacheName) {
        logger.info("删除cache" + cacheName);
    }

}
