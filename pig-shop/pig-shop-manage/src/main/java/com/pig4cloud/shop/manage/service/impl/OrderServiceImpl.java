package com.pig4cloud.shop.manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.shop.api.entity.Order;
import com.pig4cloud.shop.manage.mapper.OrderMapper;
import com.pig4cloud.shop.manage.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * 订单设置
 *
 * @author 球球
 * @date 2019-02-21 16:01:55
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

  /**
   * 订单设置简单分页查询
   * @param order 订单设置
   * @return
   */
  @Override
  public IPage<Order> getOrderPage(Page<Order> page, Order order){
      return baseMapper.getOrderPage(page,order);
  }

}
