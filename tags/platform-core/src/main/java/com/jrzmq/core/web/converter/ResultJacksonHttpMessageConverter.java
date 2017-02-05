/** 
 * Copyright 2014 jrzmq All Rights Reserved.
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.web.converter
 * 文件名：ResultJacksonHttpMessageConverter.java 
 * 日期：2014-11-26 17:29
 */
package com.jrzmq.core.web.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonProcessingException;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 将@ResponseBody的值，增加@See Result类的封装.
 * @author wusimai
 *
 */
@SuppressWarnings("rawtypes")
public class ResultJacksonHttpMessageConverter extends MappingJackson2HttpMessageConverter {
    private boolean ignoreUnknownProperties;

   
	@Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {


        byte[] byteArray = null;
        try {

            if (object instanceof Result) {
                if (((Result) object).getData() != null && ((Result) object).getData() instanceof PageList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    // 总共多少条
                    map.put("total", ((PageList<?>) ((Result) object).getData()).getPaginator().getTotalCount());
                    // 当前页
                    map.put("page", ((PageList<?>) ((Result) object).getData()).getPaginator().getPage());
                    map.put("limit", ((PageList<?>) ((Result) object).getData()).getPaginator().getLimit());
                    // 查询出来的分页数据
                    map.put("rows", ((Result) object).getData());
                    // 总共多少页
                    map.put("totalPages", ((PageList<?>) ((Result) object).getData()).getPaginator().getTotalPages());
                    byteArray = this.getObjectMapper().writeValueAsBytes(new Result<Object>(map));
                } else {
                    byteArray = this.getObjectMapper().writeValueAsBytes(object);
                }

            } else if (object instanceof PageList) {
                Map<String, Object> map = new HashMap<String, Object>();
                // 总共多少条
                map.put("total", ((PageList<?>) object).getPaginator().getTotalCount());
                // 当前页
                map.put("page", ((PageList<?>) object).getPaginator().getPage());
                
                map.put("limit", ((PageList<?>) object).getPaginator().getLimit());
                // 查询出来的分页数据
                map.put("rows", object);
                // 总共多少页
                map.put("totalPages", ((PageList<?>) object).getPaginator().getTotalPages());
                byteArray = this.getObjectMapper().writeValueAsBytes(new Result<Object>(map));
            } else {
            	//继承IResultBySelfClass 则不包装Result结果
            	if(object instanceof IResultBySelfClass) {
            		byteArray = this.getObjectMapper().writeValueAsBytes(object);
            	} else {
            		byteArray = this.getObjectMapper().writeValueAsBytes(new Result<Object>(object));
            	}
            }
            
            FileCopyUtils.copy(byteArray, outputMessage.getBody());
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    public boolean isIgnoreUnknownProperties() {
        return this.ignoreUnknownProperties;
    }

    public void setIgnoreUnknownProperties(boolean ignoreUnknownProperties) {
        if (ignoreUnknownProperties == true) {
            this.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

}
