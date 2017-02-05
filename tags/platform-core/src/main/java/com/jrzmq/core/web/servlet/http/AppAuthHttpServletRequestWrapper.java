/**
 * Copyright 2014 jrzmq All Rights Reserved. 
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.web.servlet.http
 * 文件名：AppAuthHttpServletRequestWrapper.java
 * 日期：2014-12-15 16:53:44
 */
package com.jrzmq.core.web.servlet.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 包装请求，对它做了一些处理.
 * @author wusimai
 *
 */
public class AppAuthHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final String body;

    public AppAuthHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder buffer = new StringBuilder();

        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        this.body = buffer.toString();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.body.getBytes("UTF-8"));

        ServletInputStream inputStream = new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
				
			}
        };

        return inputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

    /**
     * @return the body
     */
    public String getBody() {
        return this.body;
    }

}
