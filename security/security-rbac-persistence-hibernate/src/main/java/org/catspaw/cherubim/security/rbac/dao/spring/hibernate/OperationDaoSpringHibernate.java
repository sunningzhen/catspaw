package org.catspaw.cherubim.security.rbac.dao.spring.hibernate;

import org.catspaw.cherubim.persistence.dao.spring.hibernate.BaseSpringHibernateDao;
import org.catspaw.cherubim.security.rbac.dao.OperationDao;
import org.catspaw.cherubim.security.rbac.model.OperationModel;

public class OperationDaoSpringHibernate extends
		BaseSpringHibernateDao<OperationModel, String> implements OperationDao {

	public OperationModel findByAccessPermissionCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
}
