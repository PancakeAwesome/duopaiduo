package com.jrzmq.core.utils;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
@Component
public class CacheUtil {
    @Resource
    private CacheManager ehcacheManager;
    
    public Object get(String cacheName, String key) {
        Cache cache = ehcacheManager.getCache(cacheName);
        Element element = cache.get(key);
        return null == element ? null : element.getObjectValue();
    }
    
    public void put(String cacheName, String key, Object value) {
        Cache cache = ehcacheManager.getCache(cacheName);
        Element element = new Element(key, value);
        cache.put(element);
//        cache.flush();
    }
    
    public void remove(String cacheName, String key) {
        Cache cache = ehcacheManager.getCache(cacheName);
        Element element = cache.get(key);
        if(null != element){
            cache.remove(key);
//            cache.flush();
        }
    }
    
}
