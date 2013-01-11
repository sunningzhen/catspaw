package org.catspaw.cherubim.security.shiro.rbac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.catspaw.cherubim.security.rbac.persistence.RbacRepository;

public class RbacRolePermissionResolver implements RolePermissionResolver {

	private RbacRepository	repository;

	public Collection<Permission> resolvePermissionsInRole(String roleCode) {
		List<Permission> permissions = new ArrayList<Permission>();
		List<String> resourceCodes = repository.findResourceSymbolsByRoleCode(roleCode);
		Set<String> perms = PermissionUtils.toStringPermissions(resourceCodes, repository);
		for (String perm : perms) {
			permissions.add(new WildcardPermission(perm));
		}
		return permissions;
	}

	public RbacRepository getRbacRepository() {
		return repository;
	}

	public void setRbacRepository(RbacRepository repository) {
		this.repository = repository;
	}
}
