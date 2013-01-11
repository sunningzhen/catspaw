package org.catspaw.cherubim.persistence.jdbc.dbutils;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.RowProcessor;

public final class RowProcessorFactory {

	private static final RowProcessor	rowProcessor	= new BasicRowProcessor(
																new NameTrimmedBeanProcessor());

	private RowProcessorFactory() {
	}

	public static RowProcessor getNameTrimmedBeanRowProcessor() {
		return rowProcessor;
	}
}
