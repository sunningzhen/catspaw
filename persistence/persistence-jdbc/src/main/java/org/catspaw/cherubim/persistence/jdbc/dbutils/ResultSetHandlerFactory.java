package org.catspaw.cherubim.persistence.jdbc.dbutils;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public final class ResultSetHandlerFactory {

	private ResultSetHandlerFactory() {
	}

	public static BeanListHandler<?> getTrimedNameBeanListHandler(Class<?> beanClass) {
		BeanListHandler<?> handler = new BeanListHandler(beanClass, RowProcessorFactory
				.getNameTrimmedBeanRowProcessor());
		return handler;
	}

	public static BeanHandler<?> getTrimedNameBeanHandler(Class<?> beanClass) {
		BeanHandler<?> handler = new BeanHandler(beanClass, RowProcessorFactory
				.getNameTrimmedBeanRowProcessor());
		return handler;
	}
}
