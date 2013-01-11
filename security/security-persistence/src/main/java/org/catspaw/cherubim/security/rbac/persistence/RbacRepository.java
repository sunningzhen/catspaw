package org.catspaw.cherubim.security.rbac.persistence;

import java.util.List;

import org.catspaw.cherubim.security.rbac.Permission;
import org.catspaw.cherubim.security.rbac.Resource;
import org.catspaw.cherubim.security.rbac.User;

public interface RbacRepository {

	User findUserByUsername(String username);

	List<String> findRoleCodesByUsername(String username);

	List<? extends Permission> findPermissionsByUsername(String username);

	List<? extends Permission> findPermissionsByResourceSymbolAndOperationSymbol(String resourceSymbol,
			String operationSymbol);

	List<String> findRoleCodesByResourceSymbol(String resourceSymbol);

	List<String> findOperationSymbolsByResourceSymbol(String resourceSymbol);

	List<String> findResourceSymbolsByRoleCode(String roleCode);

	String findResourceSymbolByResourceString(String resourceString);

	List<? extends Resource> findResourcesByType(String type);
}
