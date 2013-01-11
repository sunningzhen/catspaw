package org.catspaw.cherubim.security.rbac.dao.jpa;

import static org.junit.Assert.*;

import org.catspaw.cherubim.security.rbac.model.UserModel;
import org.junit.Test;

public class UserDaoJpaTest {

	private UserDaoJpa	dao	= new UserDaoJpa();

	@Test
	public void testFindByUsername() {
		UserModel user = dao.findByUsername("manager");
		assertNotNull(user);
	}
}
