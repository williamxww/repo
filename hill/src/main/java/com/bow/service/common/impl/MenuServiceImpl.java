/**  
 * @FileName: MenuServiceImpl.java 
 * @Package com.bow.service.common.impl 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bow.dao.common.MenuDao;
import com.bow.model.common.Menu;
import com.bow.service.common.MenuTreeService;

/**
 * @ClassName: MenuServiceImpl
 * @Description: 菜单树的展开
 * @author ViVi
 * @date 2015年7月4日 下午12:05:14
 */

public class MenuServiceImpl implements MenuTreeService {

    @Autowired
    private MenuDao dao;

    /**
     * 此方法是从TreeService继承来的
     */
    @Override
    public List<Menu> expand(Menu node) {
        List<Menu> res = new ArrayList<Menu>();
        // 参数校验
        if (node == null) {
            return res;
        }
        List<Menu> menus = dao.getMenus(Long.valueOf(node.getId()));
        for (Menu m : menus) {
            res.add(m);
        }
        return res;
    }


    @Override
    public int addMenu(Menu menu) {
        if (menu == null) {
            return 0;
        }
        return dao.addMenu(menu);
    }


    @Override
    public int deleteMenu(Menu menu) {
        if (menu == null) {
            return 0;
        }
        return dao.deleteMenu(menu);
    }


    @Override
    public int updateMenu(Menu menu) {
        if (menu == null) {
            return 0;
        }
        return dao.updateMenu(menu);
    }

}
