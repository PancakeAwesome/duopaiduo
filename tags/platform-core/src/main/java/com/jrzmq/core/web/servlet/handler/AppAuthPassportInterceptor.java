/**
 * Copyright 2014 jrzmq All Rights Reserved. 
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.web.servlet.handler
 * 文件名：AppAuthPassportInterceptor.java
 * 日期：2014-12-15 16:42:33
 */
package com.jrzmq.core.web.servlet.handler;

import com.jrzmq.core.exception.ErrorCodeConstant;
import com.jrzmq.core.handler.IAppAuthHandler;
import com.jrzmq.core.web.annotation.AppAuthPassport;
import com.jrzmq.core.web.servlet.http.AppAuthHttpServletRequestWrapper;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * App鉴权拦截器
 * @author wusimai
 *
 */
public class AppAuthPassportInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private IAppAuthHandler appAuthHandler;
    
    /**
     * 认证拦截器
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            AppAuthPassport appAuthPassport = ((HandlerMethod) handler).getMethodAnnotation(AppAuthPassport.class);
            // 没有写注解，不做认证
            if (null == appAuthPassport) {
                return super.preHandle(request, response, handler);
            } else {
                return this.auth(request, response, handler);
            }
        } else {
            return super.preHandle(request, response, handler);
        }
    }

    /**
     * 认证，主要是准备好需要认证的参数.
     * @param request 请求
     * @param response 应答
     * @param handler 处理
     * @return true 认证成功 false 认证失败
     * @throws Exception
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    private boolean auth(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception,
            IOException, UnsupportedEncodingException {

        String method = request.getMethod();
        String body = "";
        if (method.equals("GET") || method.endsWith("DELETE")) {
            body = request.getQueryString();
        } else {
            body = ((AppAuthHttpServletRequestWrapper) request).getBody();
        }
        // 如果获得的参数体是null，则返回给页面一个参数不合法的json字符串
        if (body == null || "".equals(body)) {
            this.writeAccessFailedWrongMsg(response);
            return false;
        }
        
        String sign = request.getHeader("sign");
        
        if (sign != null && appAuthHandler.auth(body, sign)) {
            return super.preHandle(request, response, handler);
        } else {
            this.writeAccessFailedWrongMsg(response);
            return false;
        }
    }

    /**
     * 书写错误提示的json字符串，针对认证失败的情况.
     * @param response 应答
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    private void writeAccessFailedWrongMsg(HttpServletResponse response) throws IOException,
            UnsupportedEncodingException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "utf-8"));
        //返回json格式的提示
        pw.println("{\"code\":" + ErrorCodeConstant.VALIDATION_ERROR_CODE
                + ",\"thirdCode\":null,\"msg\":\"网络连接超时，请重新登录\",\"data\":null}");
        pw.flush();
    }   
}
