package com.pig4cloud.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.shop.api.entity.Info;
import com.pig4cloud.shop.manage.mapper.InfoMapper;
import com.pig4cloud.shop.manage.service.InfoService;
import org.springframework.stereotype.Service;

/**
 * 店铺信息 店铺信息
 *
 * @author 球球
 * @date 2019-02-21 16:06:42
 */
@Service("infoService")
public class InfoServiceImpl extends ServiceImpl<InfoMapper, Info> implements InfoService {



	/**
	 * 店铺信息 店铺信息简单分页查询
	 *
	 * @param info 店铺信息 店铺信息
	 * @return
	 */
	@Override
	public IPage<Info> getInfoPage(Page<Info> page, Info info) {
		return baseMapper.getInfoPage(page, info);
	}

	/**
	 * 店铺信息， shopId 查询
	 *
	 * @param shopId shopId
	 * @return
	 */
	@Override
	public Info findByShopId(Integer shopId) {
		return baseMapper.selectOne(Wrappers.<Info>query().lambda().eq(Info::getShopId, shopId));
	}


}
