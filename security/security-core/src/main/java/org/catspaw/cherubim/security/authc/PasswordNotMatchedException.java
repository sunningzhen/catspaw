package org.catspaw.cherubim.security.authc;

public class PasswordNotMatchedException extends AuthenticationException {

	private static final long	serialVersionUID	= -7116247965916923262L;
	private String				username;

	public PasswordNotMatchedException(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
