/**
 * Copyright 2014 jrzmq All Rights Reserved. 
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.handler
 * 文件名：IAppAuthHandler.java
 * 日期：2014-12-15 16:45:02
 */
package com.jrzmq.core.handler;

/**
 * 认证处理接口.
 * @author wusimai
 *
 */
public interface IAppAuthHandler {
    /**
     * 认证方法，true：认证成功；false：认证失败.
     * @param body 参数体
     * @param sign 加密后的值
     * @return
     */
    public boolean auth(String body, String sign);
}
