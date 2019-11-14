package com.bj.study.springboot.controller;

import com.bj.study.springboot.entity.User;
import com.bj.study.springboot.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user2")
@ResponseBody
public class UserController2 {
    @Autowired
    private UserService2 userService2;
    @RequestMapping("save")
    public Map<String,Object> save(User user) {
        this.userService2.save(user);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", "200");
        map.put("msg", "保存成功");
        return map;
    }
    @RequestMapping("get/{id}")
    public Map<String,Object> get(@PathVariable("id") String id) {
        User user = this.userService2.getUserById(id);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", "200");
        map.put("msg", "获取成功");
        map.put("data", user);
        return map;
    }
    @RequestMapping("update")
    public Map<String,Object> update(User user) {
        this.userService2.update(user);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", "200");
        map.put("msg", "修改成功");
        return map;
    }
    @RequestMapping("delete/{id}")
    public Map<String,Object> delete(@PathVariable("id") String id) {
        this.userService2.delete(id);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code", "200");
        map.put("msg", "删除成功");
        return map;
    }
}
