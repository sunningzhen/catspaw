package org.catspaw.cherubim.security.authz;

public class AuthorizationException extends SecurityException {

	private static final long	serialVersionUID	= -4072214557429528066L;

	public AuthorizationException() {
		super();
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorizationException(String s) {
		super(s);
	}

	public AuthorizationException(Throwable cause) {
		super(cause);
	}
}
