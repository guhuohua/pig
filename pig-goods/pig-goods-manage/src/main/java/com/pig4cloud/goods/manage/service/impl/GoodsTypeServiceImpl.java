package com.pig4cloud.goods.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.goods.api.entity.GoodsType;
import com.pig4cloud.goods.api.vo.BaseSelectListVo;
import com.pig4cloud.goods.manage.mapper.GoodsTypeMapper;
import com.pig4cloud.goods.manage.service.GoodsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品分类信息
 *
 * @author 球球
 * @date 2019-02-21 12:01:02
 */
@Service("goodsTypeService")
@RequiredArgsConstructor
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements GoodsTypeService {

	private final GoodsTypeMapper goodsTypeMapper;

	/**
	 * 商品分类信息简单分页查询
	 *
	 * @param goodsType 商品分类信息
	 * @return
	 */
	@Override
	public IPage<GoodsType> getGoodsTypePage(Page<GoodsType> page, GoodsType goodsType) {
		return baseMapper.getGoodsTypePage(page, goodsType);
	}

	/**
	 * 获取商品分类的顶级分类
	 *
	 * @param shopId 商铺id
	 * @return
	 */
	@Override
	public List<BaseSelectListVo> getTypeList(Integer shopId) {
		List<GoodsType> list = goodsTypeMapper.getTypeList(shopId);
		List<BaseSelectListVo> voList = list.stream().map(item -> new BaseSelectListVo(item.getTitle(), item.getId())).collect(Collectors.toList());
		voList.add(0, new BaseSelectListVo("顶级分类", 0));
		return voList;
	}

}
