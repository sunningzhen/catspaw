package org.catspaw.cherubim.persistence.dao;

public class BuildDaoException extends RuntimeException {

	private static final long	serialVersionUID	= 4146703926994655868L;

	public BuildDaoException() {
		super();
	}

	public BuildDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuildDaoException(String message) {
		super(message);
	}

	public BuildDaoException(Throwable cause) {
		super(cause);
	}
}
