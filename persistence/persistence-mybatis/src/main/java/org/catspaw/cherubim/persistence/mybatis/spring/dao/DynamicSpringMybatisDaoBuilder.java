package org.catspaw.cherubim.persistence.mybatis.spring.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.catspaw.cherubim.persistence.dao.BuildDaoException;
import org.catspaw.cherubim.persistence.dao.Dao;
import org.catspaw.cherubim.persistence.dao.DaoBuilder;
import org.mybatis.spring.SqlSessionTemplate;

public class DynamicSpringMybatisDaoBuilder implements DaoBuilder {

	private SqlSessionTemplate	sqlSessionTemplate;
	private InvocationHandler	handler	= new Handler();

	public <T extends Dao> T buildDao(Class<T> interfaceClass) throws BuildDaoException {
		return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
											new Class[] { interfaceClass }, handler);
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	private class Handler implements InvocationHandler {

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Select select = method.getAnnotation(Select.class);
			if (select != null) {
				return doSelect(select, method, args);
			}
			Insert insert = method.getAnnotation(Insert.class);
			if (insert != null) {
				return doInsert(insert, method, args);
			}
			Update update = method.getAnnotation(Update.class);
			if (update != null) {
				return doUpdate(update, method, args);
			}
			Delete delete = method.getAnnotation(Delete.class);
			if (delete != null) {
				return doDelete(delete, method, args);
			}
			return method.invoke(proxy, args);
		}

		private Object doSelect(Select select, Method method, Object[] args) {
			String sqlId = select.value()[0];
			Map<String, Object> params = new HashMap<String, Object>();
			String mapKey = null;
			if (args != null) {
				Annotation[][] annos = method.getParameterAnnotations();
				for (int i = 0; i < annos.length; i++) {
					for (int j = 0; j < annos[i].length; j++) {
						if (annos[i][j] instanceof Param) {
							Param param = (Param) annos[i][j];
							String key = param.value();
							params.put(key, args[i]);
						}
						if (annos[i][j] instanceof MapKey) {
							MapKey mapKeyAnno = (MapKey) annos[i][j];
							mapKey = mapKeyAnno.value();
						}
					}
				}
			}
			Class<?> returnType = method.getReturnType();
			if (List.class.isAssignableFrom(returnType)) {
				return getSqlSessionTemplate().selectList(sqlId, params);
			} else if (Map.class.isAssignableFrom(returnType)) {
				return getSqlSessionTemplate().selectMap(sqlId, params, mapKey);
			} else {
				return getSqlSessionTemplate().selectOne(sqlId, params);
			}
		}

		private int doInsert(Insert insert, Method method, Object[] args) {
			String sqlId = insert.value()[0];
			Map<String, Object> params = new HashMap<String, Object>();
			if (args != null) {
				Annotation[][] annos = method.getParameterAnnotations();
				for (int i = 0; i < annos.length; i++) {
					for (int j = 0; j < annos[i].length; j++) {
						if (annos[i][j] instanceof Param) {
							Param param = (Param) annos[i][j];
							String key = param.value();
							params.put(key, args[i]);
						}
					}
				}
			}
			return getSqlSessionTemplate().insert(sqlId, params);
		}

		private int doUpdate(Update update, Method method, Object[] args) {
			String sqlId = update.value()[0];
			Map<String, Object> params = new HashMap<String, Object>();
			if (args != null) {
				Annotation[][] annos = method.getParameterAnnotations();
				for (int i = 0; i < annos.length; i++) {
					for (int j = 0; j < annos[i].length; j++) {
						if (annos[i][j] instanceof Param) {
							Param param = (Param) annos[i][j];
							String key = param.value();
							params.put(key, args[i]);
						}
					}
				}
			}
			return getSqlSessionTemplate().update(sqlId, params);
		}

		private int doDelete(Delete delete, Method method, Object[] args) {
			String sqlId = delete.value()[0];
			Map<String, Object> params = new HashMap<String, Object>();
			if (args != null) {
				Annotation[][] annos = method.getParameterAnnotations();
				for (int i = 0; i < annos.length; i++) {
					for (int j = 0; j < annos[i].length; j++) {
						if (annos[i][j] instanceof Param) {
							Param param = (Param) annos[i][j];
							String key = param.value();
							params.put(key, args[i]);
						}
					}
				}
			}
			return getSqlSessionTemplate().delete(sqlId, params);
		}
	}
}
