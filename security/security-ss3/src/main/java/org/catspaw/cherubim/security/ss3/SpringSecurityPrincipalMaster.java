package org.catspaw.cherubim.security.ss3;

import org.catspaw.cherubim.security.Principal;
import org.catspaw.cherubim.security.PrincipalMaster;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityPrincipalMaster implements PrincipalMaster {

	public Principal getCurrentPrincipal() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		if (auth != null) {
			Object principal = auth.getPrincipal();
			if (principal instanceof Principal) {
				return (Principal) principal;
			}
		}
		return null;
	}
}
