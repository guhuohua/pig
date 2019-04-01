package com.pig4cloud.shop.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 商铺账户信息 商铺账户信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("shop_account")
public class Account extends Model<Account> {
private static final long serialVersionUID = 1L;

    /**
   * 编号 编号
   */
    @TableId
    private Integer id;
    /**
   * 标识 标识
   */
    private String flag;
    /**
   * 描述 描述
   */
    private String desc;
    /**
   * 状态 状态
   */
    private Integer status;
    /**
   * 删除标识 删除标识
   */
    private Integer delFlag;
    /**
   * 结束时间 结束时间
   */
    private LocalDateTime endDate;
    /**
   * 创建时间 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 更新时间 更新时间
   */
    private LocalDateTime updateTime;

}
