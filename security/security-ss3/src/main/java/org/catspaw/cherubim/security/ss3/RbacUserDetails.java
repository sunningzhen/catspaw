package org.catspaw.cherubim.security.ss3;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.catspaw.cherubim.security.Principal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class RbacUserDetails implements UserDetails {

	private static final long						serialVersionUID	= 7298317238046008045L;
	private Principal								principal;
	private Collection<? extends GrantedAuthority>	grantedAuthorities;

	public RbacUserDetails(Principal principal) {
		this.principal = principal;
		Set<GrantedAuthority> gas = new HashSet<GrantedAuthority>();
		for (String authority : principal.getAuthorities()) {
			GrantedAuthority ga = new SimpleGrantedAuthority(authority);
			gas.add(ga);
		}
		grantedAuthorities = Collections.unmodifiableCollection(gas);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return principal.getUsername();
	}

	@Override
	public String getPassword() {
		return principal.getPassword();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Principal getPrincipal() {
		return principal;
	}

	@Override
	public String toString() {
		return "RbacUserDetails [principal=" + principal + ", grantedAuthorities=" + grantedAuthorities + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grantedAuthorities == null) ? 0 : grantedAuthorities.hashCode());
		result = prime * result + ((principal == null) ? 0 : principal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RbacUserDetails other = (RbacUserDetails) obj;
		if (grantedAuthorities == null) {
			if (other.grantedAuthorities != null)
				return false;
		} else if (!grantedAuthorities.equals(other.grantedAuthorities))
			return false;
		if (principal == null) {
			if (other.principal != null)
				return false;
		} else if (!principal.equals(other.principal))
			return false;
		return true;
	}
}
