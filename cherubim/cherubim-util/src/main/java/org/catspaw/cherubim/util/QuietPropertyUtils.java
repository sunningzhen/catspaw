package org.catspaw.cherubim.util;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

public final class QuietPropertyUtils {

	private QuietPropertyUtils() {
	}

	public static void copyPropertyQuietly(Object dest, Object orig) {
		try {
			PropertyUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
	}

	public static void setPropertyQuietly(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
	}

	public static void setPropertyQuietlyWithoutNull(Object bean, String name,
			Object value) {
		if (value != null) {
			setPropertyQuietly(bean, name, value);
		}
	}

	public static Object[] getProperties(List<Object> list, String propertyName) {
		Object[] properties = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Object bean = list.get(i);
			try {
				properties[i] = PropertyUtils.getProperty(bean, propertyName);
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			} catch (NoSuchMethodException e) {
			}
		}
		return properties;
	}

	public static Object getPropertyQuietly(Object bean, String propertyName) {
		try {
			return PropertyUtils.getProperty(bean, propertyName);
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		}
		return null;
	}

	public static String getPropertiesString(List<Object> list,
			String propertyName) {
		StringBuilder builder = new StringBuilder();
		int size = list.size();
		int n = 1;
		for (Object bean : list) {
			try {
				Object property = PropertyUtils.getProperty(bean, propertyName);
				builder.append(property.toString());
				if (n < size) {
					builder.append(",");
				}
				n++;
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			} catch (NoSuchMethodException e) {
			}
		}
		return builder.toString();
	}
}
