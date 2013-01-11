package org.catspaw.cherubim.security.rbac.model;

import java.io.Serializable;

import org.catspaw.cherubim.security.rbac.Operation;

public class OperationModel implements Operation, Serializable {

	private String	id;
	private String	code;
	private String	actionString;

	public String getCode() {
		return code;
	}

	public String getActionString() {
		return actionString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setActionString(String actionString) {
		this.actionString = actionString;
	}
}
