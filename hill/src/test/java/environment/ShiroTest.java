/**  
 * @FileName: ShiroTest.java 
 * @Package environment 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package environment;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @ClassName: ShiroTest
 * @Description: 测试分析shiro源码
 * @author ViVi
 * @date 2015年5月31日 下午10:37:01
 */

public class ShiroTest {

    /**
     * 
     * @Title: testShiroEntrance
     * @Description: 用户根据表单信息填写用户名和密码，然后调用登陆按钮。内部执行如下
     * @return void
     */
    @Test
    public void testShiroEntrance() {
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
    }
}
