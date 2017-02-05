package com.wfb.utils;

import java.util.PropertyResourceBundle;

/**
 * 配置文件公用属性
 * @author zhouf
 */
public interface Constants
{
    /**
     * IM用户推送注册
     */
    // API_HTTP_SCHEMA
    public static String API_HTTP_SCHEMA = "https";
    
    // API_SERVER_HOST
    public static String API_SERVER_HOST = PropertyResourceBundle.getBundle("config").getString("API_SERVER_HOST");
    
    // APPKEY
    public static String APPKEY = PropertyResourceBundle.getBundle("config").getString("APPKEY");
    
    // APP_CLIENT_ID
    public static String APP_CLIENT_ID = PropertyResourceBundle.getBundle("config").getString("APP_CLIENT_ID");
    
    // APP_CLIENT_SECRET
    public static String APP_CLIENT_SECRET = PropertyResourceBundle.getBundle("config").getString("APP_CLIENT_SECRET");
    
    // DEFAULT_PASSWORD
    public static String DEFAULT_PASSWORD = "123456";
    
    public static String USER_ROLE_APPADMIN = "appAdmin";
    
    /**
     * 实名认证
     */
    // 商户号
    public static String MERCH_ID = PropertyResourceBundle.getBundle("config").getString("MERCH_ID");
    
    // 业务类型
    public static String PRODUCT_ID = PropertyResourceBundle.getBundle("config").getString("PRODUCT_ID");
    
    // 加密类型
    public static String SIGN_TYPE = PropertyResourceBundle.getBundle("config").getString("SIGN_TYPE");
    
    // 订单信息
    public static String OUTORDER_NO = PropertyResourceBundle.getBundle("config").getString("OUTORDER_NO");
    
    // 商户MD5密钥
    public static String SIGN_KEY = PropertyResourceBundle.getBundle("config").getString("SIGN_KEY");
    
    /**
     * 阿里云 OSS
     */
    public static String ACCESSKEY_ID = PropertyResourceBundle.getBundle("config").getString("ACCESSKEY_ID");
    
    public static String ACCESSKEY_SECRET = PropertyResourceBundle.getBundle("config").getString("ACCESSKEY_SECRET");
    
    public static String ENDPOINT = PropertyResourceBundle.getBundle("config").getString("ENDPOINT");
    
    public static String BUCKETNAME = PropertyResourceBundle.getBundle("config").getString("BUCKETNAME");
    
    public static String DOWNLOADURL = PropertyResourceBundle.getBundle("config").getString("DOWNLOADURL");
    
    /**
     * 天天基金
     */
    public static String PARTNERID = PropertyResourceBundle.getBundle("config").getString("PARTNERID");
    
    public static String PARTNERIP = PropertyResourceBundle.getBundle("config").getString("PARTNERIP");
    
    public static String PARTNERVERSION = PropertyResourceBundle.getBundle("config").getString("PARTNERVERSION");
    
    public static String SERVERVERSION = PropertyResourceBundle.getBundle("config").getString("SERVERVERSION");
    
    public static String SERV_URL = PropertyResourceBundle.getBundle("config").getString("SERV_URL");
    
    /**
     * 个推
     * 
     */
    public static String GTAPPID = PropertyResourceBundle.getBundle("config").getString("GTAPPID");
    
    public static String GTAPPKEY = PropertyResourceBundle.getBundle("config").getString("GTAPPKEY");
    
    public static String GTAPPSECRET = PropertyResourceBundle.getBundle("config").getString("GTAPPSECRET");
    
    public static String GTMASTERSECRET = PropertyResourceBundle.getBundle("config").getString("GTMASTERSECRET");
  
    public static String GTLOGO = PropertyResourceBundle.getBundle("config").getString("GTLOGO");
    
    public static String GTHOST = PropertyResourceBundle.getBundle("config").getString("GTHOST");

}
