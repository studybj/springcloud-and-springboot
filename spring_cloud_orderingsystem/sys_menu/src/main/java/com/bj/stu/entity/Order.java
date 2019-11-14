package com.bj.stu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class Order implements Serializable {
    private long id;
    private long uid;
    private long mid;
    private long aid;
    private Date date;
    private int state;
}
