package org.catspaw.cherubim.persistence.dao;

/**
 * 更新Dao，用于批量更新删除
 * @author 孙宁振
 */
public interface UpdateDao extends Dao {

	/**
	 * 批量执行多条语句
	 * @param queryString
	 * @param params 参数矩阵
	 * @return
	 */
	Object batch(String queryString, Object[][] params);

	Object batch(String queryString, Object[][] params, int batchSize);

	Object update(String queryString, Object... params);
}
