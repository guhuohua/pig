package com.pig4cloud.pig.shop.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 店铺全局设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("shop_global_setting")
public class GlobalSetting extends Model<GlobalSetting> {
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
   * 首页推荐商品 多个用逗号分隔
   */
    private String indexRecommend;
    /**
   * 首页赠送优惠券 多个用逗号分隔
   */
    private String indexCoupon;
    /**
   * 购物车推荐商品 多个用逗号分隔
   */
    private String shopcarRecommend;
    /**
   * 订单页推荐商品 多个用逗号分隔
   */
    private String orderRecommend;
    /**
   * 收索页关键词 多个用逗号分隔
   */
    private String searchKey;
  
}
