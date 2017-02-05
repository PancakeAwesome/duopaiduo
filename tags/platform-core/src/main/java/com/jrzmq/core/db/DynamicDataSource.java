/** 
 * Copyright 2014 laizi All Rights Reserved.
 * 项目名：laizi-core
 * 包名：com.laizi.core.db
 * 文件名：DynamicDataSource.java 
 * 日期：2014-12-25 10:19
 */ 
package com.jrzmq.core.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源.
 * @author wusimai
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource{

    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDbType();
    }

}
