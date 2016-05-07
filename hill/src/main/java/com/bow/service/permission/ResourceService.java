/**  
 * @FileName: ResourceService.java 
 * @Package com.bow.service.permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.permission;

import java.util.List;

import com.bow.model.permission.Resource;

/** 
 * @ClassName: ResourceService 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月2日 下午10:23:33  
 */

public interface ResourceService {

    public Resource getResource(long id);

    public int addResource(Resource resource);

    public int deleteResource(Resource resource);

    public int updateResource(Resource resource);

    public List<Resource> getResources();
}
