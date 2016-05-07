/**  
 * @FileName: ConfigService.java 
 * @Package com.bow.service.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.common;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: ConfigService
 * @Description: 系统配置应当尽量用缓存，设置失效时间开发用5分钟，生产用30min,失效后再去需重新加载
 * @author ViVi
 * @date 2015年7月3日 下午9:48:25
 */

public class ConfigService {

    private static Map<String, Properties> config = new ConcurrentHashMap<String, Properties>();

    private static ConfigService service = null;

    private ConfigService() {

    }

    public static ConfigService getInstance() {
        if (service == null) {
            service = new ConfigService();
        }
        return service;
    }

    public Properties getConfig(String fileName) {
        return config.get(fileName);
    }

    public String getConfig(String fileName, String key, String defaultValue) {
        if ((config.get(fileName)) == null) {
            return defaultValue;
        }
        String res = config.get(fileName).getProperty(key);
        if (res == null) {
            return defaultValue;
        }
        return res;
    }

    public void loadConfig(String fileName, Properties properties) {
        config.put(fileName, properties);
    }
}
