package org.catspaw.cherubim.security.rbac.persistence.model;

import java.io.Serializable;

import org.catspaw.cherubim.security.rbac.User;

public class UserModel implements User, Serializable {

	private String	id;
	private String	username;
	private String	password;

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
