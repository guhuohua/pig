package com.pig4cloud.shop.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.shop.api.entity.Info;

/**
 * 店铺信息 店铺信息
 *
 * @author 球球
 * @date 2019-02-21 16:06:42
 */
public interface InfoService extends IService<Info> {

	/**
	 * 店铺信息 店铺信息简单分页查询
	 *
	 * @param info 店铺信息 店铺信息
	 * @return
	 */
	IPage<Info> getInfoPage(Page<Info> page, Info info);


	/**
	 * 店铺信息， shopId 查询
	 *
	 * @param shopId shopId
	 * @return
	 */
	Info findByShopId(Integer shopId);
}
