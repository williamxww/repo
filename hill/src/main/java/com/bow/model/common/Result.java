/**  
 * @FileName: Result.java 
 * @Package com.bow.model.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.common;

/**
 * @ClassName: Result
 * @Description: 用于controller 和 前端进行简短交互
 * @author ViVi
 * @date 2015年9月19日 上午10:23:10
 */

public class Result {
    public static final String SUCCESS = "success";

    public static final String ERROR = "error";

    private String status = SUCCESS;

    private String message = "";

    public Result() {

    }

    public Result(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public static String getSuccess() {
        return new Result().toString();
    }

    public static String getError() {
        return new Result(ERROR, "请稍后再试").toString();
    }

    public static String getError(String message) {
        return new Result(ERROR, message).toString();
    }

    @Override
    public String toString() {
        return String.format("{\"status\":\"%s\", \"message\" : \"%s\" }", status, message);
    }

}
