package org.catspaw.cherubim.routing;

public interface RouteHolder {

	Route getAssingedRoute(Class<?> clazz, String type);

	Route getAssingedRoute(Class<?> clazz);

	Route getAssingedRoute(String target, String type);

	Route getAssingedRoute(String target);

	Route getRoute(String type);

	Route getRoute();
}
