package com.pig4cloud.pig.goods.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品规格表
 *
 * @author 球球
 * @date 2019-02-21 15:35:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_specification")
public class GoodsSpecification extends Model<GoodsSpecification> {
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
   * 子属性 多个子属性id
   */
    private String attrs;
    /**
   * 原价
   */
    private Double originalPrice;
    /**
   * 现价
   */
    private Double presentPrice;
    /**
   * 库存
   */
    private Integer inventory;
    /**
   * 编码
   */
    private String sn;
  
}
