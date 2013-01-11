package org.catspaw.cherubim.persistence.mapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesEntityMappingResolver extends
		DefaultEntityMappingResolver {

	public PropertiesEntityMappingResolver(Class<?> entityClass,
			String configPath) {
		super(entityClass);
		Properties entityProps = new Properties();
		InputStream is = PropertiesEntityMappingResolver.class.getClassLoader()
				.getResourceAsStream(null);
		//TODO 实现
		try {
			entityProps.load(is);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
