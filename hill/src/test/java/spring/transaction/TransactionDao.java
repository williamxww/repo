/**  
 * @FileName: TransactionDao.java 
 * @Package spring.transaction 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package spring.transaction;

import org.junit.Test;

import com.bow.controller.BaseTest;
import com.bow.dao.permission.UserDao;
import com.bow.model.permission.User;
import com.bow.service.common.TransactionServiceTest;

/**
 * @ClassName: TransactionDao
 * @Description: 用于测试事务控制
 * @author ViVi
 * @date 2015年10月2日 上午9:14:40
 */

public class TransactionDao extends BaseTest {



    // @Test
    public void add() {
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user = new User();
        user.setUsername("test");
        user.setNickname("test2");
        user.setPassword("test");
        dao.addUser(user);
    }

    // @Test
    public void delete() {
        int a = 1;
        if (a == 1) {
            // throw new RuntimeException("删除时异常");
        }
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user = dao.getByUsername("test");
        // dao.deleteUser(user);
    }

    @Test
    public void test() {
        TransactionServiceTest service = (TransactionServiceTest) context.getBean("transactionServiceTestImpl");
        // service.testTransaction();
        service.codeTransaction();
    }

}
