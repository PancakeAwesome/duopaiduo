/**
 * Copyright 2014 jrzmq All Rights Reserved. 
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.web.servlet
 * 文件名：JrzmqDispatcherServlet.java
 * 日期：2015-12-15 16:54:34
 */
package com.jrzmq.core.web.servlet;

import com.jrzmq.core.web.servlet.http.AppAuthHttpServletRequestWrapper;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跳转servlet.
 * @author wusimai
 *
 */
public class JrzmqDispatcherServlet extends DispatcherServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        if (method.equals("GET") || method.endsWith("DELETE")) {
            super.doDispatch(request, response);
        } else {
            if(request.getContentType() != null && request.getContentType().contains("application/json")){
                AppAuthHttpServletRequestWrapper apiAuthRequest = new AppAuthHttpServletRequestWrapper(request);
                super.doDispatch(apiAuthRequest, response);
            } else {
                super.doDispatch(request, response);
            }
        }

    }

}
