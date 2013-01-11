package org.catspaw.cherubim.security.rbac.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.catspaw.cherubim.persistence.dao.jpa.BaseJpaDao;
import org.catspaw.cherubim.persistence.jpa.JpaCallback;
import org.catspaw.cherubim.security.rbac.dao.RoleDao;
import org.catspaw.cherubim.security.rbac.model.RoleModel;

public class RoleDaoJpa extends BaseJpaDao<RoleModel, String> implements
		RoleDao {

	public RoleModel findByCode(String code) {
		return findByUnique("code", code);
	}

	public List<RoleModel> findByUserId(final String userId) {
		return findList(new JpaCallback<List<RoleModel>>() {

			public List<RoleModel> doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createNamedQuery("findRolesByUserId");
				query.setParameter(1, userId);
				return query.getResultList();
			}
		});
	}

	public List<RoleModel> findByUsername(final String username) {
		return findList(new JpaCallback<List<RoleModel>>() {

			public List<RoleModel> doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createNamedQuery("findRolesByUsername");
				query.setParameter(1, username);
				return query.getResultList();
			}
		});
	}

	public List<RoleModel> findByPermissionCode(final String code) {
		return findList(new JpaCallback<List<RoleModel>>() {

			public List<RoleModel> doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em
						.createQuery("select r from Role r, Permission p where p.code=?");
				query.setParameter(1, code);
				return query.getResultList();
			}
		});
	}

	public List<RoleModel> findByPermissionCodes(final String[] permissionCodes) {
		final StringBuilder ql = new StringBuilder(
				"select r from Role r, Permission p where p.code in(");
		for (int i = 0; i < permissionCodes.length; i++) {
			ql.append('?');
			if (i < permissionCodes.length - 1) {
				ql.append(',');
			}
		}
		return findList(new JpaCallback<List<RoleModel>>() {

			public List<RoleModel> doInJpa(EntityManager em)
					throws PersistenceException {
				Query query = em.createQuery(ql.toString());
				query.setParameter(1, permissionCodes);
				return query.getResultList();
			}
		});
	}

	public List<RoleModel> findByPermissionCodes(
			Collection<String> permissionCodes) {
		return findByPermissionCodes(permissionCodes
				.toArray(new String[permissionCodes.size()]));
	}
}
