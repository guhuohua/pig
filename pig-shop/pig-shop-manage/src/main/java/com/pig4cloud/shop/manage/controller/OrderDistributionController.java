package com.pig4cloud.shop.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.shop.api.entity.OrderDistribution;
import com.pig4cloud.shop.manage.service.OrderDistributionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 包邮设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/orderdistribution")
public class OrderDistributionController {

  private final OrderDistributionService orderDistributionService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param orderDistribution 包邮设置
   * @return
   */
  @GetMapping("/page")
  public R<IPage<OrderDistribution>> getOrderDistributionPage(Page<OrderDistribution> page, OrderDistribution orderDistribution) {
    return  new R<>(orderDistributionService.getOrderDistributionPage(page,orderDistribution));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<OrderDistribution> getById(@PathVariable("id") Integer id){
    return new R<>(orderDistributionService.getById(id));
  }

  /**
   * 新增记录
   * @param orderDistribution
   * @return R
   */
  @SysLog("新增包邮设置")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('shop_orderdistribution_add')")
  public R save(@RequestBody OrderDistribution orderDistribution){
    return new R<>(orderDistributionService.save(orderDistribution));
  }

  /**
   * 修改记录
   * @param orderDistribution
   * @return R
   */
  @SysLog("修改包邮设置")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('shop_orderdistribution_edit')")
  public R update(@RequestBody OrderDistribution orderDistribution){
    return new R<>(orderDistributionService.updateById(orderDistribution));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除包邮设置")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('shop_orderdistribution_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(orderDistributionService.removeById(id));
  }

}
