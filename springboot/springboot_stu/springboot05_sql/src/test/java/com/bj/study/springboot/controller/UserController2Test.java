package com.bj.study.springboot.controller;

import com.bj.study.springboot.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserController2Test {

    @Autowired
    private UserController2 controller2;
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setId("test01");
        user.setUsercode("test01");
        user.setUsername("controller测试01");
        user.setPassword("abcdefg");
        user.setLocked("1");
        user.setSalt("abcdefg");
        controller2.save(user);
        System.out.println("-------------保存成功-----------");
        Map map = controller2.get("test01");
        System.out.println( map.get("data"));
    }

    @Test
    public void get() throws Exception {
        Map map = controller2.get("test01");
        System.out.println( map.get("data"));
    }

    @Test
    public void update() throws Exception {
        User user = new User();
        user.setId("test01");
        user.setUsercode("test01");
        user.setUsername("controller测试01");
        user.setPassword("abcdefg");
        user.setLocked("0");
        user.setSalt("abcdefg");
        controller2.update(user);
        System.out.println("-------------更新成功-----------");
        Map map = controller2.get("test01");
        System.out.println( map.get("data"));
    }

    @Test
    public void delete() throws Exception {
        controller2.delete("test01");
        System.out.println("-------------删除成功-----------");
        Map map = controller2.get("test01");
        System.out.println( map.get("data"));
    }

}