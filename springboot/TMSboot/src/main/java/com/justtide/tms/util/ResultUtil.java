package com.justtide.tms.util;

import com.justtide.tms.common.PageResult;
import com.justtide.tms.common.Result;
import com.justtide.tms.common.StatusCode;

import java.util.List;

public class ResultUtil {

    public static Result success(String msg, Object o){
        Result resultVo = new Result(true, StatusCode.SUCCESS, msg);
        resultVo.setData(o);
        return resultVo;
    }

    public static Result success(Object o){
        return success("执行成功", o);
    }

    public static Result success(String msg){
        return success(msg, null);
    }

    public static Result success(){
        return success(null);
    }

    public static Result error(String message){
        Result resultVo = new Result(false, StatusCode.ERROR, message);
        return resultVo;
    }
    public static Result error(Integer code, String message){
        if (code == null){
            return error(message);
        }
        return new Result(false, code, message);
    }
    public static <T> PageResult<T> pageData(Long total, List<T> list){
        PageResult pageResult = new PageResult(total, list);
        return pageResult;
    }
}
