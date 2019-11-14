package com.justtide.tms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tms_user")
@Data
public class User implements Serializable {
	@Id
	private String userNo;
	private String userName;
	private String realName;
	private String password;
	private String phoneNumber;
	private String zipCode;
	private String description;
	private String createUser;
	private String createTime;
	private String updateUser;
	private String updateTime;

	private int roleId;
	private String merchantNo;
}

