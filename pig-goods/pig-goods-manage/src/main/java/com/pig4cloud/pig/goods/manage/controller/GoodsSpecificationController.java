package com.pig4cloud.pig.goods.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.goods.api.entity.GoodsSpecification;
import com.pig4cloud.pig.goods.manage.service.GoodsSpecificationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 商品规格表
 *
 * @author 球球
 * @date 2019-02-21 15:35:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/specification")
public class GoodsSpecificationController {

  private final GoodsSpecificationService goodsSpecificationService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param goodsSpecification 商品规格表
   * @return
   */
  @GetMapping("/page")
  public R<IPage<GoodsSpecification>> getGoodsSpecificationPage(Page<GoodsSpecification> page, GoodsSpecification goodsSpecification) {
    return  new R<>(goodsSpecificationService.getGoodsSpecificationPage(page,goodsSpecification));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<GoodsSpecification> getById(@PathVariable("id") Integer id){
    return new R<>(goodsSpecificationService.getById(id));
  }

  /**
   * 新增记录
   * @param goodsSpecification
   * @return R
   */
  @SysLog("新增商品规格表")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('goods_specification_add')")
  public R save(@RequestBody GoodsSpecification goodsSpecification){
    return new R<>(goodsSpecificationService.save(goodsSpecification));
  }

  /**
   * 修改记录
   * @param goodsSpecification
   * @return R
   */
  @SysLog("修改商品规格表")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('goods_specification_edit')")
  public R update(@RequestBody GoodsSpecification goodsSpecification){
    return new R<>(goodsSpecificationService.updateById(goodsSpecification));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除商品规格表")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('goods_specification_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(goodsSpecificationService.removeById(id));
  }

}
