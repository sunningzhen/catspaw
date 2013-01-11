package org.catspaw.cherubim.security.rbac.persistence.springdatajpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.catspaw.cherubim.security.rbac.Resource;

@Entity(name = "Resource")
@Table(name = "rbac_resource")
public class ResourceModel implements Resource, Serializable {

	@Id
	private String	id;
	@Basic
	private String	symbol;
	@Basic
	@Column(name = "resource_string")
	private String	resourceString;
	@Basic
	private String	type;

	public String getId() {
		return id;
	}

	public String getSymbol() {
		return symbol;
	}

	public String asString() {
		return getResourceString();
	}

	public String getResourceString() {
		return resourceString;
	}

	public String getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSymbol(String code) {
		this.symbol = code;
	}

	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}

	public void setType(String type) {
		this.type = type;
	}
}
