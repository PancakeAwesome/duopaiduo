package cn.digirun.core.api.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

import cn.digirun.core.api.security.ApiBodyHandlerMethodArgumentResolver;
import cn.digirun.core.api.security.SessionTokenHandlerMethodArgumentResolver;
import cn.digirun.core.api.security.SignInterceptor;
import cn.digirun.core.api.security.TokenCacheManager;
import cn.digirun.core.api.security.TokenInterceptor;

/** 
 * @ClassName: ApiConfiguration 
 * @Description: Api配置类
 * @author 管东海
 *  
 */
@Configuration
public class ApiConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public TokenCacheManager TokenCacheManager() {
		return new TokenCacheManager(); // 身份令牌缓存管理
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new ApiBodyHandlerMethodArgumentResolver()); // 请求体 参数解析
		argumentResolvers.add(new SessionTokenHandlerMethodArgumentResolver()); // 当前会话令牌参数解析
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenInterceptor()); // 令牌拦截器
		registry.addInterceptor(new SignInterceptor()); // 签名拦截器
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		FastJsonHttpMessageConverter4 jsonConverter = new FastJsonHttpMessageConverter4(); //这里不使用fasterxml的json解析方案；采用fastjson
		jsonConverter.getFastJsonConfig().setSerializerFeatures(SerializerFeature.WriteMapNullValue);
		converters.add(jsonConverter);
	}
}
