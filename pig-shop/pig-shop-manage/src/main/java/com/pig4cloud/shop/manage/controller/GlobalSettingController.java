package com.pig4cloud.shop.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.shop.api.entity.GlobalSetting;
import com.pig4cloud.shop.manage.service.GlobalSettingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 店铺全局设置
 *
 * @author 球球
 * @date 2019-02-21 16:06:48
 */
@RestController
@AllArgsConstructor
@RequestMapping("/globalsetting")
public class GlobalSettingController {

  private final GlobalSettingService globalSettingService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param globalSetting 店铺全局设置
   * @return
   */
  @GetMapping("/page")
  public R<IPage<GlobalSetting>> getGlobalSettingPage(Page<GlobalSetting> page, GlobalSetting globalSetting) {
    return  new R<>(globalSettingService.getGlobalSettingPage(page,globalSetting));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<GlobalSetting> getById(@PathVariable("id") Integer id){
    return new R<>(globalSettingService.getById(id));
  }

  /**
   * 新增记录
   * @param globalSetting
   * @return R
   */
  @SysLog("新增店铺全局设置")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('shop_globalsetting_add')")
  public R save(@RequestBody GlobalSetting globalSetting){
    return new R<>(globalSettingService.save(globalSetting));
  }

  /**
   * 修改记录
   * @param globalSetting
   * @return R
   */
  @SysLog("修改店铺全局设置")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('shop_globalsetting_edit')")
  public R update(@RequestBody GlobalSetting globalSetting){
    return new R<>(globalSettingService.updateById(globalSetting));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除店铺全局设置")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('shop_globalsetting_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(globalSettingService.removeById(id));
  }

}
