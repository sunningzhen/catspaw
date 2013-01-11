package org.catspaw.cherubim.security.rbac.dao;

import java.util.Collection;
import java.util.List;

import org.catspaw.cherubim.persistence.dao.GenericDao;
import org.catspaw.cherubim.security.rbac.model.RoleModel;

public interface RoleDao extends GenericDao<RoleModel, String> {

	RoleModel findByCode(String code);

	List<RoleModel> findByUserId(String userId);

	List<RoleModel> findByUsername(String username);

	List<RoleModel> findByPermissionCode(String code);

	List<RoleModel> findByPermissionCodes(String[] permissionCodes);

	List<RoleModel> findByPermissionCodes(Collection<String> permissionCodes);
}
