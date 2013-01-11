package org.catspaw.cherubim.security.ss3;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.RequestMatcher;

public class AllMatchedAttributesFilterInvocationSecurityMetadataSource extends
		DefaultFilterInvocationSecurityMetadataSource {

	private final LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>	requestMap;

	public AllMatchedAttributesFilterInvocationSecurityMetadataSource(
			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		super(requestMap);
		this.requestMap = requestMap;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) {
		Collection<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				attributes.addAll(entry.getValue());
			}
		}
		return attributes;
	}
}
