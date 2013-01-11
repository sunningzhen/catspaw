package org.catspaw.cherubim.socketpool;

public class SocketPoolException extends RuntimeException {

	private static final long	serialVersionUID	= -400542427599787819L;

	public SocketPoolException() {
		super();
	}

	public SocketPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public SocketPoolException(String message) {
		super(message);
	}

	public SocketPoolException(Throwable cause) {
		super(cause);
	}
}
