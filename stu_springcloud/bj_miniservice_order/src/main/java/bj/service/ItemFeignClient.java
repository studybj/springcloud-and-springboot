package bj.service;

import bj.entity.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "bj-miniservice-item") //声明这是一个feign的客户端，并且指明服务id
public interface ItemFeignClient {
    @GetMapping("/item/{id}")     //此处可使用feign原生注解或SpringMVC注解
    public Item queryItemById(@PathVariable("id") Long id);
}
