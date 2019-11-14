package com.bj.study.cloud.springcloud02_consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bj.study.cloud.springcloud02_consumer.feign.HelloRemote;
@RestController
public class ConsumerController {

    @Autowired
    HelloRemote helloRemote;

    @RequestMapping("/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

}
