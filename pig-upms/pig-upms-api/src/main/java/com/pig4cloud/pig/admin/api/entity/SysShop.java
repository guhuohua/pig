package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 账户店铺关系表
 *
 * @author 球球
 * @date 2019-02-22 18:30:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_shop")
public class SysShop extends Model<SysShop> {
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
   * 用户id
   */
    private Integer userId;
  
}
