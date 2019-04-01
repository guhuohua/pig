package com.pig4cloud.goods.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 商品分享
 *
 * @author 球球
 * @date 2019-02-21 15:35:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_share")
public class GoodsShare extends Model<GoodsShare> {
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
   * 分享返现
   */
    private Integer type;
    /**
   * 是否开启
   */
    private Integer open;

}
