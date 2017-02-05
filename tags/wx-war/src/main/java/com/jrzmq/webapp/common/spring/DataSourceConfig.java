package com.jrzmq.webapp.common.spring;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;
import com.jrzmq.core.db.MySQLDialect;

@Configuration

public class DataSourceConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource defaultDataSource(){
		DataSource dataSource = new DataSource();
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setMaxIdle(Integer.parseInt(env.getProperty("jdbc.pool.maxIdle")));
		dataSource.setMaxActive(Integer.parseInt(env.getProperty("jdbc.pool.maxActive")));
		return dataSource;
	}
	
	
	@Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(defaultDataSource());
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor offsetLimit = new OffsetLimitInterceptor();
        offsetLimit.setDialect(new MySQLDialect());
        bean.setPlugins(new Interceptor[]{
            offsetLimit
        });
        SqlSessionFactory sessionFactory =  bean.getObject();
        return sessionFactory;
    }
}
