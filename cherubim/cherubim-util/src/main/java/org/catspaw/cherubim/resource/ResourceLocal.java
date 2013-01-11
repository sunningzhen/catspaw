package org.catspaw.cherubim.resource;

import java.util.HashMap;
import java.util.Map;

public class ResourceLocal {

	private static final ThreadLocal<Map<Object, Object>>	resources	= new ThreadLocal<Map<Object, Object>>();

	public static void bindResource(Object key, Object value) throws IllegalStateException {
		Map<Object, Object> map = resources.get();
		// set ThreadLocal Map if none found
		if (map == null) {
			map = new HashMap<Object, Object>();
			resources.set(map);
		}
		map.put(key, value);
	}

	public static Object getResource(Object key) {
		Map<Object, Object> map = resources.get();
		if (map == null) {
			return null;
		}
		return map.get(key);
	}

	public static Object unbindResource(Object key) throws IllegalStateException {
		Map<Object, Object> map = resources.get();
		if (map == null) {
			return null;
		}
		return map.remove(key);
	}
}
