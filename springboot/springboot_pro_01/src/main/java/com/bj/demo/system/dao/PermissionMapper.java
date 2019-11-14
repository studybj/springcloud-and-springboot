package com.bj.demo.system.dao;

import com.bj.demo.system.entity.Permission;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PermissionMapper {
    String TABLE_NAEM = " sys_permission ";
    String INSERT_FIELDS = " name, type, url, percode, partentId, partentIds,sortstring, available ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",SELECT_FIELDS,") values (#{id},#{name},#{type},#{url},#{percode},#{partentId},#{partentIds},#{sortstring},#{available})"})
    public void insert(Permission permission);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where id=#{id}"})
    public Permission seletById(String id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where name=#{name}"})
    public Permission seletByName(@Param("name") String name);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    public void deleteById(String id);
}
