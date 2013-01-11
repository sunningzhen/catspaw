package org.catspaw.cherubim.security.rbac.dao.spring.jpa;

import org.catspaw.cherubim.persistence.dao.spring.jpa.BaseSpringJpaDao;
import org.catspaw.cherubim.security.rbac.dao.UserDao;
import org.catspaw.cherubim.security.rbac.model.UserModel;

public class UserDaoSpringJpa extends
		BaseSpringJpaDao<UserModel, String> implements UserDao {

	public UserModel findByUsername(String username) {
		return findByUnique("username", username);
	}
}
