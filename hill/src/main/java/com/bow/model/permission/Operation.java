/**  
 * @FileName: Operation.java 
 * @Package com.bow.model.Permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.permission;

/**
 * @ClassName: Operation
 * @Description: 定义所有的操作
 * @author ViVi
 * @date 2015年6月29日 下午10:08:59
 */

public class Operation {

    private Long id;

    private String name;

    private String description;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
