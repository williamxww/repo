/**  
 * @FileName: TransactionServiceTest.java 
 * @Package com.bow.service.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import spring.transaction.TransactionDao;

import com.bow.service.common.TransactionServiceTest;

/**
 * @ClassName: TransactionServiceTest
 * @Description: 编程式事务，方法级事物
 * @author ViVi
 * @date 2015年10月2日 上午9:56:09
 */

@Service
public class TransactionServiceTestImpl implements TransactionServiceTest {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Transactional(rollbackFor = Exception.class)
    public void testTransaction() {
        TransactionDao dao = new TransactionDao();
        dao.add();
        System.out.println("-----------添加了----------------");
        dao.delete();
    }

    public void codeTransaction() {
        TransactionDefinition td = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(td);
        try {
            TransactionDao dao = new TransactionDao();
            dao.add();
            System.out.println("-----------添加了----------------");
            dao.delete();
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
        transactionManager.commit(status);
    }
}
