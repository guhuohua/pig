package com.pig4cloud.goods.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.goods.api.entity.GoodsShare;
import org.apache.ibatis.annotations.Param;

/**
 * 商品分享
 *
 * @author 球球
 * @date 2019-02-21 15:35:49
 */
public interface GoodsShareMapper extends BaseMapper<GoodsShare> {
  /**
    * 商品分享简单分页查询
    * @param goodsShare 商品分享
    * @return
    */
  IPage<GoodsShare> getGoodsSharePage(Page page, @Param("goodsShare") GoodsShare goodsShare);


}
