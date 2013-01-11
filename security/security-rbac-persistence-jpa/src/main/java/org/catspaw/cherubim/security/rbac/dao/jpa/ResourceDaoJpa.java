package org.catspaw.cherubim.security.rbac.dao.jpa;

import org.catspaw.cherubim.persistence.dao.jpa.BaseJpaDao;
import org.catspaw.cherubim.security.rbac.dao.ResourceDao;
import org.catspaw.cherubim.security.rbac.model.ResourceModel;

public class ResourceDaoJpa extends
		BaseJpaDao<ResourceModel, String> implements ResourceDao {
}
