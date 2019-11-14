package com.bj.study.springboot.daoimpl;

import com.bj.study.springboot.dao.UserDao;
import com.bj.study.springboot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void create(User user) {
        jdbcTemplate.update("insert into sys_USER(id, usercode,username,password,salt,locked) values(?, ?,?,?,?,?)",
                user.getId(), user.getUsercode(),user.getUsername(),user.getPassword(),user.getSalt(),user.getLocked());
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from sys_USER where username = ?", name);
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("-------------UserDaoImpl getAllUsers start-------------");
        List<Map<String,Object>> list = jdbcTemplate.queryForList("SELECT * FROM SYS_USER ");
        List<User> userlist = new ArrayList<User>();
        for (Map<String,Object> map : list){
            User user = new User();
            user.setId((String) map.get("id"));
            user.setUsercode((String) map.get("usercode"));
            user.setUsername((String) map.get("username"));
            user.setPassword((String) map.get("password"));
            user.setSalt((String) map.get("salt"));
            user.setLocked((String) map.get("locked"));

            userlist.add(user);
        }
        logger.info("-------------UserDaoImpl getAllUsers end-------------");
        return  userlist;
    }

    @Override
    public Integer getAllUserCount() {
        return jdbcTemplate.queryForObject("select count(1) from sys_USER", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from sys_USER");
    }
}
