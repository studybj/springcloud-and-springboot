package com.justtide.tms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tms_device_error")
@Data
public class DeviceError implements Serializable {
	@Id
	private int  id;  // id; 
	private String  deviceSn;  // 终端SN;唯一，如40001608180246  
	private String  deviceType;  // 终端类型;1：S1000  2：M3000  3：PC1000  
	private String  createTime;  // 创建时间;yyyyMMddHHmmss  
	private String  systemTime;  // 终端系统时间;yyyyMMddHHmmss  
	private String  logInfo;  // 日志信息;  
	private String  remark;  // 备注;  
	private int spare_int1;
	private int spare_int2;
	private String spare_str1;
	private String spare_str2;
}
