package com.pig4cloud.goods.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.goods.api.entity.GoodsShare;
import com.pig4cloud.goods.manage.mapper.GoodsShareMapper;
import com.pig4cloud.goods.manage.service.GoodsShareService;
import org.springframework.stereotype.Service;

/**
 * 商品分享
 *
 * @author 球球
 * @date 2019-02-21 15:35:49
 */
@Service("goodsShareService")
public class GoodsShareServiceImpl extends ServiceImpl<GoodsShareMapper, GoodsShare> implements GoodsShareService {

  /**
   * 商品分享简单分页查询
   * @param goodsShare 商品分享
   * @return
   */
  @Override
  public IPage<GoodsShare> getGoodsSharePage(Page<GoodsShare> page, GoodsShare goodsShare){
      return baseMapper.getGoodsSharePage(page,goodsShare);
  }

}
