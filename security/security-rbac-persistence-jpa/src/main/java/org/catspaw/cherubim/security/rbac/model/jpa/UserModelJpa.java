package org.catspaw.cherubim.security.rbac.model.jpa;

import java.util.Set;

import org.catspaw.cherubim.security.rbac.Role;
import org.catspaw.cherubim.security.rbac.model.UserModel;

public class UserModelJpa extends UserModel {

	private Set<? extends Role>	roles;

	public Set<? extends Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<? extends Role> roles) {
		this.roles = roles;
	}
}
