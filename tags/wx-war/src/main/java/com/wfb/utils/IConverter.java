package com.wfb.utils;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface IConverter<T> {
    void save(T entity);
    
    void update(T entity);
    
    void remove(Object entityId);
    
    List<T> getAll();
    
    List<T> get(T entity);
    
    PageList<T> getPageList(T entity, PageBounds pageBounds);
    
    T getById(Object entityId);
}
