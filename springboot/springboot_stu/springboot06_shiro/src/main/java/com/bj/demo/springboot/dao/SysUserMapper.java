package com.bj.demo.springboot.dao;

import com.bj.demo.springboot.entity.SysUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface SysUserMapper {
    @Select("select * from sys_user where id=#{id}")
    public SysUser get(String id);
    @Select("select * from sys_user where username=#{username}")
    public SysUser findByUsername(String username);

    @Delete("delete from sys_user where id=#{id}")
    public int delete(String id);
    @Insert("insert into sys_user(id,username,usercode,password) values(#{id},#{username},#{usercode},#{password})")
    public int insert(SysUser user);
    @Update("update sys_user set username=#{username},usercode=#{usercode},password=#{password} where id=#{id}")
    public int update(SysUser user);
}
