package org.catspaw.cherubim.persistence.dao;

import java.util.ServiceLoader;

public class DiscoveryDaoBuilder implements DaoBuilder {

	public <T extends Dao> T buildDao(Class<T> interfaceClass) throws BuildDaoException {
		ServiceLoader<T> loader = ServiceLoader.load(interfaceClass, interfaceClass
				.getClassLoader());
		return loader.iterator().next();
	}
}
