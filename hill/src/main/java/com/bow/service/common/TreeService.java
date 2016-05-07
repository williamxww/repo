/**  
 * @FileName: TreeService.java 
 * @Package com.bow.service.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.common;

import java.util.List;

/**
 * @ClassName: TreeService
 * @Description: 此service只提供查询展开子节点的功能，添加删除节点用实际的业务接口实现
 * @author ViVi
 * @date 2015年6月26日 下午8:12:25
 */

public interface TreeService<E> {

    public List<E> expand(E node);

}
