package com.pig4cloud.goods.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.goods.api.entity.GoodsSpecification;
import org.apache.ibatis.annotations.Param;

/**
 * 商品规格表
 *
 * @author 球球
 * @date 2019-02-21 15:35:18
 */
public interface GoodsSpecificationMapper extends BaseMapper<GoodsSpecification> {
  /**
    * 商品规格表简单分页查询
    * @param goodsSpecification 商品规格表
    * @return
    */
  IPage<GoodsSpecification> getGoodsSpecificationPage(Page page, @Param("goodsSpecification") GoodsSpecification goodsSpecification);


}
