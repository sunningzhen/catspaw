package org.catspaw.cherubim.persistence.mapping;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;

public class EntityMappingUtils {

	public static Object getPropertyValue(Object entity, String property) {
		try {
			return PropertyUtils.getProperty(entity, property);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalStateException(e);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(e);
		}
	}

	public static Object[] getPropertyValues(Object entity, String[] properties) {
		Object[] values = new Object[properties.length];
		for (int i = 0; i < properties.length; i++) {
			values[i] = getPropertyValue(entity, properties[i]);
		}
		return values;
	}
}
