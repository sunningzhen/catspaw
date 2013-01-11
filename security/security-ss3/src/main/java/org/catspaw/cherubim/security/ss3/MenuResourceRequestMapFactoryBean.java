package org.catspaw.cherubim.security.ss3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.util.Assert;

import org.catspaw.cherubim.security.rbac.Resource;
import org.catspaw.cherubim.security.rbac.Permission;
import org.catspaw.cherubim.security.rbac.persistence.RbacRepository;

public class MenuResourceRequestMapFactoryBean implements
		FactoryBean<LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>>, InitializingBean {

	private static final String	RESOURCE_TYPE	= "MENU";
	private static final String	ACCESS_OPERATION	= "ACCESS";
	private RbacRepository		rbacRepository;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(rbacRepository, "RbacRepository must not be null");
	}

	public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> getObject() throws Exception {
		Map<String, Collection<ConfigAttribute>> map = new HashMap<String, Collection<ConfigAttribute>>();
		List<? extends Resource> resources = rbacRepository.findResourcesByType(RESOURCE_TYPE);
		for (Resource resource : resources) {
			String resourceSymbol = resource.getSymbol();
			List<? extends Permission> permissions = rbacRepository
					.findPermissionsByResourceSymbolAndOperationSymbol(resourceSymbol, ACCESS_OPERATION);
			for (Permission permission : permissions) {
				String resourceString = resource.asString();
				Collection<ConfigAttribute> attributes = map.get(resourceString);
				if (attributes == null) {
					attributes = new ArrayList<ConfigAttribute>();
					map.put(resourceString, attributes);
				}
				ConfigAttribute attribute = new SecurityConfig(permission.getPermit());
				attributes.add(attribute);
			}
		}
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> retMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
			RequestMatcher matcher = new AntPathRequestMatcher(entry.getKey());
			retMap.put(matcher, entry.getValue());
		}
		return retMap;
	}

	public Class<?> getObjectType() {
		return LinkedHashMap.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public RbacRepository getRbacRepository() {
		return rbacRepository;
	}

	public void setRbacRepository(RbacRepository rbacRepository) {
		this.rbacRepository = rbacRepository;
	}
}
