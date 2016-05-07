/**  
 * @FileName: WithCodeException.java 
 * @Package com.bow.component.exception 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.exception;

import com.bow.component.constant.ResultCode;

/** 
 * @ClassName: WithCodeException 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月28日 下午10:17:13  
 */

public class WithCodeException extends Exception {
    private static final long serialVersionUID = 1L;

    public WithCodeException() {
        super(" [CODE=" + ResultCode.DEFAULT_EXCEPTION + "] ");
    }

    public WithCodeException(int code) {
        super(" [CODE=" + code + "] ");
    }

    public WithCodeException(int code, String messgae) {
        super(" [CODE=" + code + "] " + messgae);
    }

    public WithCodeException(int code, String messgae, Throwable cause) {
        super(" [CODE=" + code + "] " + messgae, cause);
    }
}
