package com.pig4cloud.shop.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 包邮设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("shop_order_distribution")
public class OrderDistribution extends Model<OrderDistribution> {
private static final long serialVersionUID = 1L;

    /**
   * 编号
   */
    @TableId
    private Integer id;
    /**
   * 店铺id
   */
    private Integer shopId;
    /**
   * 状态
   */
    private Integer status;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;
    /**
   * 删除标识
   */
    private Integer delFlag;
    /**
   * 配送方式名称
   */
    private String typeName;
    /**
   * 是否包邮 0、包邮，1、不包邮
   */
    private Integer freeShipping;
    /**
   * 计价方式 0、按件，1、按重量
   */
    private String valuation;
    /**
   * 范围内数量
   */
    private Integer minCount;
    /**
   * 范围外数量
   */
    private Integer maxCount;
    /**
   * 范围内价格
   */
    private Double minPrice;
    /**
   * 范围外价格
   */
    private Double maxPrice;
    /**
   * 范围内重量
   */
    private Double minWeight;
    /**
   * 范围外重量
   */
    private Double maxWeight;
    /**
   * 是否是默认的 0、默认，1、非默认的
   */
    private Integer defaulty;

}
