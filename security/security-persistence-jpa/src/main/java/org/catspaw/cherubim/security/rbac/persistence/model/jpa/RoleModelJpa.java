package org.catspaw.cherubim.security.rbac.persistence.model.jpa;

import java.util.Set;

import org.catspaw.cherubim.security.rbac.Permission;
import org.catspaw.cherubim.security.rbac.persistence.model.RoleModel;

public class RoleModelJpa extends RoleModel {

	private Set<? extends Permission>	permissions;

	public Set<? extends Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<? extends Permission> permissions) {
		this.permissions = permissions;
	}
}
