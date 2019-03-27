package com.pig4cloud.pigdbinit.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @auther: 球球
 * @Date: 2019/2/19 15:35
 * @description: 商铺信息
 */
@Data
@Entity
@Table(name = "shop_account")
public class Account {

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 标识
	 */
	@Column(length = 5, columnDefinition = "varchar(5) COMMENT '标识'")
	private String flag;

	/**
	 * 描述
	 */
	@Column(name = "`desc`", length = 50)
	private String desc;

	/**
	 * 状态
	 */
	private Integer status;

	/**
	 * 删除标识
	 */
	private Integer delFlag;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 更新时间
	 */
	private Date updateDate;

	/**
	 * 到期时间
	 */
	private Date endDate;

}
