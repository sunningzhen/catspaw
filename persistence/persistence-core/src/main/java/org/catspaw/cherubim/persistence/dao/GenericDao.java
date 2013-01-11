package org.catspaw.cherubim.persistence.dao;

import java.util.List;

/**
 * 泛型Dao基本接口
 * @author 孙宁振
 * @param <E>实体类型
 * @param <P> 实体的主键类型
 */
public interface GenericDao<E, P> extends Dao {

	E create();

	void delete(E entity);

	/**
	 * 查询所有
	 * @return
	 */
	List<E> findAll();

	E get(P id);

	void save(E entity);

	/**
	 * 方法描述：求总数
	 * @return 总数，类型为Integer或Long
	 */
	long total();

	void update(E entity);
}
