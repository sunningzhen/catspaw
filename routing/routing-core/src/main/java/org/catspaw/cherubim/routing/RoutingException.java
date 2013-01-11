package org.catspaw.cherubim.routing;

public class RoutingException extends RuntimeException {

	public RoutingException() {
		super();
	}

	public RoutingException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoutingException(String message) {
		super(message);
	}

	public RoutingException(Throwable cause) {
		super(cause);
	}
}
