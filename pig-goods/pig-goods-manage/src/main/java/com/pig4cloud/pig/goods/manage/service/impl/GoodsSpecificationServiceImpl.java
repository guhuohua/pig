package com.pig4cloud.pig.goods.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.goods.api.entity.GoodsSpecification;
import com.pig4cloud.pig.goods.manage.mapper.GoodsSpecificationMapper;
import com.pig4cloud.pig.goods.manage.service.GoodsSpecificationService;
import org.springframework.stereotype.Service;

/**
 * 商品规格表
 *
 * @author 球球
 * @date 2019-02-21 15:35:18
 */
@Service("goodsSpecificationService")
public class GoodsSpecificationServiceImpl extends ServiceImpl<GoodsSpecificationMapper, GoodsSpecification> implements GoodsSpecificationService {

  /**
   * 商品规格表简单分页查询
   * @param goodsSpecification 商品规格表
   * @return
   */
  @Override
  public IPage<GoodsSpecification> getGoodsSpecificationPage(Page<GoodsSpecification> page, GoodsSpecification goodsSpecification){
      return baseMapper.getGoodsSpecificationPage(page,goodsSpecification);
  }

}
