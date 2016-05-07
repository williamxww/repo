/**  
 * @FileName: Menu.java 
 * @Package com.bow.model.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.common;

/**
 * @ClassName: Menu
 * @Description: 菜单实体类
 * @author ViVi
 * @date 2015年7月4日 上午10:10:09
 */

public class Menu extends Node {

    private String url;

    // 访问这个菜单需要什么权限
    private String needPermission;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the needPermission
     */
    public String getNeedPermission() {
        return needPermission;
    }

    /**
     * @param needPermission
     *            the needPermission to set
     */
    public void setNeedPermission(String needPermission) {
        this.needPermission = needPermission;
    }

}
