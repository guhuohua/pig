package com.pig4cloud.goods.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.goods.api.entity.GoodsDetails;
import com.pig4cloud.goods.manage.mapper.GoodsDetailsMapper;
import com.pig4cloud.goods.manage.service.GoodsDetailsService;
import org.springframework.stereotype.Service;

/**
 * 商品详情
 *
 * @author 球球
 * @date 2019-02-21 15:35:24
 */
@Service("goodsDetailsService")
public class GoodsDetailsServiceImpl extends ServiceImpl<GoodsDetailsMapper, GoodsDetails> implements GoodsDetailsService {

  /**
   * 商品详情简单分页查询
   * @param goodsDetails 商品详情
   * @return
   */
  @Override
  public IPage<GoodsDetails> getGoodsDetailsPage(Page<GoodsDetails> page, GoodsDetails goodsDetails){
      return baseMapper.getGoodsDetailsPage(page,goodsDetails);
  }

}
