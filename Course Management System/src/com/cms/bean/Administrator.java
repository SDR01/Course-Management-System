package com.cms.bean;

public class Administrator {
	
	private String username = "Admin";
	private String password = "Alpha01";
	
	public Administrator() {
		// TODO Auto-generated constructor stub
	}

	public Administrator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Administrator [username=" + username + ", password=" + password + "]";
	}

}
