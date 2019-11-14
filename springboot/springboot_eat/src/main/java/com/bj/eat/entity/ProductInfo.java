package com.bj.eat.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "eat_tb_product_info")
@Entity
@DynamicUpdate //该注解是对于更新等时，时间动态更新
@Data //该注解使得实体省略掉get、set、tostring等方法
public class ProductInfo {
    @Id
    private String id;
    /**商品名称*/
    private String productName;
    /**商品价格*/
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /**描述*/
    private String productDescription;
    /**图片*/
    private String productIcon;
    /**商品状态 0待上架，1已上架，2已下架*/
    private Integer productStatus;
    /**商品类型或类目*/
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;

}
