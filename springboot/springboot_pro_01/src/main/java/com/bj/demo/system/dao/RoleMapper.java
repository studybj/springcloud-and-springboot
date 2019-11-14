package com.bj.demo.system.dao;

import com.bj.demo.system.entity.Role;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoleMapper {
    String TABLE_NAEM = " sys_role ";
    String INSERT_FIELDS = " name, available, createDate ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",SELECT_FIELDS,") values (#{id},#{name},#{available},#{createDate})"})
    public void insert(Role role);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where id=#{id}"})
    public Role seletById(String id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where name=#{name}"})
    public Role seletByName(@Param("name") String name);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    public void deleteById(String id);
}
