package com.bj.stu.service;


import com.bj.stu.entity.User;
import com.bj.stu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;
    //-----------------------------------基本操作--------------------------------------------------
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(long id){
        User user = (User) redisTemplate.opsForValue().get("user_" + id);
        if (user == null){
            user = userRepository.findById(id);
            redisTemplate.opsForValue().set("user_" + id, user);
        }
        return user;
    }
    public int count(){
        return userRepository.count();
    }
    public void save(User user){
        userRepository.save(user);
    }
    public void update(User user){
        redisTemplate.delete("user_" + user.getId());
        userRepository.update(user);
    }
    public void deleteAll(){
        redisTemplate.delete("user_*");
        userRepository.deleteAll();
    }
    public void delete(long id){
        redisTemplate.delete("user_" + id);
        userRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<User> search(User user){
        return null;
    }

    public Page<User> searchPage(User user, Pageable pageable) {
        return null;
    }
}
