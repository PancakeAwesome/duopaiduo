/**
 * Copyright 2014 jrzmq All Rights Reserved.
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.exception
 * 文件名：ErrorCodeConstant.java 
 * 日期：2014-11-25 08:58
 */
package com.jrzmq.core.exception;

/**
 * 异常类对应编号.
 * @author wusimai
 */
public class ErrorCodeConstant {
    /**
     * success_code.
     */
    public static final int SUCCESS_CODE = 0;
    
    /**
     * NULL.
     */
    public static final int NULL_CODE = 1;
    
    /**
     * 未知错误编号.
     */
    public static final int UNKNOW_ERROR_CODE = 10001;
    
    /**
     * 检修错误编号.
     */
    public static final int CHECK_ERROR_CODE = 10002;
    
    /**
     * 权限错误编号。
     */
    public static final int VALIDATION_ERROR_CODE = 10003;
    
    /**
     * 参数错误编号。
     */
    public static final int PARAM_ERROR_CODE = 10004;
    
    /**
     * APP 权限认证失败
     */
//    public static final int APP_AUTH_ACCESS = 1000005;
    
    /**
     * 第三方接口成功反馈 为空
     * 
     * 通联支付：21001
     * 连连支付：21002
     * 中金支付：22003
     * 梦网平台：22001
     * 
     */
    public static final String THIRD_SUCCESS_CODE = null;
    
    /**
     * 通联支付反馈
     */
    public static final int THIRD_TONGLIAN_CODE = 21001;
    
    /**
     * 连连支付反馈 
     */
    public static final int THIRD_LIANLIAN_CODE = 21002;
    
    /**
     * 中金支付反馈 
     */
    public static final int THIRD_ZHONGJIN_CODE = 22003;
    
    /**
     * 梦网平台反馈
     */
    public static final int THIRD_MENGWANG_CODE = 22001;

}
