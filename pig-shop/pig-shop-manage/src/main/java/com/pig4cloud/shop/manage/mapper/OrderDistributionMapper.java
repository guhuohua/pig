package com.pig4cloud.shop.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.shop.api.entity.OrderDistribution;
import org.apache.ibatis.annotations.Param;

/**
 * 包邮设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:37
 */
public interface OrderDistributionMapper extends BaseMapper<OrderDistribution> {
  /**
    * 包邮设置简单分页查询
    * @param orderDistribution 包邮设置
    * @return
    */
  IPage<OrderDistribution> getOrderDistributionPage(Page page, @Param("orderDistribution") OrderDistribution orderDistribution);


}
