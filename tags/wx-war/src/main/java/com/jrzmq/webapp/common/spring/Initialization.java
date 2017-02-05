package com.jrzmq.webapp.common.spring;


import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.util.Log4jConfigListener;

import com.jrzmq.core.web.servlet.JrzmqDispatcherServlet;

public class Initialization implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		
		context.setInitParameter("webAppRootKey", "nj.firecontrol1.root");
		context.setInitParameter("log4jConfigLocation", "classpath:log4j.xml");
		context.setInitParameter("log4jRefreshInterval", "6000");

		context.setInitParameter("contextClass",
				"org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		context.setInitParameter("contextConfigLocation", "com.jrzmq.webapp.common.spring.ContextConfig");
		context.addListener(ContextLoaderListener.class);
		context.addListener(Log4jConfigListener.class);

		ServletRegistration.Dynamic dispatcherServlet = context.addServlet("DispatcherServlet",
		    new JrzmqDispatcherServlet());
		dispatcherServlet.setInitParameter("contextClass",
				"org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		dispatcherServlet.setInitParameter("contextConfigLocation", "com.jrzmq.webapp.common.spring.MvcConfig");
		dispatcherServlet.setLoadOnStartup(1);
		dispatcherServlet.addMapping("/");

		FilterRegistration.Dynamic characterEncodingFilter = context.addFilter("CharacterEncodingFilter",
				new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

	}

}
