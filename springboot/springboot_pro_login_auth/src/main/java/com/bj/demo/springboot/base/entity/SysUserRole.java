package com.bj.demo.springboot.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysUserRole implements Serializable {
  private String id;
  private String roleId;
  private String userId;
  private Date createTime;
  private Date updateTime;
}
