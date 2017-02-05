package cn.digirun.core.api.security;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.digirun.core.api.Constants;
import cn.digirun.core.api.exception.ApiException;
import cn.digirun.core.manager.Ret;

/** 
 * @ClassName: SignInterceptor 
 * @Description: 签名拦截器
 * @author 管东海
 *  
 */
public class SignInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = Logger.getLogger(SignInterceptor.class);

	public String generateSign(String arg, String secretKey) {
		return DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(arg.getBytes()) + secretKey).getBytes());
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!handler.getClass().isAssignableFrom(HandlerMethod.class))
			return true;

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		if (handlerMethod.getMethodAnnotation(NeedSign.class) == null)
			return true;

		if (handlerMethod.getMethodAnnotation(NeedToken.class) == null)
			return true;
		
		String token = request.getHeader(Constants.HEADER_TOKEN);
		String sign = request.getHeader(Constants.HEADER_SIGN);

		if (StringUtils.isEmpty(token))
			throw new ApiException(Ret.tokenFail("无法解析令牌:Null"));
		if (StringUtils.isEmpty(sign))
			throw new ApiException(Ret.signFail("无法解析签名:Null"));

		String generateSign = "";
		StringBuffer body = new StringBuffer();
		RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod().toUpperCase());
		switch (requestMethod) {
		case GET:
		case HEAD:
			// md5(md5(requestUrl+?+queryString)+token)
			generateSign = generateSign(request.getRequestURL() + "?" + request.getQueryString(), token);
			break;
		case POST:
		case PUT:
		case DELETE:
		case TRACE:
		case PATCH:
		case OPTIONS:
			if (request instanceof StandardMultipartHttpServletRequest) {
				StandardMultipartHttpServletRequest mRequest = (StandardMultipartHttpServletRequest) request;
				Iterator<String> fileNames = mRequest.getFileNames();

				while (fileNames.hasNext()) {
					String name = fileNames.next();
					body.append(mRequest.getFile(name).getOriginalFilename()).append(",");
				}
				body.deleteCharAt(body.length() - 1);

			} else {
				List<String> bodyLines = IOUtils.readLines(request.getInputStream(), "UTF-8");
				for (String line : bodyLines)
					body.append(line);
				request.setAttribute(Constants.BODY_STRING, body.toString());
			}
			// md5(md5(body)+token)
			generateSign = generateSign(body.toString(), token);

			break;
		}
		LOGGER.info("header sign ：" + sign);
		LOGGER.info("sign :" + generateSign);
		if (!sign.equals(generateSign))
			throw new ApiException(Ret.signFail("签名匹配失败"));
		return true;

	}

}
