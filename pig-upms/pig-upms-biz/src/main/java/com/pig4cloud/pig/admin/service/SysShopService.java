package com.pig4cloud.pig.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.admin.api.entity.SysShop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * 账户店铺关系表
 *
 * @author 球球
 * @date 2019-02-22 18:30:17
 */
public interface SysShopService extends IService<SysShop> {

	/**
	 * 账户店铺关系表简单分页查询
	 *
	 * @param sysShop 账户店铺关系表
	 * @return
	 */
	IPage<SysShop> getSysShopPage(Page<SysShop> page, SysShop sysShop);


	/**
	 * 根据userId 获得对应的 所属的店铺id
	 *
	 * @param userId
	 * @return
	 */
	Integer findByUserId(Integer userId);

}
