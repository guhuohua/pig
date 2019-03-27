package com.pig4cloud.pig.goods.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.goods.api.entity.GoodsType;
import com.pig4cloud.pig.goods.api.vo.BaseSelectListVo;

import java.util.List;

/**
 * 商品分类信息
 *
 * @author 球球
 * @date 2019-02-21 12:01:02
 */
public interface GoodsTypeService extends IService<GoodsType> {

	/**
	 * 商品分类信息简单分页查询
	 *
	 * @param goodsType 商品分类信息
	 * @return
	 */
	IPage<GoodsType> getGoodsTypePage(Page<GoodsType> page, GoodsType goodsType);


	/**
	 * 获取商品分类的顶级分类
	 * @return
	 * @param shopId 商铺id
	 */
	List<BaseSelectListVo> getTypeList(Integer shopId);
}
