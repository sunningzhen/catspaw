package org.catspaw.cherubim.routing.query.chain;

import java.lang.reflect.Method;

import org.apache.commons.chain.impl.ContextBase;
import org.catspaw.cherubim.routing.query.RoutingParams;

public class RoutingProcessContext extends ContextBase {

	private RoutingParams	routeParams;
	private Object			targetObject;
	private Method			executingMethod;
	private Object[]		parameters;

	public boolean needRouting() {
		if (!routeParams.getAssignedRoutingParams().isEmpty()) {
			return true;
		}
		if (routeParams.getGeneralRoutingParam() != null) {
			return true;
		}
		return false;
	}

	public RoutingParams getRouteParams() {
		return routeParams;
	}

	public Object getTargetObject() {
		return targetObject;
	}

	public Method getExecutingMethod() {
		return executingMethod;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setRouteParams(RoutingParams routeParams) {
		this.routeParams = routeParams;
	}

	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}

	public void setExecutingMethod(Method executingMethod) {
		this.executingMethod = executingMethod;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
