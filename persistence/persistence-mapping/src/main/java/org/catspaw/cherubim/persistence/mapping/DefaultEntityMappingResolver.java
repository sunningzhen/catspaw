package org.catspaw.cherubim.persistence.mapping;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class DefaultEntityMappingResolver implements EntityMappingResolver {

	private Class<?>			entityClass;
	private String				table;
	private String				idProperty;
	private String				idColumn;
	private String[]			properties;
	private String[]			columns;
	private Map<String, String>	propertyColumnMapping;
	private Map<String, String>	columnPropertyMapping;

	public DefaultEntityMappingResolver(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	public String getTable() {
		if (table == null) {
			table = entityClass.getSimpleName();
		}
		return table;
	}

	public String getIdProperty() {
		if (idProperty == null) {
			idProperty = "id";
		}
		return idProperty;
	}

	public String getIdColumn() {
		if (idColumn == null) {
			idColumn = "id";
		}
		return idColumn;
	}

	public String[] getProperties() {
		if (properties == null) {
			properties = extractProperties();
		}
		return properties;
	}

	private String[] extractProperties() {
		PropertyDescriptor[] pds = PropertyUtils
				.getPropertyDescriptors(entityClass);
		List<String> propertyList = new ArrayList<String>(pds.length);
		for (int i = 0; i < pds.length; i++) {
			if (pds[i].getReadMethod() != null
					&& pds[i].getWriteMethod() != null
					&& !pds[i].getName().equals("class")) {
				propertyList.add(pds[i].getName());
			}
		}
		return propertyList.toArray(new String[propertyList.size()]);
	}

	public String[] getColumns() {
		if (columns == null) {
			columns = getProperties();
		}
		return columns;
	}

	public String getColumn(String propertyName) {
		if (propertyColumnMapping == null) {
			propertyColumnMapping = new HashMap<String, String>();
			for (int i = 0; i < properties.length; i++) {
				propertyColumnMapping.put(properties[i], properties[i]);
			}
		}
		return propertyColumnMapping.get(propertyName);
	}

	public String getProperty(String column) {
		if (columnPropertyMapping == null) {
			columnPropertyMapping = new HashMap<String, String>();
			for (int i = 0; i < columns.length; i++) {
				columnPropertyMapping.put(columns[i], columns[i]);
			}
		}
		return columnPropertyMapping.get(column);
	}

	protected void setTable(String table) {
		this.table = table;
	}

	protected void setIdProperty(String idProperty) {
		this.idProperty = idProperty;
	}

	protected void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	protected void setProperties(String[] properties) {
		this.properties = properties;
	}

	protected void setColumns(String[] columns) {
		this.columns = columns;
	}

	protected void setPropertyColumnMapping(
			Map<String, String> propertyColumnMapping) {
		this.propertyColumnMapping = propertyColumnMapping;
	}

	protected void setColumnPropertyMapping(
			Map<String, String> columnPropertyMapping) {
		this.columnPropertyMapping = columnPropertyMapping;
	}
}
