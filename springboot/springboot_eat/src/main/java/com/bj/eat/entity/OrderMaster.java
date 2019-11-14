package com.bj.eat.entity;

import com.bj.eat.enums.OrderPayStatusEnum;
import com.bj.eat.enums.OrderStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eat_tb_order_master")
@Entity
@DynamicUpdate
@Data
public class OrderMaster {
    @Id
    private String id;
    /**买家名称*/
    private String buyerName;
    /**买家电话*/
    private String buyerPhone;
    /**买家地址*/
    private String buyerAddress;
    /**买家微信openID*/
    private String buyerOpenId;
    /**订单总金额*/
    private BigDecimal orderAmount;
    /**订单状态，默认0新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /**支付状态，默认0未支付*/
    private Integer payStatus = OrderPayStatusEnum.WAIT.getCode();

    private Date createTime;
    private Date updateTime;

}
