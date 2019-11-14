package com.bj.pro.qa.client;

import com.bj.pro.qa.client.clientimpl.BaseJpaClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "base-jpa", fallback = BaseJpaClientImpl.class)
public interface BaseJpaClient {
    //将需要的方法声明到该接口中
    @GetMapping("/label/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId);
}
