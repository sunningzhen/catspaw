package org.catspaw.cherubim.routing;

import java.util.HashMap;
import java.util.Map;

public class DefaultRouteHolder implements RouteHolder {

	/**
	 * key：type
	 * value：route
	 */
	private Map<String, Route>				generalRoutes;
	private Map<String, Map<String, Route>>	assignedRoutes;
	private String							defaultRouteType;

	public DefaultRouteHolder(Map<String, Route> generalRoutes, Map<String, Map<String, Route>> assignedRoutes,
			String defaultRouteType) {
		this.generalRoutes = generalRoutes;
		this.assignedRoutes = assignedRoutes;
		this.defaultRouteType = defaultRouteType;
	}

	public DefaultRouteHolder(Map<String, Route> generalRoutes, String defaultRouteType) {
		this.generalRoutes = generalRoutes;
		this.assignedRoutes = new HashMap<String, Map<String, Route>>();
		this.defaultRouteType = defaultRouteType;
	}

	@Override
	public Route getAssingedRoute(Class<?> clazz, String type) {
		return getAssingedRoute(clazz.getName(), type);
	}

	@Override
	public Route getAssingedRoute(Class<?> clazz) {
		return getAssingedRoute(clazz, defaultRouteType);
	}

	@Override
	public Route getAssingedRoute(String target, String type) {
		Map<String, Route> routes = assignedRoutes.get(target);
		if (routes != null) {
			return routes.get(type);
		}
		return getRoute(type);
	}

	@Override
	public Route getAssingedRoute(String target) {
		return getAssingedRoute(target, defaultRouteType);
	}

	@Override
	public Route getRoute(String type) {
		return generalRoutes.get(type);
	}

	@Override
	public Route getRoute() {
		return getRoute(defaultRouteType);
	}

	public String getDefaultRouteType() {
		return defaultRouteType;
	}
}
