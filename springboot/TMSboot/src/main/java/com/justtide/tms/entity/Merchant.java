package com.justtide.tms.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tms_merchant")
@Data
public class Merchant implements Serializable {
	@Id
	private String merchantNo;
	private String parentMerchantNo;
	private String merchantName;
	private String merchantEnName;
	private String contactPerson;
	private String phoneNumber;
	private String zipCode;
	private String address;
	private String description;
	private String country;
	private String province;
	private String city;
	private String district;
	private String areaId;
	private String createUser;
	private String createTime;
	private String updateUser;
	private String updateTime;
	private String sessionKey;
	private int spare_int1;
	private int spare_int2;
	private String spare_str1;
	private String spare_str2;
	private String salesman;
}
