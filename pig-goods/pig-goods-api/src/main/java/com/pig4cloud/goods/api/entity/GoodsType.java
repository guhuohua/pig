package com.pig4cloud.goods.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 商品分类信息
 *
 * @author 球球
 * @date 2019-02-21 12:01:02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_type")
public class GoodsType extends Model<GoodsType> {
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
   * 分类标题
   */
    private String title;
    /**
   * 图标
   */
    private String icon;
    /**
   * 父节点 0、顶级分类
   */
    private Integer parentId;
    /**
   * 排序
   */
    private Integer sort;
    /**
   * 广告url
   */
    private String ad;

}
