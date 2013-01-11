package org.catspaw.cherubim.security.shiro.rbac;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.catspaw.cherubim.security.rbac.persistence.RbacRepository;

public class PermissionUtils {

	public static Set<String> toStringPermissions(List<String> resourceCodes, RbacRepository repository) {
		Set<String> perms = new HashSet<String>();
		for (String resourceCode : resourceCodes) {
			perms.add(resourceCode);
			//查询此资源下的操作权限
			List<String> operationCodeList = repository.findOperationSymbolsByResourceSymbol(resourceCode);
			if (operationCodeList.size() > 0) {
				//如果这个资源包含操作权限，则添加WildcardPermission形式的字符串perms[xxx:yyy]
				for (String operationCode : operationCodeList) {
					String perm = resourceCode + ":" + operationCode;
					perms.add(perm);
				}
			}
		}
		return perms;
	}
}
