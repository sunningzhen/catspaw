package org.catspaw.cherubim.security.rbac.dao;

import java.util.List;

import org.catspaw.cherubim.persistence.dao.GenericDao;
import org.catspaw.cherubim.security.rbac.model.ResourceModel;

public interface ResourceDao extends GenericDao<ResourceModel, String> {

	List<ResourceModel> find(int offset, int max);
}
