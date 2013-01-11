package org.catspaw.cherubim.security.rbac.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.catspaw.cherubim.persistence.dao.jpa.BaseJpaDao;
import org.catspaw.cherubim.persistence.jpa.JpaCallback;
import org.catspaw.cherubim.security.rbac.dao.PermissionDao;
import org.catspaw.cherubim.security.rbac.model.PermissionModel;

public class PermissionDaoJpa extends BaseJpaDao<PermissionModel, String>
		implements PermissionDao {

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

	public List<PermissionModel> findByRoleCode(final String roleCode) {
		return findList(new JpaCallback<List<PermissionModel>>() {

			public List<PermissionModel> doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createNamedQuery("findPermissionsByRoleCode");
				query.setParameter(1, roleCode);
				return query.getResultList();
			}
		});
	}
}
