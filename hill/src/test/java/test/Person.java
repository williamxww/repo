package test;

/**
 * TODO 添加类的描述
 *
 * @author acer
 * @version C10 2016年3月6日
 * @since SDP V300R003C10
 */
public class Person
{
    public static String VERSION = "V1";
    
    public static String getN()
    {
        return "aa";
    }
    
    public int age;
    
    private boolean gender;
    
    private int height;
    
    private String name;
    
    public String getName()
    {
        return null;
    }
    
    public int getAge()
    {
        return this.age;
    }
    
    public void setAge(int a)
    {
        this.age = a;
        
    }
    
    public String getVersion()
    {
        return this.VERSION;
    }
}

