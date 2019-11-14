package com.bj.demo.system.dao;

import com.bj.demo.system.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    String TABLE_NAEM = " sys_user ";
    String INSERT_FIELDS = " usercode, username, password, salt, locked ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",SELECT_FIELDS,") values (#{id},#{username},#{password},#{salt},#{headUrl},#{role})"})
    public void insert(User user);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where id=#{id}"})
    public User seletById(String id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where name=#{name}"})
    public User seletByName(@Param("name") String name);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    public void deleteById(String id);
}
