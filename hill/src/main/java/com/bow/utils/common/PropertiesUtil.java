package com.bow.utils.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *该工具支持properties修改热加载
 *loadFile(String fileUrl)
 *getProperty(String key, String defaultValue)
 *两方法是同步的，不能并行
 * @author acer
 * @version C10 2016年5月16日
 */
public class PropertiesUtil
{
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    
    /**
     * 默认加载的properties文件
     */
    private static final String[] DEFAULT_PROPERTIES_URL = {"conf/business/system.properties"};
    
    private static final int LOAD_INTERVAL_SECONDS = 10;
    
    /**
     * properties 所有property的容器
     */
    private static Properties properties = new Properties();
    
    static
    {
        for (String url : DEFAULT_PROPERTIES_URL)
        {
            loadFile(url);
        }
        monitor();
    }
    
    private static synchronized void loadFile(String fileUrl)
    {
        // class.getResourceAsStream路径用相对话是相对于本class所在位置。也可以用绝对的如/conf/business/system.properties
        // InputStream is = PropertiesUtil.class.getResourceAsStream(fileUrl);
        // 此处注意ClassLoader.getResourceAsStream只能用相对于classpath的绝对路径,并且不能已 / 开头
        InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileUrl);
        
        try
        {
            properties.load(is);
        }
        catch (IOException e)
        {
            logger.error("IOException when load" + fileUrl, e);
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                logger.error("IOException when close inputStream " + fileUrl, e);
            }
        }
    }
    
    /**
     * 守护线程每隔LOAD_INTERVAL_SECONDS秒去加载一次properties
     */
    private static void monitor()
    {
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
            
                try
                {
                    TimeUnit.SECONDS.sleep(LOAD_INTERVAL_SECONDS);
                    for (String url : DEFAULT_PROPERTIES_URL)
                    {
                        loadFile(url);
                    }
                }
                catch (InterruptedException e)
                {
                    logger.error("properties-monitor-thread Interrupted", e);
                }
            }
            
        }, "properties-monitor-thread");
        // 设置为守护线程后，用户线程结束，此线程立即中断
        t.setDaemon(true);
        t.start();
    
    }
    public static String getProperty(String key)
    {
        return getProperty(key, null);
    }
    
    public static synchronized String getProperty(String key, String defaultValue)
    {
        Assert.notNull(key, "key can not be null");
        return properties.getProperty(key) == null ? defaultValue : properties.getProperty(key);
    }
}
