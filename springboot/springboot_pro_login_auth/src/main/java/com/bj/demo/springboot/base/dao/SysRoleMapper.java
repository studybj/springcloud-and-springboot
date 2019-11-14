package com.bj.demo.springboot.base.dao;

import com.bj.demo.springboot.base.entity.SysRole;
import com.bj.demo.springboot.base.entity.SysUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SysRoleMapper {
    @Select("select * from sys_role where id=#{id}")
    public SysRole get(String id);

    @Delete("delete from sys_role where id=#{id}")
    public int delete(String id);
    @Insert("insert into sys_role(id,role_name,status,create_time,update_time) values(#{id},#{roleName},#{status},#{createTime},update_time=#{updateTime})")
    public int insert(SysRole role);
    @Update("update sys_role set role_name=#{roleName},status=#{status},create_time=#{createTime},update_time=#{updateTime} where id=#{id}")
    public int update(SysRole role);
}
