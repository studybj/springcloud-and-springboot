package com.justtide.tms.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_role")
@Data
public class Role implements Serializable {
	@Id
	@GeneratedValue()
	private Integer roleId;
	private String roleName;
	private String roleEnName;
	private String roleMenuIds;
	private String roleMenuIds2;
	private int level;
	private String description;
	private String merchantType;
	private String createUser;
	private String createTime;
	private String updateUser;
	private String updateTime;
	private String remark;
    private int spare_int1;
    private int spare_int2;
    private String spare_str1;
    private String spare_str2;

    //无参构造方法
    public Role(){
    	
    }
    
    //有参构造方法
	public int getRoleId() {
		return roleId;
	}

	public Role(int roleId, String roleName,String roleEnName, String roleMenuIds,
			String roleMenuIds2, int level, String description,
			String merchantType, String createUser, String createTime,
			String updateUser, String updateTime, String remark, int spareInt1,
			int spareInt2, String spareStr1, String spareStr2) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleEnName = roleEnName;
		this.roleMenuIds = roleMenuIds;
		this.roleMenuIds2 = roleMenuIds2;
		this.level = level;
		this.description = description;
		this.merchantType = merchantType;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.remark = remark;
		spare_int1 = spareInt1;
		spare_int2 = spareInt2;
		spare_str1 = spareStr1;
		spare_str2 = spareStr2;
	}
}
