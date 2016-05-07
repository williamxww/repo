/**  
 * @FileName: User.java 
 * @Package com.bow.model.Permission 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.model.permission;

/**
 * @ClassName: User
 * @Description: 系统的登陆用户实体类
 * @author ViVi
 * @date 2015年6月29日 下午9:48:59
 */

public class User {

    private Long id;

    // 默认的为employeeCode;
    private String username;

    private String password;

    private String salt;

    private Integer locked;

    private String nickname;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     *            the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return the locked
     */
    public Integer getLocked() {
        return locked;
    }

    /**
     * @param locked
     *            the locked to set
     */
    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     *            the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

}
