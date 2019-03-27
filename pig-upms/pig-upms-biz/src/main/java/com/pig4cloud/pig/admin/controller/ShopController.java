package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.service.SysShopService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.admin.api.entity.SysShop;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 账户店铺关系表
 *
 * @author 球球
 * @date 2019-02-22 18:30:17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shop")
public class ShopController {

  private final SysShopService sysShopService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param sysShop 账户店铺关系表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<SysShop>> getSysShopPage(Page<SysShop> page, SysShop sysShop) {
    return  new R<>(sysShopService.getSysShopPage(page,sysShop));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<SysShop> getById(@PathVariable("id") Integer id){
    return new R<>(sysShopService.getById(id));
  }

  /**
   * 新增记录
   * @param sysShop
   * @return R
   */
  @SysLog("新增账户店铺关系表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('admin.api_sysshop_add')")
  public R save(@RequestBody SysShop sysShop){
    return new R<>(sysShopService.save(sysShop));
  }

  /**
   * 修改记录
   * @param sysShop
   * @return R
   */
  @SysLog("修改账户店铺关系表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('admin.api_sysshop_edit')")
  public R update(@RequestBody SysShop sysShop){
    return new R<>(sysShopService.updateById(sysShop));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除账户店铺关系表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('admin.api_sysshop_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(sysShopService.removeById(id));
  }

}
