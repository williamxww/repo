/**  
 * @FileName: ResourceDao.java 
 * @Package com.bow.dao.permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao.permission;

import java.util.List;

import com.bow.model.permission.Resource;

/**
 * @ClassName: ResourceDao
 * @Description: 处理 资源
 * @author ViVi
 * @date 2015年6月29日 下午10:59:28
 */

public interface ResourceDao {

    public Resource getResource(long id);

    public int addResource(Resource resource);

    public int deleteResource(Resource resource);

    public int updateResource(Resource resource);

    public List<Resource> getResources();
}
