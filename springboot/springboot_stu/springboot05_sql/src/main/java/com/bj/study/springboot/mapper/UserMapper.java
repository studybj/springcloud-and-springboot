package com.bj.study.springboot.mapper;

import com.bj.study.springboot.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    /**新增一个用户 */
    @Insert("insert into sys_user(id, usercode,username,password,salt,locked) values(#{id},#{usercode},#{username},#{password},#{salt},#{locked})")
    void create(User user);
    /** 根据name删除一个用户*/
    @Delete("delete from sys_user where username=#{name}")
    void deleteByName(String name);
    /** 获取所有用户信息 */
    @Select("select * from sys_user ")
    List<User> getAllUsers3();
        }
