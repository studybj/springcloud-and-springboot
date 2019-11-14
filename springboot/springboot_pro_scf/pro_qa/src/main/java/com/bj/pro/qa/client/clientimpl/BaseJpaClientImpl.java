package com.bj.pro.qa.client.clientimpl;

import com.bj.pro.qa.client.BaseJpaClient;
import entity.Result;
import org.springframework.stereotype.Component;
import utils.ResultUtil;

@Component
public class BaseJpaClientImpl implements BaseJpaClient {

    @Override
    public Result findById(String labelId) {
        return ResultUtil.error("暂时无法提供服务");
    }
}
