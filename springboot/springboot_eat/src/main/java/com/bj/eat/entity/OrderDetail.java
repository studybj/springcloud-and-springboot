package com.bj.eat.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eat_tb_order_detail")
@Entity
@DynamicUpdate
@Data
public class OrderDetail {
    @Id
    private String id;
    /**订单id*/
    private String orderId;
    /**商品id*/
    private String productId;
    /**商品名称*/
    private String productName;
    /**商品价格*/
    private BigDecimal productPrice;
    /**商品数量*/
    private Integer productQuantity;
    /**商品图片*/
    private String productIcon;
    private Date createTime;
    private Date updateTime;
}
