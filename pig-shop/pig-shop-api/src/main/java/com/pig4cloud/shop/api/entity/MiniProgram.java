package com.pig4cloud.shop.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 微信小程序信息 微信小程序信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("shop_mini_program")
public class MiniProgram extends Model<MiniProgram> {
private static final long serialVersionUID = 1L;

    /**
   * 编号 编号
   */
    @TableId
    private Integer id;
    /**
   * 店铺id 店铺id
   */
    private Integer shopId;
    /**
   * appid
   */
    private String appId;
    /**
   * secret
   */
    private String secret;
    /**
   * 地址
   */
    private String backUrl;
    /**
   * 商户id
   */
    private String mchIdd;
    /**
   * 支付密匙
   */
    private String appKey;
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

}
