package org.catspaw.cherubim.routing.query;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class ReflectUtils {

	public static String extractPropertyValue(Object obj, String property) {
		try {
			return BeanUtils.getNestedProperty(obj, property);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e.getMessage(), e.getTargetException());
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}
}
