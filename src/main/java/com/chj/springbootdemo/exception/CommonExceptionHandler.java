package com.chj.springbootdemo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chehaojie
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BaseException.class)
    public Map<String,Object> handleBaseException(BaseException e){
        Map<String,Object> result = new HashMap<>();
        result.put("respCode", e.getCode());
        result.put("respMsg", e.getMsg());
        //正常开发中，可创建一个统一响应实体，如CommonResp
        return result;
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Map<String,Object> handleRuntimeException(RuntimeException e){
        Map<String,Object> result = new HashMap<>();
        result.put("respCode", 500);
        result.put("respMsg", e.getMessage());
        //正常开发中，可创建一个统一响应实体，如CommonResp
        return result;
    }
}
