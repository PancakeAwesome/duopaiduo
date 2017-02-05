/** 
 * Copyright 2014 jrzmq All Rights Reserved.
 * 项目名：jrzmq-core
 * 包名：com.jrzmq.core.web.converter
 * 文件名：Result.java 
 * 日期：2014-04-25 17:29
 */
package com.jrzmq.core.web.converter;

import com.jrzmq.core.exception.ErrorCodeConstant;

/**
 * 返回结果包装类.
 * 
 * @author wusimai
 *        
 * @param <T>
 */
public class Result<T>
{
    // private boolean success = true;
    // private int errorCode = ErrorCodeConstant.SUCCESS_CODE;
    private int code = ErrorCodeConstant.SUCCESS_CODE;
    
    private String thirdCode = null;
    
    private String msg = "操作成功";
    
    // private List<String> errors;
    private T data;
    
    public Result(T object)
    {
        if (object == null)
        {
            this.fail(ErrorCodeConstant.NULL_CODE,"没有数据");
        }
        else
        {
            // this.success = true;
            this.code = ErrorCodeConstant.SUCCESS_CODE;
            this.thirdCode = ErrorCodeConstant.THIRD_SUCCESS_CODE;
            this.msg = "操作成功";
            this.data = object;
        }
    }
    
    public void fail()
    {
        this.code = ErrorCodeConstant.UNKNOW_ERROR_CODE;
        // this.success = false;
        this.thirdCode = null;
        this.msg = "操作失败";
        this.data = null;
    }
    
    public void fail(int code)
    {
        // this.success = false;
        this.code = code;
        this.thirdCode = null;
        this.msg = "操作失败";
        this.data = null;
    }
    
    public void fail(int errorCode, String msg)
    {
        // this.success = false;
        if (errorCode < 20000)
        {
            this.code = errorCode;
            this.thirdCode = null;
            this.msg = msg;
            this.data = null;
        }
        else
        {
            this.code = errorCode;
            this.thirdCode = "" + errorCode;
            this.msg = msg;
            this.data = null;
        }
        // errors = new ArrayList<String>();
        // errors.add(msg);
    }
    
    public int getCode()
    {
        return this.code;
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public String getThirdCode()
    {
        return thirdCode;
    }
    
    public void setThirdCode(String thirdCode)
    {
        this.thirdCode = thirdCode;
    }
    
    /**
     * @return the msg
     */
    public String getMsg()
    {
        return this.msg;
    }
    
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    /**
     * @return the data
     */
    public T getData()
    {
        return this.data;
    }
    
    /**
     * @param data the data to set
     */
    public void setData(T data)
    {
        this.data = data;
        if (data != null)
        {
            // this.success = true;
            this.code = ErrorCodeConstant.SUCCESS_CODE;
            this.thirdCode = ErrorCodeConstant.THIRD_SUCCESS_CODE;
            this.msg = "操作成功";
        }
    }
    
    // public List<String> getErrors() {
    // return errors;
    // }
    //
    // public void setErrors(List<String> errors) {
    // this.errors = errors;
    // }
    
}
