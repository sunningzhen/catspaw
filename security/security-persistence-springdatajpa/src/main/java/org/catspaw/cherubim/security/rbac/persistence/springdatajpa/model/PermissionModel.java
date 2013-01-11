package org.catspaw.cherubim.security.rbac.persistence.springdatajpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.catspaw.cherubim.security.rbac.Permission;

@Entity(name = "Permission")
@Table(name = "rbac_permission")
public class PermissionModel implements Permission, Serializable {

	@Id
	private String	id;
	@Basic
	private String	permit;
	@Basic
	@Column(name = "resource_id")
	private String	resourceId;
	@Basic
	@Column(name = "resource_symbol")
	private String	resourceSymbol;
	@Basic
	@Column(name = "operation_id")
	private String	operationId;
	@Basic
	@Column(name = "operation_symbol")
	private String	operationSymbol;

	public String getPermit() {
		return permit;
	}

	public String getResourceSymbol() {
		return resourceSymbol;
	}

	public String getOperationSymbol() {
		return operationSymbol;
	}

	public String getId() {
		return id;
	}

	public String getOperationId() {
		return operationId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public void setOperationSymbol(String operationSymbol) {
		this.operationSymbol = operationSymbol;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public void setResourceSymbol(String resourceSymbol) {
		this.resourceSymbol = resourceSymbol;
	}
}
