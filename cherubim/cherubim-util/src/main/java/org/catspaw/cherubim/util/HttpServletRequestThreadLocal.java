package org.catspaw.cherubim.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public final class HttpServletRequestThreadLocal {

	private static final InheritableThreadLocal<HttpServletRequest>	LOCAL	= new InheritableThreadLocal<HttpServletRequest>();

	public static HttpServletRequest getRequest() {
		return LOCAL.get();
	}

	public static class Filter implements javax.servlet.Filter {

		public void doFilter(ServletRequest servletRequest,
				ServletResponse servletResponse, FilterChain chain)
				throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			LOCAL.set(request);
			try {
				chain.doFilter(request, servletResponse);
			} finally {
				LOCAL.remove();
			}
		}

		public void init(FilterConfig config) throws ServletException {
		}

		public void destroy() {
		}
	}
}
