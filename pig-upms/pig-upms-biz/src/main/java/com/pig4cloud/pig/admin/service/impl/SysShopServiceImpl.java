package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.SysShop;
import com.pig4cloud.pig.admin.api.vo.MenuVO;
import com.pig4cloud.pig.admin.mapper.SysShopMapper;
import com.pig4cloud.pig.admin.service.SysShopService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户店铺关系表
 *
 * @author 球球
 * @date 2019-02-22 18:30:17
 */
@Service("sysShopService")
@AllArgsConstructor
public class SysShopServiceImpl extends ServiceImpl<SysShopMapper, SysShop> implements SysShopService {

	private final SysShopMapper sysShopMapper;


	/**
	 * 账户店铺关系表简单分页查询
	 *
	 * @param sysShop 账户店铺关系表
	 * @return
	 */
	@Override
	public IPage<SysShop> getSysShopPage(Page<SysShop> page, SysShop sysShop) {
		return baseMapper.getSysShopPage(page, sysShop);
	}

	/**
	 * 根据userId 获得对应的 所属的店铺id
	 *
	 * @param userId
	 * @return
	 */
	@Override
	@Cacheable(value = "user_shop", key = "#userId")
	public Integer findByUserId(Integer userId) {
		return sysShopMapper.findByUserId(userId);
	}


}
