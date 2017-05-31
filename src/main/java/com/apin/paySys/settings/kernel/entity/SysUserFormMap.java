package com.apin.paySys.settings.kernel.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "sys_user")
public class SysUserFormMap implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String password;

	public SysUserFormMap() {
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
