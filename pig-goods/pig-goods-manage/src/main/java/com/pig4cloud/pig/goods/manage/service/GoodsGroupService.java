package com.pig4cloud.pig.goods.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.goods.api.entity.GoodsGroup;

/**
 * 商品分组
 *
 * @author 球球
 * @date 2019-02-21 10:39:43
 */
public interface GoodsGroupService extends IService<GoodsGroup> {

  /**
   * 商品分组简单分页查询
   * @param goodsGroup 商品分组
   * @return
   */
  IPage<GoodsGroup> getGoodsGroupPage(Page<GoodsGroup> page, GoodsGroup goodsGroup);


}
