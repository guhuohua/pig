package com.pig4cloud.goods.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.goods.api.entity.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类信息
 *
 * @author 球球
 * @date 2019-02-21 12:01:02
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
  /**
    * 商品分类信息简单分页查询
    * @param goodsType 商品分类信息
    * @return
    */
  IPage<GoodsType> getGoodsTypePage(Page page, @Param("goodsType") GoodsType goodsType);


	List<GoodsType> getTypeList(@Param("shopId") Integer shopId);
}
