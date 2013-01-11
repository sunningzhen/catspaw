package org.catspaw.cherubim.security.rbac.dao.spring.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ResourceDaoSpringHibernateTest extends
		AbstractJUnit4SpringContextTests {

	@Autowired
	private ResourceDaoSpringHibernate	dao;

	@Test
	public void testTotal() {
		Number total = dao.total();
		assertTrue(total.intValue() > 0);
	}
}
