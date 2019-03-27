package com.pig4cloud.pig.goods.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.goods.api.entity.GoodsImage;
import org.apache.ibatis.annotations.Param;

/**
 * 商品图片
 *
 * @author 球球
 * @date 2019-02-21 15:35:04
 */
public interface GoodsImageMapper extends BaseMapper<GoodsImage> {
  /**
    * 商品图片简单分页查询
    * @param goodsImage 商品图片
    * @return
    */
  IPage<GoodsImage> getGoodsImagePage(Page page, @Param("goodsImage") GoodsImage goodsImage);


}
