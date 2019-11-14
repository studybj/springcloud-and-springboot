package com.justtide.tms.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tms_device_type")
@Data
public class DeviceType implements Serializable {
	@Id
	private String deviceTypeId;
	private String deviceTypeName;
	private String deviceTypeDesc;
	private String createUser;
	private String createTime;
	private String updateUser;
	private String updateTime;

	private String deviceSnPrefix;	//该类型sn号前缀
	private Integer deviceSnSuffixBit; //该类型sn号前缀后续(数字)位数
	private Integer deviceSnExpend;	//该类型sn号扩展位数，备用，如J700000001-xxxx 则扩展位为4

	private int spare_int1;
	private int spare_int2;
	private String spare_str1;
	private String spare_str2;
}
