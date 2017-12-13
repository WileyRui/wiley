package com.wiley.shiro.settings.kernel.entity;

import java.io.Serializable;

public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String userName;
    private String password;
    private String salt;
    private int status;

    public SysUser() {
    }

    public SysUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSalt() {
        return userName + salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    // 加盐
    public String getCredentialsSalt() {
        return userName + salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
