package org.catspaw.ppp.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.database.annotations.Transactional;
import org.unitils.database.util.TransactionMode;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext("classpath:applicationContext.xml")
public class AttentionTest {

	@SpringBeanByType
	private JobLauncher				launcher;
	@SpringBeanByType
	private JobParametersBuilder	builder;
	@SpringBeanByType
	private Job						job;

	@Test
	@Transactional(value = TransactionMode.DISABLED)
	public void test() throws Exception {
		launcher.run(job, builder.toJobParameters());
	}
}
