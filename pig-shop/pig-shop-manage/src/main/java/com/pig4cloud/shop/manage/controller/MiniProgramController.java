package com.pig4cloud.shop.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.shop.api.entity.MiniProgram;
import com.pig4cloud.shop.manage.service.MiniProgramService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 微信小程序信息 微信小程序信息
 *
 * @author 球球
 * @date 2019-02-21 16:07:17
 */
@RestController
@AllArgsConstructor
@RequestMapping("/miniprogram")
public class MiniProgramController {

  private final MiniProgramService miniProgramService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param miniProgram 微信小程序信息 微信小程序信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<MiniProgram>> getMiniProgramPage(Page<MiniProgram> page, MiniProgram miniProgram) {
    return  new R<>(miniProgramService.getMiniProgramPage(page,miniProgram));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<MiniProgram> getById(@PathVariable("id") Integer id){
    return new R<>(miniProgramService.getById(id));
  }

  /**
   * 新增记录
   * @param miniProgram
   * @return R
   */
  @SysLog("新增微信小程序信息 微信小程序信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('shop_miniprogram_add')")
  public R save(@RequestBody MiniProgram miniProgram){
    return new R<>(miniProgramService.save(miniProgram));
  }

  /**
   * 修改记录
   * @param miniProgram
   * @return R
   */
  @SysLog("修改微信小程序信息 微信小程序信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('shop_miniprogram_edit')")
  public R update(@RequestBody MiniProgram miniProgram){
    return new R<>(miniProgramService.updateById(miniProgram));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除微信小程序信息 微信小程序信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('shop_miniprogram_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(miniProgramService.removeById(id));
  }

}
