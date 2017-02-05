package cn.digirun.admin.config;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import cn.digirun.admin.annotation.RequiredAuthority;
import cn.digirun.admin.service.manager.IAdminManager;
import cn.digirun.admin.service.manager.impl.InitDataManager;
import cn.digirun.admin.service.model.Admin;
import cn.digirun.admin.service.model.Role;

/** 
 * @ClassName: AdminSecurityConfiguration 
 * @Description: 权限配置
 * @author 管东海
 *  
 */
@Configuration
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter implements InitializingBean {

	@Autowired
	private InitDataManager initDataManager;

	@Autowired
	private RequestMappingHandlerMapping mapping;

	@Autowired
	private IAdminManager adminMgr;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		try {
			auth.userDetailsService(adminMgr).passwordEncoder(new BCryptPasswordEncoder());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 解析所有权限注解，并约定配置
		
		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry urlRegistry = http
				.authorizeRequests();
		urlRegistry.regexMatchers("/").authenticated();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
		Iterator<RequestMappingInfo> keys = handlerMethods.keySet().iterator();
		while (keys.hasNext()) {

			RequestMappingInfo key = keys.next();

			HandlerMethod value = handlerMethods.get(key);
			RequiredAuthority requiredAuthority = value.getMethodAnnotation(RequiredAuthority.class);
			if (requiredAuthority == null)
				continue;

			String[] patterns = key.getPatternsCondition().getPatterns().toArray(new String[] {});
			urlRegistry.regexMatchers(patterns).hasAuthority(requiredAuthority.code());
		}

		http.formLogin().and().logout().clearAuthentication(true).invalidateHttpSession(true).logoutUrl("/logout")
				.logoutSuccessUrl("/login").and().httpBasic();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 生成初始数据
		
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
		handlerMethods.forEach((info, method) -> {
			initDataManager.saveModule(method); // 添加模块
			initDataManager.saveAuthority(method); // 添加权限
		});

		Role role = new Role();
		role.setName("超级管理员");
		role.setRemark("超级管理员");
		initDataManager.saveRole(role);

		Admin admin = new Admin();
		admin.setUsername("gdh");
		admin.setPassword(new BCryptPasswordEncoder().encode("gdh"));
		admin.setEnabled(true);

		admin.getRoles().add(role);

		initDataManager.saveAdmin(admin);
	}

}
