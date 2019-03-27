package com.pig4cloud.pig.goods.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.goods.api.entity.GoodsShare;
import com.pig4cloud.pig.goods.manage.service.GoodsShareService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 商品分享
 *
 * @author 球球
 * @date 2019-02-21 15:35:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/share")
public class GoodsShareController {

  private final  GoodsShareService goodsShareService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param goodsShare 商品分享
   * @return
   */
  @GetMapping("/page")
  public R<IPage<GoodsShare>> getGoodsSharePage(Page<GoodsShare> page, GoodsShare goodsShare) {
    return  new R<>(goodsShareService.getGoodsSharePage(page,goodsShare));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<GoodsShare> getById(@PathVariable("id") Integer id){
    return new R<>(goodsShareService.getById(id));
  }

  /**
   * 新增记录
   * @param goodsShare
   * @return R
   */
  @SysLog("新增商品分享")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('goods_share_add')")
  public R save(@RequestBody GoodsShare goodsShare){
    return new R<>(goodsShareService.save(goodsShare));
  }

  /**
   * 修改记录
   * @param goodsShare
   * @return R
   */
  @SysLog("修改商品分享")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('goods_share_edit')")
  public R update(@RequestBody GoodsShare goodsShare){
    return new R<>(goodsShareService.updateById(goodsShare));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除商品分享")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('goods_share_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(goodsShareService.removeById(id));
  }

}
