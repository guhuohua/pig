package com.pig4cloud.pigdbinit.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @auther: 球球
 * @Date: 2019/2/19 15:53
 * @description: 小程序信息
 */
@Data
@Entity
@Table(name = "shop_miniProgram")
public class MiniProgrom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer shopId;
	@Column(length = 32)
	private String appId;
	@Column(length = 32)
	private String secret;
	@Column(length = 32)
	private String appKey;
	@Column(length = 16)
	private String mchIdd;
	private String backUrl;
	private Integer status;
	private Date createTime;
	private Date updateTime;

}
