package com.wiley.shiro.settings.kernel.entity;

import java.io.Serializable;

/**
 * 用户实体类
 * @author wiley
 *
 */
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String password;
    private String salt;
    private int status;

    public SysUser() {
    }

    public SysUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSalt() {
        return name + salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    // 加盐
    public String getCredentialsSalt() {
        return name + salt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
