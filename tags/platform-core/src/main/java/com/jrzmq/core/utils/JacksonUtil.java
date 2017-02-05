/**
 * Copyright 2014 jrzmq All Rights Reserved.
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.utils
 * 文件名：JacksonUtil.java 
 * 日期：2014-11-27 15:28
 */
package com.jrzmq.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * json对象操作类
 * @author wusimai
 *
 */
public abstract class JacksonUtil {
    private final static Logger  LOGGER = LoggerFactory.getLogger(JacksonUtil.class);
    
    // can reuse, share globally
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper(); 
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        mapper.disable(new DeserializationConfig.Feature[] { DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES });
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        mapper.disable(new SerializationConfig.Feature[] { SerializationConfig.Feature.WRITE_NULL_MAP_VALUES });
    }
    
    /**
     * 对象转成json字符串.
     * @param o
     * @return
     */
    public static final String obj2Str(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonParseException e) {
            LOGGER.error("对象转json出错", e);
        } catch (JsonMappingException e) {
            LOGGER.error("对象转json出错", e);
        } catch (IOException e) {
            LOGGER.error("对象转json出错", e);
        }
        return null; 
    }
    
    public static final void writeObj(OutputStream out, Object value) {
        try {
            mapper.writeValue(out, value);
        } catch (JsonParseException e) {
            LOGGER.error("writeObj出错", e);
        } catch (JsonMappingException e) {
            LOGGER.error("writeObj出错", e);
        } catch (IOException e) {
            LOGGER.error("writeObj出错", e);
        }
    }
    
    public static final <T> T str2Obj(String s, Class<T> valueType) {
        try {
            return mapper.readValue(s, valueType);
        } catch (JsonParseException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        } catch (JsonMappingException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        } catch (IOException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        }
        return null; 
    }
    
    public static final <T> T readObj(InputStream in, Class<T> valueType) {
        try {
            return mapper.readValue(in, valueType);
        } catch (JsonParseException e) {
            LOGGER.error("json转对象出错", e);
        } catch (JsonMappingException e) {
            LOGGER.error("json转对象出错", e);
        } catch (IOException e) {
            LOGGER.error("json转对象出错", e);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> T readObj(InputStream in, JavaType valueType) {
        try {
            return (T)mapper.readValue(in, valueType);
        } catch (JsonParseException e) {
            LOGGER.error("json转对象出错", e);
        } catch (JsonMappingException e) {
            LOGGER.error("json转对象出错", e);
        } catch (IOException e) {
            LOGGER.error("json转对象出错", e);
        }
        return null;
    }
    
    public static final <T> T str2Obj(String s, Class<?> collectionClass, Class<?>... elementClasses){
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return mapper.readValue(s, javaType);
        } catch (JsonParseException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        } catch (JsonMappingException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        } catch (IOException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        }
        return null; 
        
    }
    
    /**
     * 处理类似T<T<T>>的值.
     * @param s
     * @param parametrized
     * @param parameterClasses1
     * @param parameterClasses2
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static final <T> T str2SimpleObj(String s, Class<?> parametrized, Class parameterClasses1, Class parameterClasses2){
        JavaType javaType1 = mapper.getTypeFactory().constructParametricType(parameterClasses1, parameterClasses2);
        JavaType javaType = mapper.getTypeFactory().constructParametricType(parametrized, javaType1);
        try {
            return mapper.readValue(s, javaType);
        } catch (JsonParseException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        } catch (JsonMappingException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        } catch (IOException e) {
            LOGGER.error("json转对象出错，字符串为:{}", s, e);
        }
        return null; 
        
    }
    
}
