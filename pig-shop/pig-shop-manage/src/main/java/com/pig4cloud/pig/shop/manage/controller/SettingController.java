package com.pig4cloud.pig.shop.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.shop.api.entity.Setting;
import com.pig4cloud.pig.shop.manage.service.SettingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 多店铺设置
 *
 * @author 球球
 * @date 2019-02-21 16:13:30
 */
@RestController
@AllArgsConstructor
@RequestMapping("/setting")
public class SettingController {

  private final  SettingService settingService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param setting 多店铺设置
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Setting>> getSettingPage(Page<Setting> page, Setting setting) {
    return  new R<>(settingService.getSettingPage(page,setting));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Setting> getById(@PathVariable("id") Integer id){
    return new R<>(settingService.getById(id));
  }

  /**
   * 新增记录
   * @param setting
   * @return R
   */
  @SysLog("新增多店铺设置")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('shop_setting_add')")
  public R save(@RequestBody Setting setting){
    return new R<>(settingService.save(setting));
  }

  /**
   * 修改记录
   * @param setting
   * @return R
   */
  @SysLog("修改多店铺设置")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('shop_setting_edit')")
  public R update(@RequestBody Setting setting){
    return new R<>(settingService.updateById(setting));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除多店铺设置")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('shop_setting_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(settingService.removeById(id));
  }

}
