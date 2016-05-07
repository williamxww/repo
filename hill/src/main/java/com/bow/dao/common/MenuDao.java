/**  
 * @FileName: MenuDao.java 
 * @Package com.bow.dao.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao.common;

import java.util.List;

import com.bow.model.common.Menu;

/**
 * @ClassName: MenuDao
 * @Description: 操作hill_menu表
 * @author ViVi
 * @date 2015年7月4日 上午10:15:00
 */

public interface MenuDao {

    public List<Menu> getMenus(Long pid);

    public Menu getMenu(Long id);

    public int addMenu(Menu menu);

    public int deleteMenu(Menu menu);

    public int updateMenu(Menu menu);
}
