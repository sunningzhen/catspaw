package org.catspaw.cherubim.pagination.web;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.catspaw.cherubim.pagination.PaginationParameter;
import org.catspaw.cherubim.pagination.PaginationParameterLocal;
import org.catspaw.cherubim.pagination.PaginationResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分页查询的支援filter 把request包装，在filter内的程序获取的request都是包装类 可以同时处理同一session的多个请求
 * 把本次查询的SearchMode和PageNumber绑定到线程
 * @author 孙宁振
 */
public class PaginationFilter implements Filter {

	private static final Logger	logger				= LoggerFactory
															.getLogger(PaginationFilter.class);
	private boolean				multiPageSupport	= false;

	public void init(FilterConfig config) throws ServletException {
		String str = config.getInitParameter("multiPageSupport");
		multiPageSupport = Boolean.parseBoolean(str);
		if (multiPageSupport) {
			logger.info("multi page support");
		}
		String configLocation = config.getInitParameter("pagination-resource");
		PaginationResource resource = initPaginationResource(configLocation);
		config.getServletContext()
				.setAttribute(PaginationResource.PAGINATION_RESOURCE_KEY,
								resource);
	}

	private PaginationResource initPaginationResource(String configLocation) {
		Properties properties = new Properties();
		InputStream is = null;
		if (configLocation != null) {
			is = PaginationFilter.class.getClassLoader()
					.getResourceAsStream(configLocation);
		} else {
			is = PaginationResource.class
					.getResourceAsStream(PaginationResource.class
							.getSimpleName());
		}
		try {
			properties.load(is);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
		return PaginationResource.parseFromProperties(properties);
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletRequest wrapper = null;
		if (multiPageSupport) {
			wrapper = new MultiPageSupportHttpServletRequestPageParameterWrapper(
					request);
			logger.info("use multi url support");
		} else {
			wrapper = new HttpServletRequestPageParameterWrapper(request);
		}
		PaginationParameter pp = PaginationParameterExtractor.extractFromRequest(servletRequest);
		PaginationParameterLocal.bind(pp);
		logger.debug("PaginationParameter was bind to thread");
		try {
			chain.doFilter(wrapper, servletResponse);
		} finally {
			PaginationParameterLocal.unbind();
			logger.debug("PaginationParameter was remove from thread");
		}
	}

	public void destroy() {
	}
}
