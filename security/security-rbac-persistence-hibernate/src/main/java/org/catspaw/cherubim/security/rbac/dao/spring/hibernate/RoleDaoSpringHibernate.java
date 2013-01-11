package org.catspaw.cherubim.security.rbac.dao.spring.hibernate;

import java.util.Collection;
import java.util.List;

import org.catspaw.cherubim.persistence.dao.spring.hibernate.BaseSpringHibernateDao;
import org.catspaw.cherubim.security.rbac.dao.RoleDao;
import org.catspaw.cherubim.security.rbac.model.RoleModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class RoleDaoSpringHibernate extends
		BaseSpringHibernateDao<RoleModel, String> implements RoleDao {

	public RoleModel findByCode(String code) {
		return findByUnique("code", code);
	}

	public List<RoleModel> findByUserId(String userId) {
		return getHibernateTemplate().findByNamedQuery("findRolesByUserId",
														userId);
	}

	public List<RoleModel> findByUsername(String username) {
		return getHibernateTemplate().findByNamedQuery("findRolesByUsername",
														username);
	}

	public List<RoleModel> findByPermissionCode(String code) {
		DetachedCriteria criteria = DetachedCriteria.forEntityName("Role");
		criteria.createAlias("permissions", "p");
		criteria.add(Restrictions.eq("p.code", code));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List<RoleModel> findByPermissionCodes(String[] permissionCodes) {
		DetachedCriteria criteria = DetachedCriteria.forEntityName("Role");
		criteria.createAlias("permissions", "p");
		criteria.add(Restrictions.in("p.code", permissionCodes));
		return getHibernateTemplate().findByCriteria(criteria);
	}

	public List<RoleModel> findByPermissionCodes(
			Collection<String> permissionCodes) {
		DetachedCriteria criteria = DetachedCriteria.forEntityName("Role");
		criteria.createAlias("permissions", "p");
		criteria.add(Restrictions.in("p.code", permissionCodes));
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
