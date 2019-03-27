package com.pig4cloud.pig.goods.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.goods.api.entity.GoodsImage;
import com.pig4cloud.pig.goods.manage.mapper.GoodsImageMapper;
import com.pig4cloud.pig.goods.manage.service.GoodsImageService;
import org.springframework.stereotype.Service;

/**
 * 商品图片
 *
 * @author 球球
 * @date 2019-02-21 15:35:04
 */
@Service("goodsImageService")
public class GoodsImageServiceImpl extends ServiceImpl<GoodsImageMapper, GoodsImage> implements GoodsImageService {

  /**
   * 商品图片简单分页查询
   * @param goodsImage 商品图片
   * @return
   */
  @Override
  public IPage<GoodsImage> getGoodsImagePage(Page<GoodsImage> page, GoodsImage goodsImage){
      return baseMapper.getGoodsImagePage(page,goodsImage);
  }

}
