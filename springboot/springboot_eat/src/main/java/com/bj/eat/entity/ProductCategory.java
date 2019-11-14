package com.bj.eat.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Table(name = "eat_tb_product_category")
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**类别名称*/
    private String categoryName;
    /**类别类型*/
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}
