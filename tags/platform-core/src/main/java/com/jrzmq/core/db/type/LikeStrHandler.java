/** 
 * Copyright 2014 jrzmq All Rights Reserved.
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.db
 * 文件名：LikeStrHandler.java 
 * 日期：2014-11-25 10:21
 */
package com.jrzmq.core.db.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * mybatis的模糊查询，特殊字符处理.
 * @author wusimai
 *
 */
public class LikeStrHandler extends BaseTypeHandler<String>{

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String keyword, JdbcType jdbcType)
        throws SQLException {
        if (keyword != null) {
            if (keyword.contains("%") || keyword.contains("_") || keyword.contains("[") 
                    || keyword.contains("]") || keyword.contains("^")) {  
                keyword = keyword.replaceAll("\\\\", "\\\\\\\\")  
                                 .replaceAll("\\%", "\\\\%")  
                                 .replaceAll("\\_", "\\\\_")
                                 .replaceAll("\\[", "\\\\[")
                                 .replaceAll("\\]", "\\\\]")
                                 .replaceAll("\\^", "\\\\^");
            } 
        }
        ps.setString(i, keyword);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName)
        throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex)
        throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
        throws SQLException {
        return cs.getString(columnIndex);
    }
}
