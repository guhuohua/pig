package com.pig4cloud.goods.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.goods.api.entity.GoodsEvaluation;
import org.apache.ibatis.annotations.Param;

/**
 * 商品评价
 *
 * @author 球球
 * @date 2019-02-21 15:35:31
 */
public interface GoodsEvaluationMapper extends BaseMapper<GoodsEvaluation> {
  /**
    * 商品评价简单分页查询
    * @param goodsEvaluation 商品评价
    * @return
    */
  IPage<GoodsEvaluation> getGoodsEvaluationPage(Page page, @Param("goodsEvaluation") GoodsEvaluation goodsEvaluation);


}
