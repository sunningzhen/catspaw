package org.catspaw.cherubim.routing.persistence;

import org.catspaw.cherubim.routing.Route;
import org.catspaw.cherubim.routing.RouteHolder;
import org.catspaw.cherubim.routing.RouteLocal;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RouteLocalRoutingDataSource extends AbstractRoutingDataSource {

	private String	routeType;

	@Override
	protected Object determineCurrentLookupKey() {
		RouteHolder holder = RouteLocal.get();
		Route route = holder.getRoute(routeType);
		return route.getValue();
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
}
