package org.catspaw.cherubim.routing.query.chain;

import java.lang.reflect.Method;

import org.catspaw.cherubim.routing.annotation.Xa;
import org.catspaw.cherubim.routing.query.RoutingParams;

public class XaCommand extends BaseRoutingCommand {

	@Override
	protected void execute(RoutingProcessContext context, RoutingParams params, Object targetObject,
			Method executingMethod, Object[] parameters) {
		Xa xa = executingMethod.getAnnotation(Xa.class);
		if (xa != null) {
			params.setXa(true);
		}
	}
}
