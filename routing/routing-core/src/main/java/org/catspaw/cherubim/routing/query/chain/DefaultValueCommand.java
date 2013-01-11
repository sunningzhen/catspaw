package org.catspaw.cherubim.routing.query.chain;

import java.lang.reflect.Method;
import java.util.Map;

import org.catspaw.cherubim.routing.AccessStrategy;
import org.catspaw.cherubim.routing.query.RoutingParam;
import org.catspaw.cherubim.routing.query.RoutingParams;

public class DefaultValueCommand extends BaseRoutingCommand {

	private String			defaultType;
	private AccessStrategy	defaultAccessStrategy	= AccessStrategy.READ_WRITE;

	@Override
	protected void execute(RoutingProcessContext context, RoutingParams params, Object targetObject,
			Method executingMethod, Object[] parameters) {
		Map<String, RoutingParam> generalRoutingParams = params.getGeneralRoutingParam();
		for (Map.Entry<String, RoutingParam> entry : generalRoutingParams.entrySet()) {
			RoutingParam param = entry.getValue();
			if ("".equals(param.getType())) {
				param.setType(defaultType);
			}
			if (param.getAccessStrategy() == AccessStrategy.DEFAULT) {
				param.setAccessStrategy(defaultAccessStrategy);
			}
		}
		Map<String, Map<String, RoutingParam>> assignedRoutingParams = params.getAssignedRoutingParams();
		for (Map.Entry<String, Map<String, RoutingParam>> entry : assignedRoutingParams.entrySet()) {
			for (Map.Entry<String, RoutingParam> entry2 : entry.getValue().entrySet()) {
				RoutingParam param = entry2.getValue();
				if ("".equals(param.getType())) {
					param.setType(defaultType);
				}
				if (param.getAccessStrategy() == AccessStrategy.DEFAULT) {
					param.setAccessStrategy(defaultAccessStrategy);
				}
			}
		}
	}

	public String getDefaultType() {
		return defaultType;
	}

	public AccessStrategy getDefaultAccessStrategy() {
		return defaultAccessStrategy;
	}

	public void setDefaultType(String defaultType) {
		this.defaultType = defaultType;
	}

	public void setDefaultAccessStrategy(AccessStrategy defaultAccessStrategy) {
		this.defaultAccessStrategy = defaultAccessStrategy;
	}
}
