package org.catspaw.cherubim.routing.query.chain;

import java.lang.reflect.Method;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.catspaw.cherubim.routing.query.RoutingParams;

public abstract class BaseRoutingCommand implements Command {

	@Override
	public final boolean execute(Context context) throws Exception {
		RoutingProcessContext ctx = (RoutingProcessContext) context;
		RoutingParams params = ctx.getRouteParams();
		Object targetObject = ctx.getTargetObject();
		Method executingMethod = ctx.getExecutingMethod();
		Object[] parameters = ctx.getParameters();
		execute(ctx, params, targetObject, executingMethod, parameters);
		return CONTINUE_PROCESSING;
	}

	protected abstract void execute(RoutingProcessContext context, RoutingParams params, Object targetObject,
			Method executingMethod, Object[] parameters);
}
