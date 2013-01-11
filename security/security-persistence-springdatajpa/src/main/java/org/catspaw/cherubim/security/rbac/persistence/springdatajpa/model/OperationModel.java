package org.catspaw.cherubim.security.rbac.persistence.springdatajpa.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.catspaw.cherubim.security.rbac.Operation;

@Entity(name = "Operation")
@Table(name = "rbac_operation")
public class OperationModel implements Operation, Serializable {

	@Id
	private String	id;
	@Basic
	private String	symbol;
	@Basic
	@Column(name = "operation_string")
	private String	operationString;

	public String getSymbol() {
		return symbol;
	}

	public String asString() {
		return getOperationString();
	}

	public String getId() {
		return id;
	}

	public String getOperationString() {
		return operationString;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSymbol(String code) {
		this.symbol = code;
	}

	public void setOperationString(String operationString) {
		this.operationString = operationString;
	}
}
