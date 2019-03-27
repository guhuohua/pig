package com.pig4cloud.pig.goods.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.goods.api.entity.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息
 *
 * @author 球球
 * @date 2019-02-21 15:15:59
 */
public interface GoodsMapper extends BaseMapper<Goods> {
  /**
    * 商品信息简单分页查询
    * @param goods 商品信息
    * @return
    */
  IPage<Goods> getGoodsPage(Page page, @Param("goods") Goods goods);


}
