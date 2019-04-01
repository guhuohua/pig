package com.pig4cloud.goods.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.goods.api.entity.GoodsEvaluation;

/**
 * 商品评价
 *
 * @author 球球
 * @date 2019-02-21 15:35:31
 */
public interface GoodsEvaluationService extends IService<GoodsEvaluation> {

  /**
   * 商品评价简单分页查询
   * @param goodsEvaluation 商品评价
   * @return
   */
  IPage<GoodsEvaluation> getGoodsEvaluationPage(Page<GoodsEvaluation> page, GoodsEvaluation goodsEvaluation);


}
