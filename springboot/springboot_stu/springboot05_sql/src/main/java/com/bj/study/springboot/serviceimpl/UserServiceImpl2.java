package com.bj.study.springboot.serviceimpl;

import com.bj.study.springboot.dao.UserXmlMapper;
import com.bj.study.springboot.entity.User;
import com.bj.study.springboot.service.UserService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
@CacheConfig(cacheNames = "user")
@Service("userService2")
public class UserServiceImpl2 implements UserService2{

    @Autowired
    private UserXmlMapper userMapper;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @CachePut(key = "#user.id")
    public User save(User user) {
        logger.info("-------------save start-------------"+"保存 id=" + user.getId() + " 的数据");
        this.userMapper.create(user);
        return user;
    }
    @CachePut(key = "#user.id")
    public User update(User user) {
        logger.info("-------------update start-------------"+"修改 id=" + user.getId() + " 的数据");
        this.userMapper.update(user);
        return user;
    }
    @Cacheable(key = "#id")
    public User getUserById(String id) {
        logger.info("-------------getUserById start-------------"+"获取 id=" + id + " 的数据");
        User user = this.userMapper.getById(id);
        return user;
    }
    @CacheEvict(key = "#id")
    public void delete(String id) {
        logger.info("-------------delete start-------------"+"删除 id=" + id + " 的数据");
        this.userMapper.deleteById(id);
    }
}
