package com.pig4cloud.goods.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.goods.api.entity.GoodsImage;

/**
 * 商品图片
 *
 * @author 球球
 * @date 2019-02-21 15:35:04
 */
public interface GoodsImageService extends IService<GoodsImage> {

  /**
   * 商品图片简单分页查询
   * @param goodsImage 商品图片
   * @return
   */
  IPage<GoodsImage> getGoodsImagePage(Page<GoodsImage> page, GoodsImage goodsImage);


}
