package com.pig4cloud.goods.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.goods.api.entity.GoodsDetails;

/**
 * 商品详情
 *
 * @author 球球
 * @date 2019-02-21 15:35:24
 */
public interface GoodsDetailsService extends IService<GoodsDetails> {

  /**
   * 商品详情简单分页查询
   * @param goodsDetails 商品详情
   * @return
   */
  IPage<GoodsDetails> getGoodsDetailsPage(Page<GoodsDetails> page, GoodsDetails goodsDetails);


}
