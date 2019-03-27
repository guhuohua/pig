package com.pig4cloud.pig.goods.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.goods.api.entity.GoodsDetails;
import org.apache.ibatis.annotations.Param;

/**
 * 商品详情
 *
 * @author 球球
 * @date 2019-02-21 15:35:24
 */
public interface GoodsDetailsMapper extends BaseMapper<GoodsDetails> {
  /**
    * 商品详情简单分页查询
    * @param goodsDetails 商品详情
    * @return
    */
  IPage<GoodsDetails> getGoodsDetailsPage(Page page, @Param("goodsDetails") GoodsDetails goodsDetails);


}
