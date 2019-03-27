package com.pig4cloud.pig.goods.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.goods.api.entity.GoodsGroup;
import com.pig4cloud.pig.goods.manage.mapper.GoodsGroupMapper;
import com.pig4cloud.pig.goods.manage.service.GoodsGroupService;
import org.springframework.stereotype.Service;

/**
 * 商品分组
 *
 * @author 球球
 * @date 2019-02-21 10:39:43
 */
@Service("goodsGroupService")
public class GoodsGroupServiceImpl extends ServiceImpl<GoodsGroupMapper, GoodsGroup> implements GoodsGroupService {

  /**
   * 商品分组简单分页查询
   * @param goodsGroup 商品分组
   * @return
   */
  @Override
  public IPage<GoodsGroup> getGoodsGroupPage(Page<GoodsGroup> page, GoodsGroup goodsGroup){
      return baseMapper.getGoodsGroupPage(page,goodsGroup);
  }

}
