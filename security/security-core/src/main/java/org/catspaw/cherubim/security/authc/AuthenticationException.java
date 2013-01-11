package org.catspaw.cherubim.security.authc;

public class AuthenticationException extends SecurityException {

	private static final long	serialVersionUID	= 2656652174121162872L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}
}
