package com.pig4cloud.pig.goods.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.goods.api.entity.Goods;
import com.pig4cloud.pig.goods.manage.mapper.GoodsMapper;
import com.pig4cloud.pig.goods.manage.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * 商品信息
 *
 * @author 球球
 * @date 2019-02-21 15:15:59
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

  /**
   * 商品信息简单分页查询
   * @param goods 商品信息
   * @return
   */
  @Override
  public IPage<Goods> getGoodsPage(Page<Goods> page, Goods goods){
      return baseMapper.getGoodsPage(page,goods);
  }

}
