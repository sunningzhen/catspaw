package org.catspaw.cherubim.security.rbac.dao.spring.hibernate;

import org.catspaw.cherubim.persistence.dao.spring.hibernate.BaseSpringHibernateDao;
import org.catspaw.cherubim.security.rbac.dao.UserDao;
import org.catspaw.cherubim.security.rbac.model.UserModel;

public class UserDaoSpringHibernate extends
		BaseSpringHibernateDao<UserModel, String> implements UserDao {

	public UserModel findByUsername(String username) {
		return findByUnique("username", username);
	}
}
