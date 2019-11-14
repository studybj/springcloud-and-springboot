package com.bj.demo.springboot.base.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysMenu implements Serializable{
  private String id;
  private String menuName;
  private Integer status;
  private Date createTime;
  private Date updateTime;
}
