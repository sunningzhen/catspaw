package org.catspaw.cherubim.persistence.dao.hibernate.spring;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 为有查询功能的Dao提供查询缓存
 * @author 孙宁振
 */
public class QueryHibernateDaoSupport extends HibernateDaoSupport {

	private boolean	cacheQueries	= false;	//是否使用查询缓存
	private String	queryCacheRegion;			//查询缓存的名称

	/**
	 * 初始化 判断是否使用查询缓存，如使用查询缓存并指定查询缓存的名称，则设定查询缓存名称
	 */
	@Override
	protected void initDao() {
		if (cacheQueries) {
			getHibernateTemplate().setCacheQueries(true);//开启查询缓存
			logger.debug("enable query cache");
			if (queryCacheRegion != null && !queryCacheRegion.equals("")) {
				getHibernateTemplate().setQueryCacheRegion(queryCacheRegion);//更改查询缓存名称
				logger.debug("query cache region is: " + queryCacheRegion);
			}
		}
	}

	public boolean isCacheQueries() {
		return cacheQueries;
	}

	public void setCacheQueries(boolean cacheQueries) {
		this.cacheQueries = cacheQueries;
	}

	public String getQueryCacheRegion() {
		return queryCacheRegion;
	}

	public void setQueryCacheRegion(String queryCacheRegion) {
		this.queryCacheRegion = queryCacheRegion;
	}
}
