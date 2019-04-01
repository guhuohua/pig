package com.pig4cloud.goods.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.goods.api.entity.GoodsEvaluation;
import com.pig4cloud.goods.manage.mapper.GoodsEvaluationMapper;
import com.pig4cloud.goods.manage.service.GoodsEvaluationService;
import org.springframework.stereotype.Service;

/**
 * 商品评价
 *
 * @author 球球
 * @date 2019-02-21 15:35:31
 */
@Service("goodsEvaluationService")
public class GoodsEvaluationServiceImpl extends ServiceImpl<GoodsEvaluationMapper, GoodsEvaluation> implements GoodsEvaluationService {

  /**
   * 商品评价简单分页查询
   * @param goodsEvaluation 商品评价
   * @return
   */
  @Override
  public IPage<GoodsEvaluation> getGoodsEvaluationPage(Page<GoodsEvaluation> page, GoodsEvaluation goodsEvaluation){
      return baseMapper.getGoodsEvaluationPage(page,goodsEvaluation);
  }

}
