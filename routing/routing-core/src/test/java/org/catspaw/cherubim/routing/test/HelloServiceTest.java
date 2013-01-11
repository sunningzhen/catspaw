package org.catspaw.cherubim.routing.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("classpath:applicationContext.xml")
public class HelloServiceTest {

	@SpringBeanByType
	private HelloService	helloService;

	@Test
	public void testQuery() {
		helloService.query("123");
	}
	@Test
	public void testQuery2() {
		helloService.query2("123");
	}
}
