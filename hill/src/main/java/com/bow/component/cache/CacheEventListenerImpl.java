/**  
 * @FileName: CacheEventListenerImpl.java 
 * @Package com.bow.component.cache 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: CacheEventListenerImpl
 * @Description: 缓存监听器
 * @author ViVi
 * @date 2015年7月5日 上午10:01:30
 */

public class CacheEventListenerImpl implements CacheEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CacheEventListenerImpl.class);

    @Override
    public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
        logger.debug("notifyElementRemoved: {}", element);

    }


    @Override
    public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
        logger.debug("notifyElementPut: {}", element);

    }


    @Override
    public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
        logger.debug("notifyElementUpdated: {}", element);

    }


    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {
        logger.debug("notifyElementExpired: {}", element);

    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {
        logger.debug("notifyElementEvicted: {}", element);

    }


    @Override
    public void notifyRemoveAll(Ehcache cache) {
        logger.debug("notifyRemoveAll: {}", cache.getGuid());

    }

    @Override
    public void dispose() {
        logger.debug("dispose");

    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
