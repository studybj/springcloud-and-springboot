package com.bj.demo.system.dao;

import com.bj.demo.system.entity.UserRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    String TABLE_NAEM = " sys_user_role ";
    String INSERT_FIELDS = " userId, roleId ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",SELECT_FIELDS,") values (#{id},#{userId},#{roleId})"})
    public void insert(UserRole userRole);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where id=#{id}"})
    public UserRole seletById(String id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where sys_user_id=#{userId}"})
    public List<UserRole> listByUserId(String userId);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where sys_role_id=#{roleId}"})
    public List<UserRole> listByRoleId(String roleId);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    public void deleteById(String id);
}
