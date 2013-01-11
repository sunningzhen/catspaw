package org.catspaw.cherubim.socketpool;

public class MakeSocketException extends SocketPoolException {

	private static final long	serialVersionUID	= -4792825187876306998L;

	public MakeSocketException() {
		super();
	}

	public MakeSocketException(String message, Throwable cause) {
		super(message, cause);
	}

	public MakeSocketException(String message) {
		super(message);
	}

	public MakeSocketException(Throwable cause) {
		super(cause);
	}
}
