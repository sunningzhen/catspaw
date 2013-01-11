package org.catspaw.cherubim.persistence.dao;

public interface DaoBuilder {

	/**
	 * 根据接口生成DAO实例
	 * @param interfaceClass
	 * @return dao实例
	 * @throws BuildDaoException
	 */
	<T extends Dao> T buildDao(Class<T> interfaceClass)
			throws BuildDaoException;
}
