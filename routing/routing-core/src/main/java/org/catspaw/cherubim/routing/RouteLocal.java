package org.catspaw.cherubim.routing;

public class RouteLocal {

	private static final ThreadLocal<RouteHolder>	LOCAL	= new ThreadLocal<RouteHolder>();

	public static void bind(RouteHolder holder) {
		LOCAL.set(holder);
	}

	public static RouteHolder get() {
		return LOCAL.get();
	}

	public static void unbind() {
		LOCAL.remove();
	}
}
