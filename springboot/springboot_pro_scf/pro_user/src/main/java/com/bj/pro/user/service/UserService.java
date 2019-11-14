package com.bj.pro.user.service;

import com.bj.pro.user.entity.User;
import com.bj.pro.user.repository.UserRepository;
import com.bj.pro.user.vo.UserLoginVo;
import exception.OwnRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private BCryptPasswordEncoder encoder;
    //-----------------------------------基本操作--------------------------------------------------
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(String id){
        User user = (User) redisTemplate.opsForValue().get("user_" + id);
        if (user == null){
            user = userRepository.findById(id).get();
            redisTemplate.opsForValue().set("user_" + id, user);
        }
        return user;
    }
    public void save(User user){
        //初始化
        user.setId(idWorker.nextId() + "");
        user.setFollowcount(0);
        user.setFanscount(0);
        user.setOnline(0l);
        user.setRegdate(new Date());
        user.setUpdatedate(new Date());
        user.setLastdate(new Date());
        //密码加密
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public void update(User user){
        redisTemplate.delete("user_" + user.getId());
        userRepository.save(user);
    }
    public void deleteAll(){
        redisTemplate.delete("user_*");
        userRepository.deleteAll();
    }
    public void delete(String id){
        redisTemplate.delete("user_" + id);
        userRepository.deleteById(id);
    }
    //-----------------------------------条件查询操作--------------------------------------------------
    public List<User> search(User user){
        return userRepository.findAll(paramOperate(user));
    }

    public Page<User> searchPage(User user, Pageable pageable) {
        return userRepository.findAll(paramOperate(user), pageable);
    }
    public void sendSms(String mobile) {
        //生成随机数
        String random = RandomStringUtils.randomNumeric(6);
        //放入缓存
        redisTemplate.opsForValue().set("validate_code_" + mobile, random, 30, TimeUnit.SECONDS);
        //发一份给用户(消息队列)【或发短信】
        Map map = new HashMap();
        map.put("mobile", mobile);
        map.put("code", random);
        rabbitTemplate.convertAndSend("sms", map);
        //留一份进行验证
        log.info("validate_code:{}", random);
    }
    public void login(UserLoginVo userVo) {
        if (userVo == null || StringUtils.isEmpty(userVo.getMobile())){
            throw  new OwnRuntimeException("用户信息不正确");
        }
        User user = userRepository.findByMobile(userVo.getMobile());
        if (user == null){
            throw  new OwnRuntimeException("用户信息不正确");
        }
        if (!encoder.matches(userVo.getPassword(), user.getPassword())){
            throw  new OwnRuntimeException("用户信息不正确");
        }
    }
    //-----------------------------------私有操作--------------------------------------------------
    private Specification<User> paramOperate(User user) {
        Specification<User> spec = new Specification<User>() {
            /**
             * @param root 根对象，即要把条件封装到指定对象中，
             * @param query 封装的查询关键字
             * @param cb 封装条件对象
             * @return
             */
            @Nullable
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                if(user != null){
                    if(!StringUtils.isEmpty(user.getNickname())){//user.getName() != null && !"".equals(user.getName())
                        Predicate namePredicate = cb.like(root.get("nickname"), "%" + user.getNickname() + "%");
                        predicatesList.add(namePredicate);
                    }
                }
                if (predicatesList.size() != 0) {
                    Predicate[] p = new Predicate[predicatesList.size()];
                    return cb.and(predicatesList.toArray(p));
                }
                return null;
            }
        };
        return spec;
    }

}
