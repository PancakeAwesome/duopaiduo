package com.jrzmq.webapp.common.spring;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class TransactionConfig {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public DataSourceTransactionManager defaultTx(){
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		return transactionManager;
	}
}
