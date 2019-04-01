package com.pig4cloud.goods.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.goods.api.entity.GoodsSpecification;

/**
 * 商品规格表
 *
 * @author 球球
 * @date 2019-02-21 15:35:18
 */
public interface GoodsSpecificationService extends IService<GoodsSpecification> {

  /**
   * 商品规格表简单分页查询
   * @param goodsSpecification 商品规格表
   * @return
   */
  IPage<GoodsSpecification> getGoodsSpecificationPage(Page<GoodsSpecification> page, GoodsSpecification goodsSpecification);


}
