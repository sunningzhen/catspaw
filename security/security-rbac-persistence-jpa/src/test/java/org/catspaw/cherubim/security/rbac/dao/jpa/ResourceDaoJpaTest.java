package org.catspaw.cherubim.security.rbac.dao.jpa;

import static org.junit.Assert.*;

import java.util.List;

import org.catspaw.cherubim.security.rbac.model.ResourceModel;
import org.junit.Test;

public class ResourceDaoJpaTest {

	private ResourceDaoJpa	dao	= new ResourceDaoJpa();

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
