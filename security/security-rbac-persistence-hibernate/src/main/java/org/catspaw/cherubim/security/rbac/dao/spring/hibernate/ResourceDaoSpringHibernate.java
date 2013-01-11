package org.catspaw.cherubim.security.rbac.dao.spring.hibernate;

import org.catspaw.cherubim.persistence.dao.spring.hibernate.BaseSpringHibernateDao;
import org.catspaw.cherubim.security.rbac.dao.ResourceDao;
import org.catspaw.cherubim.security.rbac.model.ResourceModel;

public class ResourceDaoSpringHibernate extends
		BaseSpringHibernateDao<ResourceModel, String> implements ResourceDao {
}
