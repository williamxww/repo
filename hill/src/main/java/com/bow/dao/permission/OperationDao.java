/**  
 * @FileName: OperationDao.java 
 * @Package com.bow.dao.permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.dao.permission;

import java.util.List;

import com.bow.model.permission.Operation;

/**
 * @ClassName: OperationDao
 * @Description: Operation 的 Dao
 * @author ViVi
 * @date 2015年6月29日 下午10:56:12
 */

public interface OperationDao {

    public Operation getOperation(long id);

    public int addOperation(Operation operation);

    public int deleteOperation(Operation operation);

    public int updateOperation(Operation operation);

    public List<Operation> getOperations();
}
