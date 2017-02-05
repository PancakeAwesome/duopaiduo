package com.jrzmq.core.utils;

import com.jrzmq.core.exception.ErrorCodeConstant;
import com.jrzmq.core.web.converter.Result;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorMsgUtil {
    private static List<String> msgConverter(List<ObjectError> errors) {
        List<String> errorMsgList = new ArrayList<String>();
        for(ObjectError error : errors){
            errorMsgList.add(error.getDefaultMessage());
        }
        return errorMsgList;
    }

    public static <T> Result<T> invalidResult(BindingResult bindingResult) {
        Result<T> result = new Result<T>(null);
        result.setMsg("数据校验错误 |"+ msgConverter(bindingResult.getAllErrors()));
        result.setCode(ErrorCodeConstant.CHECK_ERROR_CODE);
//        result.setSuccess(false);
        result.setThirdCode(null);
//        result.setErrors(msgConverter(bindingResult.getAllErrors()));
        return result;
    }
}
