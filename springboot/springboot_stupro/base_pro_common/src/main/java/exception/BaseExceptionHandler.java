package exception;

import entity.Result;
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
    @ExceptionHandler(value = ProUserException.class)
    public Result error(ProUserException e){
        e.printStackTrace();
        return ResultUtil.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return ResultUtil.error(e.getMessage());
    }
}
