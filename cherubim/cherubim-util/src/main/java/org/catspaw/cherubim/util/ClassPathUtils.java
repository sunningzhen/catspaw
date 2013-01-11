package org.catspaw.cherubim.util;

import java.net.URL;

public class ClassPathUtils {

	public static URL loadResource(String name) {
		return loadResource(name, (Class<?>) null);
	}

	public static URL loadResource(String name, Class<?> position) {
		ClassLoader[] loaders = getClassLoaders(position);
		for (int i = 0; i < loaders.length; i++) {
			URL url = loaders[i].getResource(name);
			if (url != null) {
				return url;
			}
		}
		return null;
	}

	public static URL loadResource(String name, ClassLoader classLoader) {
		URL url = classLoader.getResource(name);
		if (url != null) {
			return url;
		}
		return loadResource(name);
	}

	public static ClassLoader[] getClassLoaders() {
		return new ClassLoader[] {
				Thread.currentThread().getContextClassLoader(),
				ClassPathUtils.class.getClassLoader(),
				ClassLoader.getSystemClassLoader() };
	}

	public static ClassLoader[] getClassLoaders(Class<?> clazz) {
		if (clazz == null) {
			return getClassLoaders();
		}
		return new ClassLoader[] {
				Thread.currentThread().getContextClassLoader(),
				clazz.getClassLoader(), ClassPathUtils.class.getClassLoader(),
				ClassLoader.getSystemClassLoader() };
	}
}
