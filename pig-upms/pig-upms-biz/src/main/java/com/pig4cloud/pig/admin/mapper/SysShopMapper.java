package com.pig4cloud.pig.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysShop;
import org.apache.ibatis.annotations.Param;

/**
 * 账户店铺关系表
 *
 * @author 球球
 * @date 2019-02-22 18:30:17
 */
public interface SysShopMapper extends BaseMapper<SysShop> {
	/**
	 * 账户店铺关系表简单分页查询
	 *
	 * @param sysShop 账户店铺关系表
	 * @return
	 */
	IPage<SysShop> getSysShopPage(Page page, @Param("sysShop") SysShop sysShop);

	Integer findByUserId(@Param("userId") Integer userId);



}
