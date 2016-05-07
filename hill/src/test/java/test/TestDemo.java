package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO 添加类的描述
 *
 * @author acer
 * @version C10 2016年3月6日
 * @since SDP V300R003C10
 */
public class TestDemo
{
    public void init()
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = "";
            String username = "";
            String passwd = "";
            Connection connection = DriverManager.getConnection(url, username, passwd);
            Statement statament = connection.createStatement();
            ResultSet rs = statament.executeQuery("");
            while (rs.next())
            {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String sex = rs.getString("sex");
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
}
