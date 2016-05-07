/**  
 * @FileName: SendMsg.java 
 * @Package com.bow.component.dwr 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.component.dwr;

import java.util.Collection;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

/**
 * @ClassName: SendMsg
 * @Description: Test
 * @author ViVi
 * @date 2015年6月8日 下午8:49:32
 */

public class SendMsg {
    @SuppressWarnings("deprecation")
    public void sendMsg(String msg) {
        System.out.println("dwr 获取到信息： " + msg);
        // 得到上下文
        WebContext context = WebContextFactory.get();
        String currentPage = context.getCurrentPage();
        System.out.println("dwr函数所在文件：" + currentPage);
        // 得到要推送到 的页面 dwr3为项目名称 ， 一定要加上。
        Collection<ScriptSession> sessions = context.getScriptSessionsByPage("/hill/dwrjsp/showMsg.jsp");

        // 不知道该怎么解释这个 ，
        Util util = new Util(sessions);

        // 下面是创建一个javascript脚本 ， 相当于在页面脚本中添加了一句 show(msg);
        ScriptBuffer sb = new ScriptBuffer();
        sb.appendScript("show('");
        sb.appendData(msg);
        sb.appendScript("')");

        // 推送
        util.addScript(sb);
    }
}
