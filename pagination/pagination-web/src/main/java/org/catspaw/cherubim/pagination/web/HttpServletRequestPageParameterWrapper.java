package org.catspaw.cherubim.pagination.web;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.catspaw.cherubim.pagination.PaginationParameter;
import org.catspaw.cherubim.pagination.PaginationParameter.SearchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpServletRequest包装 覆盖getParameter等方法 在翻页时，从session里获取以前的查询条件
 * 重新查询时，从request获取查询条件，并放入session
 * @author 孙宁振
 */
public class HttpServletRequestPageParameterWrapper extends
		HttpServletRequestWrapper {

	private static final Logger	logger	= LoggerFactory
												.getLogger(HttpServletRequestPageParameterWrapper.class);
	private Map					parameterMap;																//替代request里的parameterNames

	public HttpServletRequestPageParameterWrapper(HttpServletRequest request) {
		super(request);
		this.parameterMap = obtainParameterMap(request);
	}

	protected Map obtainParameterMap(HttpServletRequest request) {
		Map parameterMap = null;
		HttpSession session = request.getSession();
		PaginationParameter pp = PaginationParameterExtractor.extractFromRequest(request);
		SearchMode searchMode = pp.getSearchMode();
		if (searchMode == PaginationParameter.SearchMode.RESEARCH && !session.isNew()) {
			logger.debug("seach mode is change page");
			parameterMap = (Map) session
					.getAttribute(PaginationParameter.PARAMETER_MAP_KEY);//从session取出以前的查询条件
			parameterMap.put(PaginationParameter.PAGINATION_PARAMETER_KEY, pp);
			return parameterMap;
		} else {
			logger.debug("seach mode is research");
			parameterMap = new HashMap(request.getParameterMap());//把新的查询条件封装为一个parameterMap
			session.setAttribute(PaginationParameter.PARAMETER_MAP_KEY,
									parameterMap);//放到parameterMap里
			return parameterMap;
		}
	}

	/**
	 * 从this.parameterMap里获取parameter
	 * @see org.apache.catalina.core.ApplicationHttpRequest.getParameter(String
	 *      name)
	 */
	@Override
	public String getParameter(String name) {
		Object value = getParameterObject(name);
		if (value == null) {
			return null;
		} else if (value instanceof String[]) {
			return (((String[]) value)[0]);
		} else if (value instanceof String) {
			return ((String) value);
		} else {
			return (value.toString());
		}
	}

	/**
	 * 从this.parameterMap里获取parameterValues
	 * @see org.apache.catalina.core.ApplicationHttpRequest.getParameterValues(
	 *      String name)
	 */
	@Override
	public String[] getParameterValues(String name) {
		Object value = getParameterObject(name);
		if (value == null) {
			return null;
		} else if (value instanceof String[]) {
			return ((String[]) value);
		} else if (value instanceof String) {
			String[] values = new String[1];
			values[0] = (String) value;
			return (values);
		} else {
			String[] values = new String[1];
			values[0] = value.toString();
			return (values);
		}
	}

	/**
	 * 从this.parameterMap里获取parameter
	 */
	private Object getParameterObject(String name) {
		Map parameters = getParameterMap();
		return parameters.get(name);
	}

	/**
	 * 返回this.parameterMap
	 */
	@Override
	public Map getParameterMap() {
		return parameterMap;
	}

	@Override
	public Enumeration getParameterNames() {
		return new Enumeration() {

			private Iterator	iter	= getParameterMap().keySet().iterator();

			public boolean hasMoreElements() {
				return iter.hasNext();
			}

			public Object nextElement() {
				return iter.next();
			}
		};
	}
}
