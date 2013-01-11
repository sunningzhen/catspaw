package org.catspaw.cherubim.security.rbac.model;

import java.io.Serializable;

import org.catspaw.cherubim.security.rbac.Role;

public class RoleModel implements Role, Serializable {

	private String	id;
	private String	code;
	private String	name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
