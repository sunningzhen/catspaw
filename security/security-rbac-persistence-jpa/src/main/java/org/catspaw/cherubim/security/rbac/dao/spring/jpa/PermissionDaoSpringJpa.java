package org.catspaw.cherubim.security.rbac.dao.spring.jpa;

import java.util.List;

import org.catspaw.cherubim.persistence.dao.spring.jpa.BaseSpringJpaDao;
import org.catspaw.cherubim.security.rbac.dao.PermissionDao;
import org.catspaw.cherubim.security.rbac.model.PermissionModel;

public class PermissionDaoSpringJpa extends
		BaseSpringJpaDao<PermissionModel, String> implements PermissionDao {

	public List<PermissionModel> findByDomainCode(String domainCode) {
		return findByProperty("domainCode", domainCode);
	}

	public PermissionModel findByDomainCodeAndActionCode(
			String domainCode, String actionCode) {
		List<PermissionModel> permissions = findByProperties(new String[] {
				"domainCode", "actionCode" }, new String[] { domainCode,
				actionCode });
		if (permissions.size() > 0) {
			return permissions.get(0);
		}
		return null;
	}

	public List<PermissionModel> findByRoleCode(String roleCode) {
		return getJpaTemplate().findByNamedQuery("findPermissionsByRoleCode",
													roleCode);
	}
}
