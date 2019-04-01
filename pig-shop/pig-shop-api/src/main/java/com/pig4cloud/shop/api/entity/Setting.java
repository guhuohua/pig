package com.pig4cloud.shop.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 多店铺设置
 *
 * @author 球球
 * @date 2019-02-21 16:13:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("shop_setting")
public class Setting extends Model<Setting> {
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
   * 商铺序号
   */
    private Integer shopIndex;
    /**
   * 商铺名称
   */
    private String shopName;
    /**
   * 商铺地址
   */
    private String shopAddress;
    /**
   * 商铺联系方式
   */
    private String shopTel;
    /**
   * 经度
   */
    private String longitude;
    /**
   * 纬度
   */
    private String latitude;
    /**
   * 删除标识
   */
    private String delFlag;

}
