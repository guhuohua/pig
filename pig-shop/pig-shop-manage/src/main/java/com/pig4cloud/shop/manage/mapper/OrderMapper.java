package com.pig4cloud.shop.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.shop.api.entity.Order;
import org.apache.ibatis.annotations.Param;

/**
 * 订单设置
 *
 * @author 球球
 * @date 2019-02-21 16:01:55
 */
public interface OrderMapper extends BaseMapper<Order> {
  /**
    * 订单设置简单分页查询
    * @param order 订单设置
    * @return
    */
  IPage<Order> getOrderPage(Page page, @Param("order") Order order);


}
