package com.bow.model.vo;

import java.util.List;

/**
 * TODO 添加类的描述
 *
 * @author acer
 * @version C10 2016年5月25日
 * @since SDP V300R003C10
 */
public class TreeGridNode
{
    private String id;
    
    private String name;
    
    private boolean isLeaf;
    
    private List<TreeGridNode> children;
    
    /**
     * 取得id
     * 
     * @return 返回id。
     */
    public String getId()
    {
        return id;
    }
    
    /**
     * 设置id
     * 
     * @param id 要设置的id。
     */
    public void setId(String id)
    {
        this.id = id;
    }
    
    /**
     * 取得isLeaf
     * 
     * @return 返回isLeaf。
     */
    public boolean isLeaf()
    {
        return isLeaf;
    }
    
    /**
     * 设置isLeaf
     * 
     * @param isLeaf 要设置的isLeaf。
     */
    public void setLeaf(boolean isLeaf)
    {
        this.isLeaf = isLeaf;
    }
    
    /**
     * 取得name
     * 
     * @return 返回name。
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * 设置name
     * 
     * @param name 要设置的name。
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * 取得children
     * 
     * @return 返回children。
     */
    public List<TreeGridNode> getChildren()
    {
        return children;
    }
    
    /**
     * 设置children
     * 
     * @param children 要设置的children。
     */
    public void setChildren(List<TreeGridNode> children)
    {
        this.children = children;
    }

}
