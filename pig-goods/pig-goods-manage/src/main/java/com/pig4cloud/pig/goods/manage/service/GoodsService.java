package com.pig4cloud.pig.goods.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.goods.api.entity.Goods;

/**
 * 商品信息
 *
 * @author 球球
 * @date 2019-02-21 15:15:59
 */
public interface GoodsService extends IService<Goods> {

  /**
   * 商品信息简单分页查询
   * @param goods 商品信息
   * @return
   */
  IPage<Goods> getGoodsPage(Page<Goods> page, Goods goods);


}
