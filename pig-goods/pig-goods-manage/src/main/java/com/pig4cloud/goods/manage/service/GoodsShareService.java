package com.pig4cloud.goods.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.goods.api.entity.GoodsShare;

/**
 * 商品分享
 *
 * @author 球球
 * @date 2019-02-21 15:35:49
 */
public interface GoodsShareService extends IService<GoodsShare> {

  /**
   * 商品分享简单分页查询
   * @param goodsShare 商品分享
   * @return
   */
  IPage<GoodsShare> getGoodsSharePage(Page<GoodsShare> page, GoodsShare goodsShare);


}
