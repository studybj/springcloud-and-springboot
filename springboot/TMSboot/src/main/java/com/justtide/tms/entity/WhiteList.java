package com.justtide.tms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tms_online_whitelist")
@Data
public class WhiteList implements Serializable {

	@Id
	@GeneratedValue()
	private int id;
	private String deviceSn;	//唯一，如40001608180246
	private String deviceType;	//1：S1000  2：M3000  3：PC1000
	private String createTime;	//创建时间：yyyyMMddHHmmss
	private String status;		//状态
	private String activateTime;//激活时间
	private String maintainId;	//维护编号6位随机数
	private int spare_int1;
	private int spare_int2;
	private String spare_str1;
	private String spare_str2;
	
	private String cardCheck;
}
