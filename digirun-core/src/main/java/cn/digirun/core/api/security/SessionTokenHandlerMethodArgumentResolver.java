package cn.digirun.core.api.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import cn.digirun.core.api.Constants;

/** 
 * @ClassName: SessionTokenHandlerMethodArgumentResolver 
 * @Description: 当前会话令牌参数解析
 * @author 管东海
 *  
 */
public class SessionTokenHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(SessionToken.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		TokenCacheManager tokenCacheMgr = WebApplicationContextUtils
				.getRequiredWebApplicationContext(
						webRequest.getNativeRequest(HttpServletRequest.class).getServletContext())
				.getBean(TokenCacheManager.class);
		return tokenCacheMgr.get(webRequest.getHeader(Constants.HEADER_TOKEN));
	}

}
