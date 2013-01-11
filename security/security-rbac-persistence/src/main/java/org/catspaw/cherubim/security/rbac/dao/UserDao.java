package org.catspaw.cherubim.security.rbac.dao;

import org.catspaw.cherubim.persistence.dao.GenericDao;
import org.catspaw.cherubim.security.rbac.model.UserModel;

public interface UserDao extends GenericDao<UserModel, String> {

	UserModel findByUsername(String username);
}
