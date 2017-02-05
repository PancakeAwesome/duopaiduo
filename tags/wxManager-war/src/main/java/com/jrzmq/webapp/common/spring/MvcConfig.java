package com.jrzmq.webapp.common.spring;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.jrzmq.core.web.argument.JsonMapperArgumentResolver;
import com.jrzmq.core.web.converter.ResultJacksonHttpMessageConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com", useDefaultFilters = false, includeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Controller.class) })
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/webapp/**").addResourceLocations("/WEB-INF/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/pages/", ".jsp").viewClass(InternalResourceView.class);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new ResultJacksonHttpMessageConverter());
		FastJsonHttpMessageConverter jsonConverter = new FastJsonHttpMessageConverter();
		converters.add(jsonConverter);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new JsonMapperArgumentResolver());
	}

}
