/**  
 * @FileName: ResultCode.java 
 * @Package com.bow.component.constant 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.constant;

/**
 * @ClassName: ResultCode
 * @Description: 响应码
 * @author ViVi
 * @date 2015年7月28日 下午10:18:55
 */

public interface ResultCode {

    /**
     * 没有给定异常码时默认抛出
     */
    public static final int DEFAULT_EXCEPTION = -20000;

    /**
     * 参数异常默认码
     */
    public static final int DEFAULT_PARAM_EXCEPTION = -20001;

    /**
     * 业务逻辑异常默认码
     */
    public static final int DEFAULT_BUSINESS_EXCEPTION = -30001;
}
