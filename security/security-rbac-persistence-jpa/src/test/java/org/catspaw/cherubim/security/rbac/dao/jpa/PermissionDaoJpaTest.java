package org.catspaw.cherubim.security.rbac.dao.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.catspaw.cherubim.security.rbac.model.PermissionModel;
import org.junit.Test;

public class PermissionDaoJpaTest {

	private PermissionDaoJpa	dao	= new PermissionDaoJpa();

	@Test
	public void testTotal() {
		Number total = dao.total();
		assertTrue(total.intValue() > 0);
	}

	@Test
	public void testFindAll() {
		List<PermissionModel> list = dao.findAll();
		assertTrue(list.size() > 0);
	}

	@Test
	public void testFindByDomainCode() {
		List<PermissionModel> permissions = dao.findByDomainCode("SYSTEM");
		assertTrue(permissions.size() > 0);
	}

	@Test
	public void testFindByRoleCode() {
		List<PermissionModel> permissions = dao.findByRoleCode("NORMAL");
		assertTrue(permissions.size() > 0);
	}
}
