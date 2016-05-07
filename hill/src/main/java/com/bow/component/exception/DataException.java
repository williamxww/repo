/**  
 * @FileName: DataException.java 
 * @Package com.bow.component.exception 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.exception;

/**
 * @ClassName: DataException
 * @Description: 非捕获异常，指由于数据库中数据不完整而造成的异常
 * @author ViVi
 * @date 2015年9月27日 上午9:56:47
 */

public class DataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataException() {
        super("数据完整性异常");
    }

    public DataException(String message) {
        super(message);
    }

}
