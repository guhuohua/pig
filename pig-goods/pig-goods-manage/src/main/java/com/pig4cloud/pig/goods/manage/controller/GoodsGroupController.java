package com.pig4cloud.pig.goods.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.goods.api.entity.GoodsGroup;
import com.pig4cloud.pig.goods.manage.service.GoodsGroupService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 商品分组
 *
 * @author 球球
 * @date 2019-02-21 10:39:43
 */
@RestController
@AllArgsConstructor
@RequestMapping("/group")
public class GoodsGroupController {

  private final GoodsGroupService goodsGroupService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param goodsGroup 商品分组
   * @return
   */
  @GetMapping("/page")
  public R<IPage<GoodsGroup>> getGoodsGroupPage(Page<GoodsGroup> page, GoodsGroup goodsGroup) {
    return  new R<>(goodsGroupService.getGoodsGroupPage(page,goodsGroup));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<GoodsGroup> getById(@PathVariable("id") Integer id){
    return new R<>(goodsGroupService.getById(id));
  }

  /**
   * 新增记录
   * @param goodsGroup
   * @return R
   */
  @SysLog("新增商品分组")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('goods_group_add')")
  public R save(@RequestBody GoodsGroup goodsGroup){
    return new R<>(goodsGroupService.save(goodsGroup));
  }

  /**
   * 修改记录
   * @param goodsGroup
   * @return R
   */
  @SysLog("修改商品分组")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('goods_group_edit')")
  public R update(@RequestBody GoodsGroup goodsGroup){
    return new R<>(goodsGroupService.updateById(goodsGroup));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除商品分组")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('goods_group_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(goodsGroupService.removeById(id));
  }

}
