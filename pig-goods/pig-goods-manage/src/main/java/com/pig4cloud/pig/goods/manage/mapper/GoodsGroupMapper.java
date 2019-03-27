package com.pig4cloud.pig.goods.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.goods.api.entity.GoodsGroup;
import org.apache.ibatis.annotations.Param;

/**
 * 商品分组
 *
 * @author 球球
 * @date 2019-02-21 10:39:43
 */
public interface GoodsGroupMapper extends BaseMapper<GoodsGroup> {
  /**
    * 商品分组简单分页查询
    * @param goodsGroup 商品分组
    * @return
    */
  IPage<GoodsGroup> getGoodsGroupPage(Page page, @Param("goodsGroup") GoodsGroup goodsGroup);


}
