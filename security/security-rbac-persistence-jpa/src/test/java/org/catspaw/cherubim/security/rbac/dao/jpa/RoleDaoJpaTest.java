package org.catspaw.cherubim.security.rbac.dao.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.catspaw.cherubim.security.rbac.model.RoleModel;
import org.junit.Test;

public class RoleDaoJpaTest {

	private RoleDaoJpa	dao	= new RoleDaoJpa();

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
