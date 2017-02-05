/** 
 * Copyright 2014 laizi All Rights Reserved.
 * 项目名：laizi-core
 * 包名：com.laizi.core.db
 * 文件名：MySQLDialect.java 
 * 日期：2014-11-25 10:21
 */
package com.jrzmq.core.db;


import com.github.miemiedev.mybatis.paginator.dialect.Dialect;

/**
 * mysql分页.
 * @author wusimai
 */
public class MySQLDialect extends Dialect {
    @Override
    public boolean supportsLimitOffset() {
        return true;
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        if (offset > 0) {
            return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
        } else {
            return sql + " limit " + limitPlaceholder;
        }
    }

    /**
     * 计算行数.
     */
    @Override
    public String getCountString(String sql) {
        StringBuffer sqlSb = new StringBuffer(sql.toLowerCase());
        if (sqlSb.indexOf("group by") > 0) {
            return "select count(1) from (" + sql + ") tmp_count";
        } else {
            int index = sqlSb.indexOf("from ");
            sqlSb.replace(0, index, "select count(1) \n");
        }
        return sqlSb.toString();
    }
}
