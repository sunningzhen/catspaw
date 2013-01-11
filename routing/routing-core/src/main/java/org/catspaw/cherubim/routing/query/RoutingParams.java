package org.catspaw.cherubim.routing.query;

import java.util.HashMap;
import java.util.Map;

public class RoutingParams {

	private Map<String, Map<String, RoutingParam>>	assignedRoutingParams	= new HashMap<String, Map<String, RoutingParam>>();
	private Map<String, RoutingParam>				generalRoutingParam		= new HashMap<String, RoutingParam>();
	private boolean									xa						= false;

	public void addAssignedRoutingParam(String target, RoutingParam param) {
		Map<String, RoutingParam> map = assignedRoutingParams.get(target);
		if (map == null) {
			map = new HashMap<String, RoutingParam>();
			assignedRoutingParams.put(target, map);
		}
		map.put(param.getType(), param);
	}

	public void addGeneralRoutingParam(RoutingParam param) {
		generalRoutingParam.put(param.getType(), param);
	}

	public Map<String, Map<String, RoutingParam>> getAssignedRoutingParams() {
		return assignedRoutingParams;
	}

	public Map<String, RoutingParam> getGeneralRoutingParam() {
		return generalRoutingParam;
	}

	public boolean isXa() {
		return xa;
	}

	public void setAssignedRoutingParams(Map<String, Map<String, RoutingParam>> assignedRoutingParams) {
		this.assignedRoutingParams = assignedRoutingParams;
	}

	public void setGeneralRoutingParam(Map<String, RoutingParam> generalRoutingParam) {
		this.generalRoutingParam = generalRoutingParam;
	}

	public void setXa(boolean xa) {
		this.xa = xa;
	}
}
