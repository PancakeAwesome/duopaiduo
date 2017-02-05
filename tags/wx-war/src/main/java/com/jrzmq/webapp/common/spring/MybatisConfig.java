package com.jrzmq.webapp.common.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages="com.wfb.*.persistence",sqlSessionFactoryRef="sqlSessionFactory")
public class MybatisConfig {
//	@Bean
//	public MapperScannerConfigurer mapperScannerConfigurer(){
//		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
//		configurer.setBasePackage("com.mnw.*.persistence");
//		configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//		
//		
//		return configurer;
//	}
}
