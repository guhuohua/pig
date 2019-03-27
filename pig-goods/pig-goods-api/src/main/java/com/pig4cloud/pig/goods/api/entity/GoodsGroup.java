package com.pig4cloud.pig.goods.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品分组
 *
 * @author 球球
 * @date 2019-02-21 10:39:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_group")
public class GoodsGroup extends Model<GoodsGroup> {
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
	 * 分组名称
	 */
	private String title;
	/**
	 * 分类
	 */
	private Integer type;
	/**
	 * 商品id
	 */
	private String goodsIds;
	/**
	 * 分类
	 */
	private String goodsTypes;

}
