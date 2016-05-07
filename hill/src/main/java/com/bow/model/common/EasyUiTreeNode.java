/**  
 * @FileName: EasyUiTreeNode.java 
 * @Package com.bow.model.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.common;

import java.util.List;

/**
 * @ClassName: EasyUiTreeNode
 * @Description: EasyUi树节点的模型
 * @author ViVi
 * @date 2015年9月13日 下午6:17:38
 */

public class EasyUiTreeNode {

    private String id;

    private String pid;

    private String text;

    private String iconCls;

    // closed 表示初始化时不展示子节点
    private String state;

    private List<EasyUiTreeNode> children;

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
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the children
     */
    public List<EasyUiTreeNode> getChildren() {
        return children;
    }

    /**
     * @param children
     *            the children to set
     */
    public void setChildren(List<EasyUiTreeNode> children) {
        this.children = children;
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
     * @return the iconCls
     */
    public String getIconCls() {
        return iconCls;
    }

    /**
     * @param iconCls
     *            the iconCls to set
     */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    @Override
    public String toString() {
        return "EasyUiTreeNode [id=" + id + ", pid=" + pid + ", text=" + text + ", iconCls=" + iconCls + ", state="
                + state + ", children=" + children + "]";
    }

}
