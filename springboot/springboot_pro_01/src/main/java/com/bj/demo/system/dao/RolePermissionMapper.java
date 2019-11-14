package com.bj.demo.system.dao;

import com.bj.demo.system.entity.RolePermission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface RolePermissionMapper {
    String TABLE_NAEM = " sys_role_permission ";
    String INSERT_FIELDS = " roleId, permissionId ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",SELECT_FIELDS,") values (#{id},#{roleId},#{permissionId})"})
    public void insert(RolePermission rolePermission);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where id=#{id}"})
    public RolePermission seletById(String id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where sys_user_id=#{roleId}"})
    public List<RolePermission> listByRoleId(String roleId);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where sys_permission_id=#{permissionId}"})
    public List<RolePermission> listByPermissionId(String permissionId);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    public void deleteById(String id);
}
