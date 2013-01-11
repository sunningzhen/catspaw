package org.catspaw.cherubim.persistence.mapping;

public interface EntityMappingResolver {

	String getTable();

	String getIdProperty();

	String getIdColumn();

	String getProperty(String column);

	String[] getProperties();

	String getColumn(String propertyName);

	String[] getColumns();
}
