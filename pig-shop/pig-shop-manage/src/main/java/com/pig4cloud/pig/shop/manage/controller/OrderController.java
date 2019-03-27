package com.pig4cloud.pig.shop.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.shop.api.entity.Order;
import com.pig4cloud.pig.shop.manage.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 订单设置
 *
 * @author 球球
 * @date 2019-02-21 16:01:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

  private final  OrderService orderService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param order 订单设置
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Order>> getOrderPage(Page<Order> page, Order order) {
    return  new R<>(orderService.getOrderPage(page,order));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Order> getById(@PathVariable("id") Integer id){
    return new R<>(orderService.getById(id));
  }

  /**
   * 新增记录
   * @param order
   * @return R
   */
  @SysLog("新增订单设置")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('shop_order_add')")
  public R save(@RequestBody Order order){
    return new R<>(orderService.save(order));
  }

  /**
   * 修改记录
   * @param order
   * @return R
   */
  @SysLog("修改订单设置")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('shop_order_edit')")
  public R update(@RequestBody Order order){
    return new R<>(orderService.updateById(order));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除订单设置")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('shop_order_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(orderService.removeById(id));
  }

}
