package org.catspaw.cherubim.security.rbac.dao.spring.hibernate;

import java.util.List;

import org.catspaw.cherubim.persistence.dao.spring.hibernate.BaseSpringHibernateDao;
import org.catspaw.cherubim.security.rbac.dao.PermissionDao;
import org.catspaw.cherubim.security.rbac.model.PermissionModel;

public class PermissionDaoSpringHibernate extends
		BaseSpringHibernateDao<PermissionModel, String> implements
		PermissionDao {

	public List<PermissionModel> findByDomainCode(String domainCode) {
		return findByProperty("domainCode", domainCode);
	}

	public List<PermissionModel> findByRoleCode(String roleCode) {
		return getHibernateTemplate()
				.findByNamedQuery("findPermissionsByRoleCode", roleCode);
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
}
