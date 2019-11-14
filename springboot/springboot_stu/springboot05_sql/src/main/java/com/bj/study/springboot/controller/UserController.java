package com.bj.study.springboot.controller;

import com.bj.study.springboot.entity.JpaUser;
import com.bj.study.springboot.entity.User;
import com.bj.study.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("")
    public String userinfo(){
        return "userinfo";
    }

    @RequestMapping("/list")
    public String list(){
        logger.info("-------------list start-------------");
        List<User> list = userService.getAllUsers();
        logger.info("-------------list end-------------");
        return list.toString();
    }

    @RequestMapping("/jpalist")
    public String jpalist(){
        logger.info("-------------list start-------------");
        List<JpaUser> list = userService.getAllUsers2();
        logger.info("-------------list end-------------");
        return list.toString();
    }
    @RequestMapping("/mybatislist")
    public String mybatislist(){
        logger.info("-------------userController mybatislist start-------------");
        List<User> list = userService.getAllUsers3();
        logger.info("-------------userController mybatislist end-------------");
        return list.toString();
    }
    @RequestMapping("/mybatisxmllist")
    public String mybatisxmllist(){
        logger.info("-------------userController mybatisxmllist start-------------");
        List<User> list = userService.getAllUsers4();
        logger.info("-------------userController mybatisxmllist end-------------");
        return list.toString();
    }
    @RequestMapping("/redisCreate")
    public void redisCreate(){
        logger.info("-------------userController redisCreate start-------------");
        User user = new User();
        user.setUsercode("123456");
        user.setUsername("张三");
        user.setPassword("avdsd");
        user.setSalt("12321");
        userService.create(user);
        logger.info("-------------userController redisCreate end-------------");
    }
    @RequestMapping("/redisGet")
    public String redisGet(){
        logger.info("-------------userController redisGet start-------------");
        String str = userService.getAllUsersByRedis("user1");
        logger.info("-------------userController redisGet end-------------");
        return str;
    }

}
