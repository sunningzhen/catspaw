package org.catspaw.cherubim.security.shiro;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;

import org.catspaw.cherubim.security.Principal;
import org.catspaw.cherubim.security.SecurityMaster;

public class ShiroSecurityMaster implements SecurityMaster {

	public Principal getCurrentPrincipal() {
		return (Principal) SecurityUtils.getSubject().getPrincipal();
	}

	public boolean isPermitted(String resource, String operation) {
		String permission = resource + ":" + operation;
		return isPermitted(permission);
	}

	public boolean isPermitted(String permission) {
		return SecurityUtils.getSubject().isPermitted(permission);
	}

	public boolean isPermittedAll(Collection<String> permissions) {
		return SecurityUtils.getSubject().isPermittedAll(permissions.toArray(new String[permissions.size()]));
	}
}
