package com.bj.demo.springboot.base.entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysRole implements Serializable {
  private String id;
  private String roleName;
  private Long status;
  private java.sql.Date createTime;
  private java.sql.Date updateTime;
}
