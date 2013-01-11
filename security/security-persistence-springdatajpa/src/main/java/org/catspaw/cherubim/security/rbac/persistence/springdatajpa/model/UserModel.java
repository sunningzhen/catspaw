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

import org.catspaw.cherubim.security.rbac.User;

@Entity(name = "User")
@Table(name = "rbac_user")
public class UserModel implements User, Serializable {

	@Id
	private String			id;
	@Basic
	private String			username;
	@Basic
	private String			password;
	@ManyToMany(targetEntity = RoleModel.class)
	@JoinTable(name = "rbac_user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") })
	private Set<RoleModel>	roles;

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}
}
