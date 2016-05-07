/**  
 * @FileName: OrganizationStatus.java 
 * @Package com.bow.component.constant 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.constant;

/**
 * @ClassName: OrganizationStatus
 * @Description: 与 Organization 相关的状态值
 * @author ViVi
 * @date 2015年6月10日 下午10:50:16
 */

public enum OrganizationStatus {
    // 实例
    VALID(1), INVALID(0);

    // 成员变量
    private int value;

    // 构造函数
    private OrganizationStatus(int status) {
        this.value = status;
    }

    /**
     * 成员方法
     * @return the value
     */
    public int getValue() {
        return value;
    }

}
