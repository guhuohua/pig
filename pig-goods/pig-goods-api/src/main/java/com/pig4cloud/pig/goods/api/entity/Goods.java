package com.pig4cloud.pig.goods.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品信息
 *
 * @author 球球
 * @date 2019-02-21 15:15:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods")
public class Goods extends Model<Goods> {
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
   * 商品标题
   */
    private String title;
    /**
   * 商品简介
   */
    private String desc;
    /**
   * 推荐状态 0、非推荐，1、推荐中
   */
    private Integer recommend;
    /**
   * 出售状态 0、未出售，1、出售中
   */
    private Integer sale;
    /**
   * 排序
   */
    private Integer sort;
    /**
   * 分类
   */
    private Integer type;
    /**
   * 库存
   */
    private Integer inventory;
    /**
   * 原价
   */
    private Double originalPrice;
    /**
   * 现价
   */
    private Double presentPrice;
    /**
   * 商品编码
   */
    private String sn;
    /**
   * 会员折扣
   */
    private Integer memberDiscount;
    /**
   * 限购 为0不限购
   */
    private Integer limitBuy;
  
}
