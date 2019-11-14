package bj.controller;

import bj.entity.Order;
import bj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/order/{id}")
    public Order queryOrderById(@PathVariable("id") String id){
        return orderService.queryOrderById(id);
    }
}
