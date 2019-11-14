package com.bj.demo.system.controller;

import com.bj.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

}
