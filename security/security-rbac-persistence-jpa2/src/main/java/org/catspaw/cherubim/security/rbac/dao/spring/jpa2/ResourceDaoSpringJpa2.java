package org.catspaw.cherubim.security.rbac.dao.spring.jpa2;

import org.catspaw.cherubim.persistence.dao.spring.jpa2.BaseSpringJpa2Dao;
import org.catspaw.cherubim.security.rbac.dao.ResourceDao;
import org.catspaw.cherubim.security.rbac.model.ResourceModel;

public class ResourceDaoSpringJpa2 extends
		BaseSpringJpa2Dao<ResourceModel, String> implements ResourceDao {

}
