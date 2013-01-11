package org.catspaw.cherubim.security.rbac.dao.spring.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.catspaw.cherubim.security.rbac.model.PermissionModel;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PermissionDaoSpringHibernateTest extends
		AbstractJUnit4SpringContextTests {

	@Autowired
	private PermissionDaoSpringHibernate	dao;

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
