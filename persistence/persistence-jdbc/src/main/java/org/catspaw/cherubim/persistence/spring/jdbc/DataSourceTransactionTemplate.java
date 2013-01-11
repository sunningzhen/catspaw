package org.catspaw.cherubim.persistence.spring.jdbc;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

public class DataSourceTransactionTemplate extends TransactionTemplate {

	private static final long serialVersionUID = 3540122637917600530L;

	public DataSourceTransactionTemplate(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(
				dataSource);
		setTransactionManager(transactionManager);
	}
}
