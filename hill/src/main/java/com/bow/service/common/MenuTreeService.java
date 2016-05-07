/**  
 * @FileName: MenuTreeService.java 
 * @Package com.bow.service.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.common;

import com.bow.model.common.Menu;

/**
 * @ClassName: MenuTreeService
 * @Description: 主菜单服务
 * @author ViVi
 * @date 2015年7月4日 上午10:04:41
 */

public interface MenuTreeService extends TreeService<Menu> {

    public int addMenu(Menu menu);

    public int deleteMenu(Menu menu);

    public int updateMenu(Menu menu);
}
