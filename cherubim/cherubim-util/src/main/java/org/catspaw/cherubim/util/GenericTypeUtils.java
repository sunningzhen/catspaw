package org.catspaw.cherubim.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 获取指定类的参数化类型 
 * @author 孙宁振 sunningzhen@gmail.com
 * @version 1.0 2011-4-17
 */
public final class GenericTypeUtils {

	private GenericTypeUtils() {
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 
	 * 如public BookManager extends GenricManager<Book>
	 * @param clazz The class to introspect
	 * @return the first generic declaration, or <code>Object.class</code> if
	 *         cannot be determined
	 */
	public static Class<?> getSuperClassGenericType(Class<?> clazz) {
		return getSuperClassGenericType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型. 
	 * 如public BookManager extends GenricManager<Book>
	 * @param clazz clazz The class to introspect
	 * @param index the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or <code>Object.class</code> if
	 *         cannot be determined
	 */
	public static Class<?> getSuperClassGenericType(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (genType == null) {
			return null;
		}
		if (genType instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genType;
			Type[] params = parameterizedType.getActualTypeArguments();
			if (index >= 0 && index < params.length
					&& params[index] instanceof Class) {
				return (Class<?>) params[index];
			}
		}
		return getSuperClassGenericType(clazz.getSuperclass(), index);
	}

	/**
	 * 通过反射,获得定义接口时声明的父接口的范型参数的类型. 
	 * 如public BookManager extends GenricManager<Book>
	 * @param clazz
	 * @return 接口class
	 */
	public static Class<?> getInterfaceGenericType(Class<?> clazz) {
		return getInterfaceGenericType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义接口时声明的父接口的范型参数的类型. 
	 * 如public BookManager extends GenricManager<Book>
	 * @param clazz
	 * @param index
	 * @return 接口class
	 */
	public static Class<?> getInterfaceGenericType(Class<?> clazz, int index) {
		Type[] types = clazz.getGenericInterfaces();
		if (types == null || types.length == 0) {
			return null;
		}
		int n = 0;
		for (int i = 0; i < types.length; i++) {
			if (types[i] instanceof ParameterizedType) {
				ParameterizedType parameterizedType = (ParameterizedType) types[i];
				Type[] params = parameterizedType.getActualTypeArguments();
				if (index >= n && index - n < params.length
						&& params[index - n] instanceof Class) {
					return (Class<?>) params[index - n];
				}
				n += params.length;
			}
		}
		return getInterfaceGenericType(clazz, index);
	}
}
