package com.bj.study.cloud.springcloud02_consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "spring-cloud-producer")
public interface HelloRemote {
    @RequestMapping(value = "/")
    public String hello(@RequestParam(value = "name") String name);
}