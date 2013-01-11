package org.catspaw.cherubim.pagination.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.catspaw.cherubim.pagination.PaginationParameter;
import org.catspaw.cherubim.pagination.PaginationParameter.SearchMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支持多个页面切换
 * @see HttpServletRequestPageParameterWrapper
 * @author 孙宁振
 */
public class MultiPageSupportHttpServletRequestPageParameterWrapper extends
		HttpServletRequestPageParameterWrapper {

	private static final Logger	logger	= LoggerFactory
												.getLogger(MultiPageSupportHttpServletRequestPageParameterWrapper.class);

	public MultiPageSupportHttpServletRequestPageParameterWrapper(
			HttpServletRequest request) {
		super(request);
	}

	@Override
	protected Map obtainParameterMap(HttpServletRequest request) {
		String uri = request.getRequestURI();
		int pos = uri.indexOf("?");
		if (pos > 0) {
			uri = uri.substring(0, pos);
		}
		HttpSession session = request.getSession();
		Map parameterMap = null;
		PaginationParameter pp = PaginationParameterExtractor.extractFromRequest(request);
		SearchMode searchMode = pp.getSearchMode();
		if (searchMode == PaginationParameter.SearchMode.CHANGE_PAGE && !session.isNew()) {
			logger.debug("seach mode is change page");
			Map<String, Map<String, Object>> multiMap = (Map) session
					.getAttribute(PaginationParameter.PARAMETER_MAP_KEY);
			parameterMap = multiMap.get(uri);
			parameterMap.put(PaginationParameter.PARAMETER_MAP_KEY, pp);
			return parameterMap;
		} else {
			logger.debug("seach mode is research");
			parameterMap = new HashMap(request.getParameterMap());//把新的查询条件封装为一个parameterMap
			Map<String, Map<String, Object>> multiMap = (Map) session
					.getAttribute(PaginationParameter.PARAMETER_MAP_KEY);
			if (multiMap == null) {
				multiMap = new HashMap<String, Map<String, Object>>();
				session.setAttribute(PaginationParameter.PARAMETER_MAP_KEY,
										multiMap);
			}
			multiMap.put(uri, parameterMap);
			return parameterMap;
		}
	}
}
