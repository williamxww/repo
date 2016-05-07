/**  
 * @FileName: ParameterException.java 
 * @Package com.bow.component.exception 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.exception;

import com.bow.component.constant.ResultCode;

/**
 * @ClassName: ParameterException
 * @Description: 所有参数异常均用此异常来构造
 *               建立此类主要是为了防止需要在其他地方捕获此exception,最好不要直接将RuntimeException捕获
 * 
 *               此异常一般在action检查参数时抛出或DAO层抛出(与业务逻辑关系不大的错误)
 * @author ViVi
 * @date 2015年6月24日 下午10:57:44
 */

public class ParameterException extends WithCodeException {
    static final long serialVersionUID = -1L;

    public ParameterException(String message) {
        super(ResultCode.DEFAULT_PARAM_EXCEPTION, message);
    }

    public ParameterException(int code) {
        super(code);
    }
}
