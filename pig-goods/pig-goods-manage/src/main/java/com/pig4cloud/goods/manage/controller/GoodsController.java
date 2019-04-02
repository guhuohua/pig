package com.pig4cloud.goods.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.goods.api.entity.Goods;
import com.pig4cloud.goods.manage.service.GoodsService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 商品信息
 *
 * @author 球球
 * @date 2019-02-21 15:15:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/goods")
public class GoodsController {

  private final GoodsService goodsService;



  

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param goods 商品信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Goods>> getGoodsPage(Page<Goods> page, Goods goods) {
    return  new R<>(goodsService.getGoodsPage(page,goods));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Goods> getById(@PathVariable("id") Integer id){
    return new R<>(goodsService.getById(id));
  }

  /**
   * 新增记录
   * @param goods
   * @return R
   */
  @SysLog("新增商品信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('goods_add')")
  public R save(@RequestBody Goods goods){
    return new R<>(goodsService.save(goods));
  }

  /**
   * 修改记录
   * @param goods
   * @return R
   */
  @SysLog("修改商品信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('goods_edit')")
  public R update(@RequestBody Goods goods){
    return new R<>(goodsService.updateById(goods));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除商品信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('goods_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(goodsService.removeById(id));
  }

}
