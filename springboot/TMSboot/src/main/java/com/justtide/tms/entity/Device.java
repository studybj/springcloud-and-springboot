package com.justtide.tms.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="tms_device")
@Data
@ApiModel
public class Device implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@ApiModelProperty(value = "终端id")
	private Integer id;
	
	private String deviceSn; // 终端SN;终端sn+终端类型可唯一确定一台设备
	
	private String deviceType; // 终端类型;1：S1000 2：M3000 3：PC1000
	
	private String ip; // 终端ip地址;
	
	private String deviceSystemTime; // 终端系统时间;yyyyMMddHHmmss
	
	private String systemVersion; // 终端系统版本;如：AM812_B14G4W1_Z3031_JSTD007_V00A4
	
	private String firmwareVersion; // 安全固件版本;如：N09A_1102_beta0
	
	private String hardwareVersion; // 硬件版本;如：Q-A1JT3
	
	private String IMEINo; // IMEI号;
	
	private String HVGANum; // 分辨率;
	
	private String mapType; // 坐标系;如：gps84、gcj02、bd09
	
	private String longitude; // 经度;如：112.33224455，保留六位小数点
	
	private String latitude; // 纬度;如：112.33224455，保留六位小数点
	
	private String memorySpace; // 存储信息;内部存储空间 ; // ;可用空间/剩余空间 ; D卡/外置SD卡 ;可用空间/剩余空间 ; 以’,’隔开，可以为空，单位M
	
	private String kernelVersion; // 内核版本号;
	
	private String merchantNo; // 所属商户id;默认为1，表示九思设备，在显示时需要进行过滤
	
	private String batch; // 出库批次;
	
	private String updateTime; // 更新时间;yyyyMMddHHmmss
	
	private String createTime; // 创建时间;yyyyMMddHHmmss
	
	private String deviceState; // 设备状态;
	
	private int dealTime; // 交互次数;默认为0，每交互一次加1
	
	private String remark; // 备注;

	private String serverVersion;//TMS应用版本
	
	private int activateTimes;	//激活次数，默认为0

	private int spare_int1;
	
	private int spare_int2;
	
	private String spare_str1;
	
	private String spare_str2;
	/** 所属用户id;表示由哪个用户添加，需注意用户必须是商户号下的*/
	//private String userid;
}
