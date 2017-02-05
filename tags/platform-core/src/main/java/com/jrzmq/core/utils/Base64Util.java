package com.jrzmq.core.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base64工具类
 * @author wusimai
 *
 */
public class Base64Util {
    private final static Logger  LOGGER = LoggerFactory.getLogger(Base64Util.class);
    
    /** 
     * BASE64解密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String decodeBase64(String key) {
        
        
        if(key==null || key.length()<1){  
            return "";  
        } else {
            try {
                return new String(Base64.decodeBase64(key.getBytes("UTF-8")), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("base64解码出错，原字符串为:{}", key, e);
                return "";
            }
        }
       
    }  
      
    /** 
     * BASE64加密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encodeBase64(String key) {  
        if(key==null || key.length()<1){  
            return "";  
        }  
        try {
            return new String(Base64.encodeBase64(key.getBytes("UTF-8")), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("base64加密出错，原字符串为:{}", key, e);
            return "";
        }
    }  
}
