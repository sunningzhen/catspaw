package org.catspaw.cherubim.security.rbac.model;

import java.io.Serializable;

import org.catspaw.cherubim.security.rbac.Resource;

public class ResourceModel implements Resource, Serializable {

	private String	id;
	private String	code;
	private String	resourceString;

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getResourceString() {
		return resourceString;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}
}
