package com.pig4cloud.pig.goods.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品评价
 *
 * @author 球球
 * @date 2019-02-21 15:35:31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_evaluation")
public class GoodsEvaluation extends Model<GoodsEvaluation> {
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
   * 用户名称
   */
    private String name;
    /**
   * 详情
   */
    private String details;
    /**
   * 评分 0-5
   */
    private Integer score;
    /**
   * 商品id
   */
    private Integer goodsId;
    /**
   * 订单id
   */
    private Integer orderId;
  
}
