package org.catspaw.cherubim.routing.query.chain;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.catspaw.cherubim.routing.annotation.Key;
import org.catspaw.cherubim.routing.query.RoutingParams;

public class KeyCommand extends BaseRoutingCommand {

	private KeyProcesser	keyProcesser	= new KeyProcesser();

	@Override
	protected void execute(RoutingProcessContext context, RoutingParams params, Object targetObject,
			Method executingMethod, Object[] parameters) {
		processMethodAnnotation(params, targetObject, executingMethod);
		processParameterAnnotation(params, executingMethod, parameters);
	}

	private void processMethodAnnotation(RoutingParams params, Object targetObject, Method executingMethod) {
		Key key = executingMethod.getAnnotation(Key.class);
		if (key != null) {
			keyProcesser.process(params, key, targetObject);
		}
	}

	private void processParameterAnnotation(RoutingParams params, Method executingMethod, Object[] parameters) {
		Annotation[][] annos = executingMethod.getParameterAnnotations();
		for (int i = 0; i < annos.length; i++) {
			for (int j = 0; j < annos[i].length; j++) {
				if (annos[i][j] instanceof Key) {
					Key key = (Key) annos[i][j];
					keyProcesser.process(params, key, parameters[i]);
				}
			}
		}
	}
}
