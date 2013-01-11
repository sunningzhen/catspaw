package org.catspaw.cherubim.security.shiro.rbac.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.catspaw.cherubim.security.rbac.Resource;
import org.catspaw.cherubim.security.rbac.persistence.RbacRepository;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class FilterChainDefinitionMapFactoryBean implements FactoryBean<Map<String, String>>, InitializingBean {

	private RbacRepository		repository;
	private Map<String, String>	filterChainDefinitions	= new LinkedHashMap<String, String>();

	public void afterPropertiesSet() throws Exception {
		List<? extends Resource> resources = repository.findResourcesByType("URL");
		for (Resource resource : resources) {
			String resourceString = resource.asString();
			String resourceCode = resource.getSymbol();
			List<String> roleCodeList = repository.findRoleCodesByResourceSymbol(resourceCode);
			String[] roleCodes = roleCodeList.toArray(new String[roleCodeList.size()]);
			String roleStr = toRoleString(roleCodes);
			List<String> operationCodes = repository.findOperationSymbolsByResourceSymbol(resourceCode);
			if (operationCodes == null) {
				operationCodes = Collections.EMPTY_LIST;
			}
			String permStr = toWildcardPermissionString(resourceCode, operationCodes);//perms[xxx:yyy:zzz]
			filterChainDefinitions.put(resourceString, roleStr + ", " + permStr);
		}
	}

	private String toRoleString(String[] roleCodes) {
		return "\"roles" + Arrays.toString(roleCodes) + "\"";
	}

	private String toWildcardPermissionString(String resourceCode, Collection<String> operationCodes) {
		String permStr = "\"perms";//perms[xxx:yyy:zzz]
		if (operationCodes.size() > 0) {
			List<String> permList = new ArrayList<String>(operationCodes.size());
			for (String operationCode : operationCodes) {
				String perm = resourceCode + ":" + operationCode;
				permList.add(perm);
			}
			String[] perms = permList.toArray(new String[permList.size()]);
			permStr += Arrays.toString(perms);
		} else {
			permStr += "[" + resourceCode + "]";
		}
		permStr += "\"";
		return permStr;
	}

	public Map<String, String> getObject() throws Exception {
		return filterChainDefinitions;
	}

	public Class<?> getObjectType() {
		return Map.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public RbacRepository getRbacRepository() {
		return repository;
	}

	public Map<String, String> getFilterChainDefinitions() {
		return filterChainDefinitions;
	}

	public void setRbacRepository(RbacRepository repository) {
		this.repository = repository;
	}

	public void setFilterChainDefinitions(Map<String, String> filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}
}
