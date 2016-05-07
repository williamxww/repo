/**  
 * @FileName: Permission.java 
 * @Package com.bow.model.Permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.permission;

/**
 * @ClassName: Permission
 * @Description: 权限 即 资源:操作
 * @author ViVi
 * @date 2015年6月29日 下午9:53:31
 */

public class Permission {

    private Long id;

    private Long operationId;

    private String operationName;

    private Long resourceId;

    private String resourceName;

    // 1:正常的权利 2:用户的特权
    private Integer type = 1;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the operationId
     */
    public Long getOperationId() {
        return operationId;
    }

    /**
     * @param operationId
     *            the operationId to set
     */
    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    /**
     * @return the operationName
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * @param operationName
     *            the operationName to set
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }


    /**
     * @return the resourceId
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     *            the resourceId to set
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return the resourceName
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName
     *            the resourceName to set
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return resourceName + ":" + operationName;
    }

}
