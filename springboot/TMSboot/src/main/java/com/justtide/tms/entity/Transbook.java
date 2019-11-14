package com.justtide.tms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="tms_transbook")
public class Transbook implements Serializable {
	@Id
	private int  id;  // id;  
	private String  deviceSn;  // 终端SN;唯一，如40001608180246  
	private String  deviceType;  // 终端类型;1：S1000  2：M3000  3：PC1000  4:V7 
	private String  transTime;  // 交互时间;yyyyMMddHHmmss  
	private String  deviceIp;  // 终端IP地址;  
	private String  longitude;  // 经度;如：112.33224455，保留六位小数点 
	private String  latitude;  // 纬度;如：112.33224455，保留六位小数点  
	private String  transInfo;  // 交互具体内容;如：查询，系统更新，版本更新等 
	private String  remark;  // 备注;  
	private int  spare_int1;  // spare_int1;备用字段  
	private int  spare_int2;  // spare_int2;备用字段  
	private String  spare_str1;  // spare_str1;备用字段  
	private String  spare_str2;  // spare_str2;备用字段  
}
