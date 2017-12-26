package com.tengyun.modules.sys.security;

import java.io.Serializable;

public class Principal implements Serializable {
	/** 序列化UID */
	private static final long serialVersionUID = 1L;

	private String username;
	
	/**
	 * 用户认证token
	 */
	private String token;
	
	public Principal(String username, String token) {
		super();
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
