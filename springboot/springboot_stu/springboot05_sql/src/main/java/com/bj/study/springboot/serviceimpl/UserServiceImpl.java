package com.bj.study.springboot.serviceimpl;

import com.bj.study.springboot.dao.UserDao;
import com.bj.study.springboot.dao.UserRepository;
import com.bj.study.springboot.daoimpl.RedisDaoImpl;
import com.bj.study.springboot.entity.JpaUser;
import com.bj.study.springboot.entity.User;
import com.bj.study.springboot.dao.UserXmlMapper;
import com.bj.study.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{
        @Autowired
        private UserDao userDao;
        @Autowired
        private UserRepository userRepository;
       /* @Autowired
       此处引用com.bj.study.springboot.mapper.UserMapper
        private UserMapper userMapper;*/
        @Autowired
        private UserXmlMapper userMapper;
        @Autowired
        private RedisDaoImpl redisDao;
        private Logger logger = LoggerFactory.getLogger(this.getClass());
        @Override
        public void create(User user) {
            logger.info("-------------create start-------------");
            //userDao.create(user);
            redisDao.set("user1",user.toString());
            logger.info("-------------create start-------------");
        }
        @Override
        public void deleteByName(String name) {
            logger.info("-------------deleteByName start-------------");
            userDao.deleteByName(name);
            logger.info("-------------deleteByName start-------------");
        }
        @Override
        public List<User> getAllUsers(){
            logger.info("-------------UserServiceImpl getAllUsers start-------------");
            List<User> list = userDao.getAllUsers();
            logger.info("-------------UserServiceImpl getAllUsers start-------------");
            return list;
        }
        @Override
        public List<JpaUser> getAllUsers2(){
            logger.info("-------------jpa UserServiceImpl getAllUsers2 start-------------");
            List<JpaUser> list = userRepository.findAll();
            logger.info("-------------jpa UserServiceImpl getAllUsers2 start-------------");
            return list;
        }
        @Override
        public List<User> getAllUsers3(){
            logger.info("-------------mybatis UserServiceImpl getAllUsers3 start-------------");
            //List<User> list = userMapper.getAllUsers3();
            logger.info("-------------mybatis UserServiceImpl getAllUsers3 end-------------");
            return null;
        }
        @Override
        public List<User> getAllUsers4(){
            logger.info("-------------mybatis UserServiceImpl xml getAllUsers4 start-------------");
            List<User> list = userMapper.getAllUsers4();
            logger.info("-------------mybatis UserServiceImpl xml getAllUsers4 end-------------");
            return list;
        }
        @Override
        public Integer getAllUserCount() {
            logger.info("-------------getAllUserCount start-------------");
            Integer count = userDao.getAllUserCount();
            logger.info("-------------getAllUserCount start-------------");
            return count;
        }
        @Override
        public void deleteAllUsers() {
            logger.info("-------------deleteAllUsers start-------------");
            userDao.deleteAllUsers();
            logger.info("-------------deleteAllUsers start-------------");
        }
        @Override
        public String getAllUsersByRedis(String key){
            logger.info("-------------getAllUsersByRedis start-------------");
            String users = redisDao.get(key);
            logger.info("-------------getAllUsersByRedis start-------------");
            return users;
        }
}
