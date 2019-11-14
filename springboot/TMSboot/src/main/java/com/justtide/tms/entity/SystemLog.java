package com.justtide.tms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tms_systerm_log")
@Data
public class SystemLog implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int  id;  // Id;
	
	private String  operator;  // 操作员; 
	
	private String  ip;  // 操作员;  
	
	private String  role;  // 角色;  
	
	private String  operate;  // 具体操作;操作员做的具体操作，用语言直接描述  
	
	private String  operateTime;  // 操作时间;yyyyMMddHHmmss  
	
	private String  remark;  // 备注;  

	private int spare_int1;
	
	private int spare_int2;
	
	private String spare_str1;
	
	private String spare_str2;
	
}
