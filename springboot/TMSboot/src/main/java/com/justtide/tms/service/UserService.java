package com.justtide.tms.service;

import com.justtide.tms.entity.User;
import com.justtide.tms.repository.UserRepository;
import com.justtide.tms.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;


    //-----------------------------------基本操作--------------------------------------------------
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById(String id){
        return userRepository.findById(id).get();
    }
    public void save(User user){
        userRepository.save(user);
    }
    public void update(User user){
        userRepository.save(user);
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }
    public void delete(String id){
        userRepository.deleteById(id);
    }

    public User login(UserVo user) {
        User user_login = userRepository.findByUserName(user.getUsername());
        if (user_login != null /*&& encoder.matches(user.getPassword(), user_login.getPassword())*/){
            return user_login;
        }
        return null;
    }

    //-----------------------------------条件查询操作--------------------------------------------------
    public List<User> search(User user){
        return userRepository.findAll(paramOperate(user));
    }

    public Page<User> searchPage(User user, Pageable pageable) {
        return userRepository.findAll(paramOperate(user), pageable);
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
                    if(user.getUserName() != null && !"".equals(user.getUserName())){
                        Predicate userSnnamePredicate = cb.like(root.get("userName"), "%" + user.getUserName() + "%");
                        predicatesList.add(userSnnamePredicate);
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
