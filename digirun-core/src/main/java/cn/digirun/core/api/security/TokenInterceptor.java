package cn.digirun.core.api.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.digirun.core.api.Constants;
import cn.digirun.core.api.exception.ApiException;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: TokenInterceptor 
 * @Description: 身份令牌拦截器
 * @author 管东海
 *  
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		if (!handler.getClass().isAssignableFrom(HandlerMethod.class))
			return true;

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		if (handlerMethod.getMethodAnnotation(NeedToken.class) == null)
			return true;

		String token = request.getHeader(Constants.HEADER_TOKEN);

		if (StringUtils.isEmpty(token))
			throw new ApiException(Ret.tokenFail("无法解析令牌:Null"));

		ApplicationContext context = WebApplicationContextUtils
				.getRequiredWebApplicationContext(request.getServletContext());

		TokenCacheManager tokenCacheMgr = context.getBean(TokenCacheManager.class);

		TokenResponse tokenResp = tokenCacheMgr.get(token);

		if (tokenResp == null)
			throw new ApiException(Ret.tokenFail("不能找到匹配的令牌信息:Null"));

		Date nowDate = new Date();

		if (tokenResp.getExpireTime().compareTo(nowDate) <= 0){
			tokenCacheMgr.del(tokenResp);
			throw new ApiException(Ret.tokenFail("令牌已过期:Null"));
			
		}

		tokenResp.setLastAccessTime(nowDate);
		tokenCacheMgr.put(tokenResp);
		return true;

	}
}
