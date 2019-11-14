package com.bj.eat.vo;

import lombok.Data;

/**
 * Http请求返回的对象
 */
@Data
public class ResultVo<T> {
    /**错误码*/
    private Integer code;
    /**返回的信息*/
    private String msg;
    /** 记录总条数*/
    private Long count;
    /**具体内容*/
    private T data;

}
