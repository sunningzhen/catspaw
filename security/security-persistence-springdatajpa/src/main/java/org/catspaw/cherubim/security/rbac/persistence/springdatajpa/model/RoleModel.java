package org.catspaw.cherubim.security.rbac.persistence.springdatajpa.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.catspaw.cherubim.security.rbac.Role;

@Entity(name = "Role")
@Table(name = "rbac_role")
public class RoleModel implements Role, Serializable {

	@Id
	private String					id;
	@Basic
	private String					code;
	@Basic
	private String					name;
	@ManyToMany(targetEntity = PermissionModel.class)
	@JoinTable(name = "rbac_role_permission", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id") })
	private Set<PermissionModel>	permissions;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Set<PermissionModel> getPermissions() {
		return permissions;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPermissions(Set<PermissionModel> permissions) {
		this.permissions = permissions;
	}
}
