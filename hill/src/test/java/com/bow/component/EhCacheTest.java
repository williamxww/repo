/**  
 * @FileName: EhCacheTest.java 
 * @Package com.bow.component 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component;


import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/** 
 * @ClassName: EhCacheTest 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月5日 上午10:24:05  
 */

public class EhCacheTest extends BaseTest {

    @Test
    public void test1() {
        EhCacheCacheManager manager = (EhCacheCacheManager) context.getBean("springCacheManager");
        Cache cache = manager.getCache("test");
        cache.put("aa", "v");
        System.out.println(cache.get("aa"));
    }

}
