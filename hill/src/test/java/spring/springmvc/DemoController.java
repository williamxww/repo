/**  
 * @FileName: DemoController.java 
 * @Package spring.springmvc 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package spring.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: DemoController 
 * @Description: TODO(describe in one sentence) 
 * @author ViVi 
 * @date 2015年9月7日 下午10:25:31  
 */

@Controller
public class DemoController {

    @RequestMapping("/getString.do")
    public String getString(HttpServletRequest request) {
        String param = request.getParameter("param");
        System.out.println(param);
        return "success";
    }
}
