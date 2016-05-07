/**  
 * @FileName: OperationPermission.java 
 * @Package shiro 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package shiro;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

/**
 * @ClassName: OperationPermission
 * @Description: 这里定义一些操作 测试有无权限去执行
 * @author ViVi
 * @date 2015年6月2日 下午9:23:23
 */

@Service("operationPermission")
public class OperationPermission {

    @RequiresRoles("admin")
    public void addUser() {
        System.out.println("增加用户");
    }

}
