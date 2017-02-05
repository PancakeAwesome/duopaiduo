package com.jrzmq.webapp.common.spring;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import com.jrzmq.core.utils.PropertiesUtil;
import com.wfb.spg.apidoc.common.configer.MySwaggerConfig;

@Configuration
@EnableTransactionManagement
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com", useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Repository.class),
		@Filter(type = FilterType.ANNOTATION, value = Service.class),
		@Filter(type = FilterType.ANNOTATION, value = Component.class) }, excludeFilters = {
				@Filter(type = FilterType.ANNOTATION, value = Controller.class) })
@PropertySource({ "classpath:datasource.properties" })
@Import({DataSourceConfig.class,TransactionConfig.class,MybatisConfig.class,MySwaggerConfig.class})
public class ContextConfig {

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		return ehCacheManagerFactoryBean;
	}
	
	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
		propertyPlaceholderConfigurer.setLocations(new ClassPathResource("smsContent.properties"));
		propertyPlaceholderConfigurer.setLocations(new ClassPathResource("properties/param.properties"));
		propertyPlaceholderConfigurer.setLocations(new ClassPathResource("config.properties"));
		return propertyPlaceholderConfigurer;
	}
	
	@Bean
	public PropertiesUtil propertiesUtil(){
		PropertiesUtil propertiesUtil = new PropertiesUtil("smsContent.properties","properties/param.properties","config.properties");
		return propertiesUtil;
	}
	
	@Bean
	public FreeMarkerConfigurationFactoryBean freemarkerConfiguration(){
		FreeMarkerConfigurationFactoryBean freemarkerConfiguration = new FreeMarkerConfigurationFactoryBean();
		freemarkerConfiguration.setTemplateLoaderPath("classpath:/email");
		return freemarkerConfiguration;
	}
	
}