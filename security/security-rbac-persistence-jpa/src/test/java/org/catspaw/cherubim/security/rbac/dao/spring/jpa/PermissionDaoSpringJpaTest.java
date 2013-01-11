package org.catspaw.cherubim.security.rbac.dao.spring.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.catspaw.cherubim.security.rbac.model.PermissionModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PermissionDaoSpringJpaTest extends
		AbstractJUnit4SpringContextTests {

	@Autowired
	private PermissionDaoSpringJpa	dao;

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
		List<PermissionModel> permissions = dao
				.findByDomainCode("SYSTEM");
		assertTrue(permissions.size() > 0);
	}

	@Test
	public void testFindByRoleCode() {
		List<PermissionModel> permissions = dao.findByRoleCode("NORMAL");
		assertTrue(permissions.size() > 0);
	}
}
