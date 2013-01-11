package org.catspaw.cherubim.persistence;

public class PersistenceException extends RuntimeException {

	private static final long	serialVersionUID	= 8828839601776926054L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}
}
