/**  
 * @FileName: ParameterException.java 
 * @Package com.bow.component.exception 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.exception;

import com.bow.component.constant.ResultCode;

/**
 * @ClassName: BusinessException
 * @Description: 业务逻辑异常,此异常一般在service层抛出
 * @author ViVi
 * @date 2015年6月24日 下午10:57:44
 */

public class BusinessException extends WithCodeException {
    static final long serialVersionUID = -1L;

    public BusinessException(String message) {
        super(ResultCode.DEFAULT_BUSINESS_EXCEPTION, message);
    }

    public BusinessException(int code) {
        super(code);
    }

    /**
     * 
     * @param code
     *            业务逻辑的code以3开头
     * @param message
     */
    public BusinessException(int code, String message) {
        super(code, message);
    }
}
