/**  
 * @FileName: InitHillServlet.java 
 * @Package com.bow.component.servlet 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bow.service.common.ConfigService;

/**
 * @ClassName: InitHillServlet
 * @Description: 
 * 加载配置文件到缓存
 * 初始化环境变量 如applicationName  version
 * 初始化定时任务
 * 
 * @author ViVi
 * @date 2015年7月3日 下午9:15:31
 */

public class InitHillServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(InitHillServlet.class);

    @Override
    public void init() {

        load("conf/business/system.properties");
    }

    private void load(String filePath) {
        logger.debug("加载配置文件 {}", filePath);
        InputStream is = null;
        try {
            is = InitHillServlet.class.getClassLoader().getResourceAsStream(filePath);
            Properties prop = new Properties();
            prop.load(is);
            String key = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.lastIndexOf("."));
            ConfigService.getInstance().loadConfig(key, prop);
        } catch (IOException e) {
            logger.error("系统初始化错误", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("系统初始化错误", e);
            }
        }

    }


    // public static void main(String[] args) {
    // int beginIndex = "conf/business/system.properties".lastIndexOf("/");
    // int endIndex = "conf/business/system.properties".lastIndexOf(".");
    //
    // System.out.println("conf/business/system.properties".substring(beginIndex,
    // endIndex));
    // }

}
