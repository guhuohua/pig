package com.pig4cloud.pig.goods.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品详情
 *
 * @author 球球
 * @date 2019-02-21 15:35:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_details")
public class GoodsDetails extends Model<GoodsDetails> {
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
   * 描述
   */
    private String details;
    /**
   * 商品id
   */
    private Integer goodsId;
  
}
