/** 
 * Copyright 2014 jrzmq All Rights Reserved.
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.db
 * 文件名：DbContextHolder.java 
 * 日期：2014-11-25 10:21
 */
package com.jrzmq.core.db;

/**
 * 线程安全的db.
 * @author wusimai
 *
 */
public class DbContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDbType() {
        String s = (String) contextHolder.get();
        return s;
    }

    public static void clearDbType() {
        contextHolder.remove();
    }
}
