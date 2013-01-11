package org.catspaw.cherubim.security.rbac.dao.spring.jpa;

import org.catspaw.cherubim.persistence.dao.spring.jpa.BaseSpringJpaDao;
import org.catspaw.cherubim.security.rbac.dao.ResourceDao;
import org.catspaw.cherubim.security.rbac.model.ResourceModel;

public class ResourceDaoSpringJpa extends
		BaseSpringJpaDao<ResourceModel, String> implements ResourceDao {
}
