package com.bj.demo.system.dao;

import com.bj.demo.system.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insertUser() throws Exception {

    }

    @Test
    public void seletById() throws Exception {
        User user = userMapper.seletById("lisi");
        System.out.println(user.toString());
    }

    @Test
    public void seletByName() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

}