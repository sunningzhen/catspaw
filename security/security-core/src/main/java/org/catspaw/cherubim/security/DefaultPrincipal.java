package org.catspaw.cherubim.security;

import java.util.Collection;
import java.util.Collections;

import org.catspaw.cherubim.security.rbac.User;

public class DefaultPrincipal implements Principal {

	private static final long	serialVersionUID	= 4547017163217680868L;
	private String				username;
	private String				password;
	private Collection<String>	authorities;

	public DefaultPrincipal(String username, String password, Collection<String> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = Collections.unmodifiableCollection(authorities);
	}

	public DefaultPrincipal(User user, Collection<String> authorities) {
		this(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public String getName() {
		return getUsername();
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<String> getAuthorities() {
		return authorities;
	}

	@Override
	public String toString() {
		return "DefaultPrincipal [username=" + username + ", password=[PROTECTED], authorities=" + authorities + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		DefaultPrincipal other = (DefaultPrincipal) obj;
		if (authorities == null) {
			if (other.authorities != null)
				return false;
		} else if (!authorities.equals(other.authorities))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
