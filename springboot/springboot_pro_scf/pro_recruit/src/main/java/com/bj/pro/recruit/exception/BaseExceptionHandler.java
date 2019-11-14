package com.bj.pro.recruit.exception;

import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import utils.ResultUtil;

/**
 * 统一异常处理类
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return ResultUtil.error(e.getMessage());
    }
}
