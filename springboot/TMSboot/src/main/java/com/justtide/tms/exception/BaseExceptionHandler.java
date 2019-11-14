package com.justtide.tms.exception;

import com.justtide.tms.common.Result;
import com.justtide.tms.common.StatusCode;
import com.justtide.tms.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e){
        if (!(e instanceof OwnException))
            e.printStackTrace();
        return ResultUtil.error(e.getMessage());
    }
}
