package com.bj.stu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {
    private long id;
    private String name;
    private double price;
    private String flavor;
    private long tid;
}
