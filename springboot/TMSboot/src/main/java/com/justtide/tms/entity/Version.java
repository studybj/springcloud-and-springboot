package com.justtide.tms.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tms_version")
@Data
public class Version implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer  id;  // Id;在新增版本时，针对某些版本发布的时候需要取到该id
	
	private String  versionNo;  // 版本编号;  
	
	private String  versionName;  // 版本名称;  
	
	private String   versionType;  // 版本类型;1：系统版本；2：安全固件版本；3：TMS应用版本 
	
	private String   deviceType;  // 终端类型；1：S1000 2：M3000 3：PC1000
	
	private String publishType;  //版本发布类型;1:按版本发布;2:按终端发布
	
	private String  detail;  // 版本描述详情;  
	
	private String  state;  	// 版本状态; 0:已发布;1:已关闭;2:发布中 
	private String  pushSign;  // 推送标志，用于终端版本主动推送，1：推送 0：不推送
	
	private String  fileMd5;  	// 版本md5;  
	private String  fileSize;  	// 版本文件大小;  
	
	private String  createTime;  // 创建时间;yyyyMMddHHmmss  
	
	private String  updateTime;  // 修改时间;yyyyMMddHHmmss  
	
	private String  url;  // 系统文件存放url;用来存放系统文件，以便下载系统至本地 
	
	private String  remark;  // 备注;
	
	private int spare_int1;
	
	private int spare_int2;
	
	private String spare_str1;
	
	private String spare_str2;
}
