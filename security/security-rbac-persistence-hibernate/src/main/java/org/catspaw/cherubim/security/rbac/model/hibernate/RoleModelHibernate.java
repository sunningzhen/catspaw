package org.catspaw.cherubim.security.rbac.model.hibernate;

import java.util.Set;

import org.catspaw.cherubim.security.rbac.Permission;
import org.catspaw.cherubim.security.rbac.model.RoleModel;

public class RoleModelHibernate extends RoleModel {

	private Set<? extends Permission>	permissions;

	public Set<? extends Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<? extends Permission> permissions) {
		this.permissions = permissions;
	}
}
