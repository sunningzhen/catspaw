package org.catspaw.cherubim.security.rbac.dao.spring.hibernate;

import static org.junit.Assert.*;

import org.catspaw.cherubim.security.rbac.model.UserModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoSpringHibernateTest extends
		AbstractJUnit4SpringContextTests {

	@Autowired
	private UserDaoSpringHibernate	dao;

	@Test
	public void testFindByUsername() {
		UserModel user = dao.findByUsername("admin");
		assertNotNull(user);
		assertEquals("admin", user.getUsername());
	}
}
