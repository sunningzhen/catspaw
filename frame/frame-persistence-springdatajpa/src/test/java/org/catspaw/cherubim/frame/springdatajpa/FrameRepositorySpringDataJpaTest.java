package org.catspaw.cherubim.frame.springdatajpa;

import static org.junit.Assert.*;

import java.util.List;

import org.catspaw.cherubim.frame.model.Menu;
import org.catspaw.cherubim.frame.model.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("classpath:applicationContext.xml")
public class FrameRepositorySpringDataJpaTest {

	@SpringBeanByType
	private FrameRepositorySpringDataJpa	repository;

	@Test
	public void testFindAllModules() {
		List<Module> list = repository.findAllModules();
		assertTrue(list.size() > 0);
	}

	@Test
	public void testFindTopMenus() {
		List<Menu> list = repository.findTopMenus("1");
		assertTrue(list.size() > 0);
	}

	@Test
	public void testFindChildMenus() {
		List<Menu> list = repository.findTopMenus("1");
		assertTrue(list.size() > 0);
	}
}
