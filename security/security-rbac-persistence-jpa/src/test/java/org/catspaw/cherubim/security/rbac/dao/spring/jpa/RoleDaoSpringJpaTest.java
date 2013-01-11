package org.catspaw.cherubim.security.rbac.dao.spring.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.catspaw.cherubim.security.rbac.model.RoleModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RoleDaoSpringJpaTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private RoleDaoSpringJpa	dao;

	@Test
	public void testFindByCode() {
		RoleModel role = dao.findByCode("ADMIN");
		assertNotNull(role);
		assertEquals("ADMIN", role.getCode());
	}

	@Test
	public void testFindByUserId() {
		List<RoleModel> roles = dao
				.findByUserId("7ce30c8e-7539-4271-bb9a-44b4cc4b23e3");
		assertTrue(roles.size() > 0);
	}

	@Test
	public void testFindByUsername() {
		List<RoleModel> roles = dao.findByUsername("manager");
		assertTrue(roles.size() > 0);
	}

	@Test
	public void testFindByPermissionCode() {
		List<RoleModel> roles = dao.findByPermissionCode("ACCESS_FREE");
		assertTrue(roles.size() > 0);
	}
}
