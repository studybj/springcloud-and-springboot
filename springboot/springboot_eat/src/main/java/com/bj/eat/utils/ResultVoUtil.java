package com.bj.eat.utils;

import com.bj.eat.vo.ResultVo;

import java.util.Map;

public class ResultVoUtil {
    public static ResultVo success(Object o){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(o);
        return resultVo;
    }
    public static ResultVo success(){
        return success(null);
    }
    public static ResultVo error(Integer code,String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(message);
        return resultVo;
    }

    public static ResultVo success(Object o, long count){
        ResultVo resultVo = success(o);
        resultVo.setCount(count);
        return resultVo;
    }
    public static ResultVo success(long count){
        ResultVo resultVo = success();
        resultVo.setCount(count);
        return resultVo;
    }
    public static ResultVo result(Map<String, String> map){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(Integer.parseInt(map.get("errcode")));
        resultVo.setMsg(map.get("errmsg"));
        resultVo.setData(null);
        return resultVo;
    }
}
