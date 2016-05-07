/**  
 * @FileName: Node.java 
 * @Package com.bow.model.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.common;

/**
 * @ClassName: Node
 * @Description: 树节点的实体类
 * @author ViVi
 * @date 2015年6月26日 下午7:59:09
 */

public class Node {

    private String id;

    private String pid;

    private String name;

    private String icon;

    private Boolean leaf;

    private Boolean expanded; // 描述当前是否是展开状态

    private Boolean checked;

    private String description;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid
     *            the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }



    /**
     * @return the expanded
     */
    public Boolean getExpanded() {
        return expanded;
    }

    /**
     * @param expanded
     *            the expanded to set
     */
    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * @return the leaf
     */
    public Boolean getLeaf() {
        return leaf;
    }

    /**
     * @param leaf
     *            the leaf to set
     */
    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    /**
     * @return the checked
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * @param checked
     *            the checked to set
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
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

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Node [id=" + id + ", pid=" + pid + ", name=" + name + "]";
    }

}
