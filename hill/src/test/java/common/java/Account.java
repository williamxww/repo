/**  
 * @FileName: Account.java 
 * @Package common.java 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package common.java;

/**
 * @ClassName: Account
 * @Description:
 * @author ViVi
 * @date 2015年8月9日 下午3:19:49
 */

public class Account {
    private int id;

    private String name;

    private String email;

    private String address;

    private String birthday;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}
