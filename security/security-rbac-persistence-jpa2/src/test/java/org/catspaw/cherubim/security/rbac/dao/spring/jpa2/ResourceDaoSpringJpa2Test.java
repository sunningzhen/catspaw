package org.catspaw.cherubim.security.rbac.dao.spring.jpa2;

import static org.junit.Assert.*;

import java.util.List;

import org.catspaw.cherubim.security.rbac.model.ResourceModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ResourceDaoSpringJpa2Test extends
		AbstractJUnit4SpringContextTests {

	@Autowired
	private ResourceDaoSpringJpa2	dao;

	@Test
	public void testTotal() {
		Number total = dao.total();
		assertTrue(total.intValue() > 0);
	}

	@Test
	public void testFindAll() {
		List<ResourceModel> list = dao.findAll();
		assertTrue(list.size() > 0);
	}
}
