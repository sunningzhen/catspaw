package org.catspaw.cherubim.security.ss3.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.Assert;

import org.catspaw.cherubim.security.PermitUtils;
import org.catspaw.cherubim.security.ss3.SpringSecuritySecurityMaster;
import org.catspaw.cherubim.security.web.WebSecurityMaster;

public class SpringSecurityWebSecurityMaster extends SpringSecuritySecurityMaster implements WebSecurityMaster,
		InitializingBean {

	private FilterInvocationSecurityMetadataSource	filterInvocationSecurityMetadataSource;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(filterInvocationSecurityMetadataSource, "FilterInvocationSecurityMetadataSource must not be null");
	}

	@Override
	public boolean isPermitted(HttpServletRequest request, String operationSymbol) {
		FilterInvocation fi = new FilterInvocation(request.getContextPath(), request.getServletPath(),
				request.getPathInfo(), request.getQueryString(), request.getMethod());
		return isPermitted(fi, operationSymbol);
	}

	@Override
	public boolean isPermitted(String contextPath, String servletPath, String operationSymbol) {
		FilterInvocation fi = new FilterInvocation(contextPath, servletPath);
		return isPermitted(fi, operationSymbol);
	}

	private boolean isPermitted(FilterInvocation fi, String operationSymbol) {
		Collection<ConfigAttribute> attributes = filterInvocationSecurityMetadataSource.getAttributes(fi);
		List<String> permits = new ArrayList<String>();
		for (ConfigAttribute configAttribute : attributes) {
			String permit = configAttribute.getAttribute();
			String permitOperationSymbol = PermitUtils.extractOperationSymbol(permit);
			if (permitOperationSymbol.equals(PermitUtils.WILDCARD_OPERATION)
					|| permitOperationSymbol.equals(operationSymbol)) {
				permits.add(permit);
			}
		}
		for (String permit : permits) {
			if (isPermitted(permit)) {
				return true;
			}
		}
		return false;
	}

	public FilterInvocationSecurityMetadataSource getFilterInvocationSecurityMetadataSource() {
		return filterInvocationSecurityMetadataSource;
	}

	public void setFilterInvocationSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource) {
		this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
	}
}
