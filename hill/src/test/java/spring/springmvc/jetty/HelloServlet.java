package spring.springmvc.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO 添加类的描述
 *
 * @author acer
 * @version C10 2016年5月17日
 * @since SDP V300R003C10
 */
public class HelloServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1> hello </h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }

}
