package org.catspaw.cherubim.security;

import java.util.Date;

public class SecurityException extends RuntimeException {

	private static final long	serialVersionUID	= -6502853503507217643L;
	private Date				timestamp;

	public SecurityException() {
		super();
		timestamp = new Date();
	}

	public SecurityException(String message, Throwable cause) {
		super(message, cause);
		timestamp = new Date();
	}

	public SecurityException(String message) {
		super(message);
		timestamp = new Date();
	}

	public SecurityException(Throwable cause) {
		super(cause);
		timestamp = new Date();
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
