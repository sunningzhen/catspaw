package org.catspaw.cherubim.security.rbac.dao;

import java.util.List;

import org.catspaw.cherubim.persistence.dao.GenericDao;
import org.catspaw.cherubim.security.rbac.model.PermissionModel;

public interface PermissionDao extends GenericDao<PermissionModel, String> {

	List<PermissionModel> findByDomainCode(String domainCode);

	PermissionModel findByDomainCodeAndActionCode(String domainCode,
			String actionCode);

	List<PermissionModel> findByRoleCode(String roleCode);
}
