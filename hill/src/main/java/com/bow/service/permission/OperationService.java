/**  
 * @FileName: OperationService.java 
 * @Package com.bow.service.permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.permission;

import java.util.List;

import com.bow.model.permission.Operation;

/** 
 * @ClassName: OperationService 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年7月2日 下午10:14:21  
 */

public interface OperationService {

    public int addOperation(Operation operation);

    public int deleteOperation(Operation operation);

    public int updateOperation(Operation operation);

    public List<Operation> getOperations();

}
