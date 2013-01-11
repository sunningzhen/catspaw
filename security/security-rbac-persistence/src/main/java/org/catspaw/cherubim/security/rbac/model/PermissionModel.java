package org.catspaw.cherubim.security.rbac.model;

import java.io.Serializable;

import org.catspaw.cherubim.security.rbac.Permission;

public class PermissionModel implements Permission, Serializable {

	private String	id;
	private String	code;
	private String	domainId;
	private String	domainCode;
	private String	actionId;
	private String	actionCode;
	private String	instance;

	public String getCode() {
		return code;
	}

	public String getDomainCode() {
		return domainCode;
	}

	public String getActionCode() {
		return actionCode;
	}

	public String getInstance() {
		return instance;
	}

	public String getId() {
		return id;
	}

	public String getDomainId() {
		return domainId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}
}
