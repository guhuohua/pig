package com.pig4cloud.pig.goods.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther: 球球
 * @Date: 2019/3/15 14:15
 * @description: 一般下拉列表对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseSelectListVo {
	private String label;
	private Integer value;
}
