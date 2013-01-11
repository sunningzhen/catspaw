package org.catspaw.cherubim.security.ss3;

import java.util.Collection;

import org.catspaw.cherubim.security.PermitUtils;
import org.catspaw.cherubim.security.Principal;
import org.catspaw.cherubim.security.SecurityMaster;
import org.catspaw.cherubim.security.ss3.SpringSecurityPrincipalMaster;

public class SpringSecuritySecurityMaster extends SpringSecurityPrincipalMaster implements SecurityMaster {

	public boolean isPermitted(String resourceSymbol, String operationSymbol) {
		String permit = PermitUtils.buildPermit(resourceSymbol, operationSymbol);
		return isPermitted(permit);
	}

	public boolean isPermitted(String permit) {
		Principal principal = getCurrentPrincipal();
		Collection<String> authorities = principal.getAuthorities();
		if (authorities.contains(permit)) {
			return true;
		}
		for (String authority : authorities) {
			if (PermitUtils.isPermitted(authority, permit)) {
				return true;
			}
		}
		return false;
	}

	public boolean isPermittedAll(Collection<String> permits) {
		Principal principal = getCurrentPrincipal();
		Collection<String> authorities = principal.getAuthorities();
		if (authorities.containsAll(permits)) {
			return true;
		}
		permits: for (String permit : permits) {
			for (String authority : authorities) {
				if (PermitUtils.isPermitted(authority, permit)) {
					continue permits;
				}
			}
			return false;
		}
		return true;
	}
}
