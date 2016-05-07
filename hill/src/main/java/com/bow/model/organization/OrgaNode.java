/**  
 * @FileName: OrgaNode.java 
 * @Package com.bow.model.organization 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.organization;

/**
 * @ClassName: OrgaNode
 * @Description: 组织机构树的节点
 * @author ViVi
 * @date 2015年7月27日 下午10:29:56
 */

public class OrgaNode {

    private Long id;

    // 上级id
    private Long pid;

    private String name;

    private String code;

    // type:1公司 2 部门 3职务 4员工
    private Integer type = -1;

    private String description;

    // status:1有效 2无效
    private Integer status = 1;

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
     * @return the pid
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(Long pid) {
        this.pid = pid;
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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
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

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


}
