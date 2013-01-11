package org.catspaw.cherubim.routing.query.chain;

import org.apache.commons.beanutils.ConvertUtils;

import org.catspaw.cherubim.routing.AccessStrategy;
import org.catspaw.cherubim.routing.annotation.Key;
import org.catspaw.cherubim.routing.query.ReflectUtils;
import org.catspaw.cherubim.routing.query.RoutingParam;
import org.catspaw.cherubim.routing.query.RoutingParams;

class KeyProcesser {

	public void process(RoutingParams params, Key key, Object targetObject) {
		String property = key.property();
		String kind = key.kind();
		String kindProperty = key.kindProperty();
		String type = key.type();
		AccessStrategy accessStrategy = key.accessStrategy();
		String keyValue = null;
		if (property != null && property.length() > 0) {
			keyValue = ReflectUtils.extractPropertyValue(targetObject, property);
		} else {
			keyValue = ConvertUtils.convert(targetObject);
		}
		if (kind == null || kind.length() == 0 && kindProperty != null && kindProperty.length() > 0) {
			kind = ReflectUtils.extractPropertyValue(targetObject, kindProperty);
		}
		RoutingParam param = new RoutingParam();
		param.setKey(keyValue);
		param.setKind(kind);
		param.setAccessStrategy(accessStrategy);
		param.setType(type);
		String[] targets = key.target();
		Class<?>[] targetClasses = key.targetClass();
		if (!"".equals(targets[0])) {
			for (int k = 0; k < targets.length; k++) {
				params.addAssignedRoutingParam(targets[k], param);
			}
		} else if (!Object.class.equals(targetClasses[0])) {
			for (int k = 0; k < targetClasses.length; k++) {
				params.addAssignedRoutingParam(targetClasses[k].getName(), param);
			}
		} else {
			params.addGeneralRoutingParam(param);
		}
	}
}
