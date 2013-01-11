package org.catspaw.cherubim.security.rbac.dao.spring.jpa;

import java.util.Collection;
import java.util.List;

import org.catspaw.cherubim.persistence.dao.spring.jpa.BaseSpringJpaDao;
import org.catspaw.cherubim.security.rbac.dao.RoleDao;
import org.catspaw.cherubim.security.rbac.model.RoleModel;

public class RoleDaoSpringJpa extends BaseSpringJpaDao<RoleModel, String>
		implements RoleDao {

	public RoleModel findByCode(String code) {
		return findByUnique("code", code);
	}

	public List<RoleModel> findByUserId(String userId) {
		return getJpaTemplate().findByNamedQuery("findRolesByUserId", userId);
	}

	public List<RoleModel> findByUsername(String username) {
		return getJpaTemplate().findByNamedQuery("findRolesByUsername",
													username);
	}

	public List<RoleModel> findByPermissionCode(String code) {
		return getJpaTemplate()
				.find("select r from Role r, Permission p where p.code=?", code);
	}

	public List<RoleModel> findByPermissionCodes(String[] permissionCodes) {
		StringBuilder ql = new StringBuilder(
				"select r from Role r, Permission p where p.code in(");
		for (int i = 0; i < permissionCodes.length; i++) {
			ql.append('?');
			if (i < permissionCodes.length - 1) {
				ql.append(',');
			}
		}
		return getJpaTemplate().find(ql.toString(), (Object[]) permissionCodes);
	}

	public List<RoleModel> findByPermissionCodes(
			Collection<String> permissionCodes) {
		return findByPermissionCodes(permissionCodes
				.toArray(new String[permissionCodes.size()]));
	}
}
