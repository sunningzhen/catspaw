package org.catspaw.cherubim.security.rbac.dao.jpa;

import org.catspaw.cherubim.persistence.dao.jpa.BaseJpaDao;
import org.catspaw.cherubim.security.rbac.dao.UserDao;
import org.catspaw.cherubim.security.rbac.model.UserModel;

public class UserDaoJpa extends BaseJpaDao<UserModel, String> implements
		UserDao {

	public UserModel findByUsername(String username) {
		return findByUnique("username", username);
	}
}
