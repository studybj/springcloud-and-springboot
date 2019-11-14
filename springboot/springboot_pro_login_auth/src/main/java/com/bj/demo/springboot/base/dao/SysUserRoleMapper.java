package com.bj.demo.springboot.base.dao;

import com.bj.demo.springboot.base.entity.SysUserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysUserRoleMapper {
    @Select("SELECT * FROM sys_user_role WHERE user_id = #{userId}")
    List<SysUserRole> listByUserId(String userId);
    @Delete("delete from sys_user_role where id=#{id}")
    public int delete(String id);
    @Insert("insert into sys_user_role(id,role_id,user_id,create_time,update_time) values(#{id},#{roleId},#{userId},#{createTime},update_time=#{updateTime})")
    public int insert(SysUserRole sysUserRole);
}
