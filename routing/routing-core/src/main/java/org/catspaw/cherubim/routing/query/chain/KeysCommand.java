package org.catspaw.cherubim.routing.query.chain;

import java.lang.reflect.Method;

import org.catspaw.cherubim.routing.annotation.Key;
import org.catspaw.cherubim.routing.annotation.Keys;
import org.catspaw.cherubim.routing.query.RoutingParams;

public class KeysCommand extends BaseRoutingCommand {

	private KeyProcesser	keyProcesser	= new KeyProcesser();

	@Override
	protected void execute(RoutingProcessContext context, RoutingParams params, Object targetObject,
			Method executingMethod, Object[] parameters) {
		Keys keys = executingMethod.getAnnotation(Keys.class);
		if (keys != null) {
			Key[] ks = keys.value();
			for (int i = 0; i < ks.length; i++) {
				keyProcesser.process(params, ks[i], targetObject);
			}
		}
	}
}
