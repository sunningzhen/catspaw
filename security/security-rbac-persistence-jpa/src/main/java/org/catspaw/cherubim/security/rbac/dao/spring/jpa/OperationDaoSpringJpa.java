package org.catspaw.cherubim.security.rbac.dao.spring.jpa;

import org.catspaw.cherubim.persistence.dao.spring.jpa.BaseSpringJpaDao;
import org.catspaw.cherubim.security.rbac.dao.OperationDao;
import org.catspaw.cherubim.security.rbac.model.OperationModel;

public class OperationDaoSpringJpa extends
		BaseSpringJpaDao<OperationModel, String> implements OperationDao {

	public OperationModel findByAccessPermissionCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
}
