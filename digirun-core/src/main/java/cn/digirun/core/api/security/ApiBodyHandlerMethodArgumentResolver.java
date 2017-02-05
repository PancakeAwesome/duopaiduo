package cn.digirun.core.api.security;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;

import cn.digirun.core.api.Constants;
import cn.digirun.core.api.exception.ApiException;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: ApiBodyHandlerMethodArgumentResolver 
 * @Description: 请求体参数解析
 * @author 管东海
 *  
 */
public class ApiBodyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		ApiBody formBody = parameter.getParameterAnnotation(ApiBody.class);

		if (formBody != null)
			return true;
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		Object bodyObj = request.getAttribute(Constants.BODY_STRING);
		String body = "";
		if (bodyObj == null) {
			List<String> bodyLines = IOUtils.readLines(request.getInputStream(), "UTF-8");
			for (String line : bodyLines)
				body += line;
		} else {
			body = bodyObj.toString();
		}

		String decodeBody = new String(Base64.getDecoder().decode(body));
		if(StringUtils.isEmpty(decodeBody))
			throw new ApiException(Ret.bodyFail("无法解析body:Null"));
		Object returnObj = JSON.parseObject(decodeBody, parameter.getParameterType());

		WebDataBinder binder = binderFactory.createBinder(webRequest, returnObj, returnObj.getClass().toString());

		BindException bindException = new BindException(binder.getBindingResult());
		binder.getValidator().validate(returnObj, new BindException(bindException));

		if (binder.getBindingResult().hasErrors())
			throw bindException;
		return returnObj;
	}

}
