package com.pig4cloud.shop.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 订单设置
 *
 * @author 球球
 * @date 2019-02-21 16:01:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("shop_order")
public class Order extends Model<Order> {
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
   * 包邮金额 全部免邮费置0即可
   */
    private Double freeShipping;
    /**
   * 常规订单关闭时间 单位：分钟
   */
    private Integer orderCloseNormal;
    /**
   * 活动订单关闭时间 单位：分钟
   */
    private Integer orderCloseActive;
    /**
   * 订单自动确定收货时间 单位：天
   */
    private Integer confirmDay;
    /**
   * 订单取消通知
   */
    private String templateCancel;
    /**
   * 订单发货通知
   */
    private String templateDelivery;
    /**
   * 订单评价通知
   */
    private String templateEvaluation;
    /**
   * 订单完成通知
   */
    private String templateComplete;

}
