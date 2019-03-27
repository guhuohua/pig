package com.pig4cloud.pig.shop.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.shop.api.entity.OrderDistribution;

/**
 * 包邮设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:37
 */
public interface OrderDistributionService extends IService<OrderDistribution> {

  /**
   * 包邮设置简单分页查询
   * @param orderDistribution 包邮设置
   * @return
   */
  IPage<OrderDistribution> getOrderDistributionPage(Page<OrderDistribution> page, OrderDistribution orderDistribution);


}
