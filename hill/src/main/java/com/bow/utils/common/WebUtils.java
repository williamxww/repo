package com.bow.utils.common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 *
 * @author acer
 * @version C10 2016年5月30日
 * @since SDP V300R003C10
 */
public class WebUtils
{
    private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);
    
    /**
     * 
     * 注意此处的流不能关闭，可能还有filter对其进行处理。servlet最后会自己关闭该流的
     *
     * @author acer
     * @param response
     * @param msg
     */
    public static void writeJsonToPage(HttpServletResponse response, Object msg)
    {
        try
        {
            response.getWriter().write(JSON.toJSONString(msg));
        }
        catch (IOException e)
        {
            logger.error("fail to writeJsonToPage ", e);
        }
    }

}
