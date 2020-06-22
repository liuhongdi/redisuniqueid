package com.redisuniqueid.demo.exception;

import com.redisuniqueid.demo.constant.ResponseCode;
import com.redisuniqueid.demo.util.ServerResponseUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ServerResponseUtil serviceExceptionHandler(ServiceException se) {
        return ServerResponseUtil.error(se.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ServerResponseUtil exceptionHandler(Exception e) {
        //System.out.println(e);
        return ServerResponseUtil.error(ResponseCode.SERVER_ERROR.getMsg());
    }
}