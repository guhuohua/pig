package com.pig4cloud.shop.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 店铺信息 店铺信息
 *
 * @author 球球
 * @date 2019-02-21 16:06:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("shop_info")
public class Info extends Model<Info> {
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
   * 店铺名称
   */
    private String title;
    /**
   * 店铺地址
   */
    private String address;
    /**
   * 店铺分享标题
   */
    private String shareTitle;
    /**
   * 客服电话
   */
    private String tel;
    /**
   * 客服时间 客服时间段
   */
    private String serviceTime;
    /**
   * 店铺logo
   */
    private String logo;
    /**
   * 店铺分享标题
   */
    private String shareImage;
    /**
   * 状态 0、正常，1、禁用
   */
    private BigDecimal status;
    /**
   * 创建日期
   */
    private LocalDateTime createTime;
    /**
   * 更新时间
   */
    private LocalDateTime updateTime;

}
