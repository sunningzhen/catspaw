package org.catspaw.cherubim.security.rbac.persistence.springdatajpa;

import static org.junit.Assert.*;
import java.util.List;

import org.catspaw.cherubim.security.rbac.Permission;
import org.catspaw.cherubim.security.rbac.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("classpath:applicationContext.xml")
public class RbacRepositorySpringDataJpaTest {

	@SpringBeanByType
	private RbacRepositorySpringDataJpa	repository;

	@Test
	public void testFindOperationSymbolsByResourceSymbol() {
		List<String> list = repository.findOperationSymbolsByResourceSymbol("RBAC");
		System.out.println(list.size());
		assertEquals(1, list.size());
	}

	@Test
	public void testFindResourceSymbolByResourceString() throws Exception {
		String symbol = repository.findResourceSymbolByResourceString("/rbac/**");
		assertEquals("RBAC", symbol);
	}

	@Test
	public void testFindResourceSymbolsByRoleCode() {
		List<String> list = repository.findResourceSymbolsByRoleCode("SECURITY_ADMIN");
		System.out.println(list.size());
		assertEquals(1, list.size());
	}

	@Test
	public void testFindPermissionsByUsername() {
		List<? extends Permission> list = repository.findPermissionsByUsername("secadmin");
		System.out.println(list.size());
		assertEquals(1, list.size());
	}

	@Test
	public void testFindRoleCodesByResourceSymbol() {
		List<String> list = repository.findRoleCodesByResourceSymbol("RBAC");
		System.out.println(list.size());
		assertEquals(1, list.size());
	}

	@Test
	public void testFindRoleCodesByUsername() {
		List<String> list = repository.findRoleCodesByUsername("secadmin");
		System.out.println(list.size());
		assertEquals(1, list.size());
	}

	@Test
	public void testFindUserByUsername() {
		User user = repository.findUserByUsername("secadmin");
		assertNotNull(user);
	}
}
