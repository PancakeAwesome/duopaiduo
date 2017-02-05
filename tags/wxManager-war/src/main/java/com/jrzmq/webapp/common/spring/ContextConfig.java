package com.jrzmq.webapp.common.spring;

import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com", useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Repository.class),
		@Filter(type = FilterType.ANNOTATION, value = Service.class),
		@Filter(type = FilterType.ANNOTATION, value = Component.class) }, excludeFilters = {
				@Filter(type = FilterType.ANNOTATION, value = Controller.class) })
public class ContextConfig {

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		return ehCacheManagerFactoryBean;
	}
}
