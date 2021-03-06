package com.justtide.tms.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //生成无参数构造方法
@AllArgsConstructor //生成全部参数的构造方法
public class Result {
    /**是否成功*/
    private boolean flag;
    /**返回码*/
    private Integer code;
    /**返回信息*/
    private String msg;
    /**返回数据*/
    private Object data;

    public Result(boolean flag, Integer code, String msg) {
        this.flag = flag;
        this.code = code;
        this.msg = msg;
    }
}
