package com.bj.study.springboot.dao;

import com.bj.study.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserXmlMapper {
    void create(User user);
    User getById(String id);
    void update(User user);
    void deleteById(String id);

    /** 根据name删除一个用户*/
    void deleteByName(String name);
    /** 获取所有用户信息 */
    List<User> getAllUsers4();
}
