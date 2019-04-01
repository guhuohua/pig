package com.pig4cloud.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.shop.api.entity.OrderDistribution;
import com.pig4cloud.shop.manage.mapper.OrderDistributionMapper;
import com.pig4cloud.shop.manage.service.OrderDistributionService;
import org.springframework.stereotype.Service;

/**
 * 包邮设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:37
 */
@Service("orderDistributionService")
public class OrderDistributionServiceImpl extends ServiceImpl<OrderDistributionMapper, OrderDistribution> implements OrderDistributionService {

  /**
   * 包邮设置简单分页查询
   * @param orderDistribution 包邮设置
   * @return
   */
  @Override
  public IPage<OrderDistribution> getOrderDistributionPage(Page<OrderDistribution> page, OrderDistribution orderDistribution){
      return baseMapper.getOrderDistributionPage(page,orderDistribution);
  }

}
