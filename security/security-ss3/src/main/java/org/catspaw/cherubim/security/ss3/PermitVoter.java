package org.catspaw.cherubim.security.ss3;

import java.util.Collection;

import org.catspaw.cherubim.security.PermitUtils;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class PermitVoter implements AccessDecisionVoter<Object> {

	@Override
	public boolean supports(ConfigAttribute attribute) {
		if ((attribute.getAttribute() != null) && attribute.getAttribute().startsWith(PermitUtils.PERMIT_PRIFIX)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		int result = ACCESS_ABSTAIN;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (ConfigAttribute attribute : attributes) {
			if (this.supports(attribute)) {
				result = ACCESS_DENIED;
				String permit = attribute.getAttribute();
				// Attempt to find a matching granted authority
				for (GrantedAuthority grantedAuthority : authorities) {
					String authority = grantedAuthority.getAuthority();
					if (PermitUtils.isPermitted(authority, permit)) {
						return ACCESS_GRANTED;
					}
				}
			}
		}
		return result;
	}
}
