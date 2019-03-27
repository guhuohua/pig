package com.pig4cloud.pig.goods.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.goods.api.entity.GoodsEvaluation;
import com.pig4cloud.pig.goods.manage.service.GoodsEvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 商品评价
 *
 * @author 球球
 * @date 2019-02-21 15:35:31
 */
@RestController
@AllArgsConstructor
@RequestMapping("/evaluation")
public class GoodsEvaluationController {

  private final  GoodsEvaluationService goodsEvaluationService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param goodsEvaluation 商品评价
   * @return
   */
  @GetMapping("/page")
  public R<IPage<GoodsEvaluation>> getGoodsEvaluationPage(Page<GoodsEvaluation> page, GoodsEvaluation goodsEvaluation) {
    return  new R<>(goodsEvaluationService.getGoodsEvaluationPage(page,goodsEvaluation));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<GoodsEvaluation> getById(@PathVariable("id") Integer id){
    return new R<>(goodsEvaluationService.getById(id));
  }

  /**
   * 新增记录
   * @param goodsEvaluation
   * @return R
   */
  @SysLog("新增商品评价")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('goods_evaluation_add')")
  public R save(@RequestBody GoodsEvaluation goodsEvaluation){
    return new R<>(goodsEvaluationService.save(goodsEvaluation));
  }

  /**
   * 修改记录
   * @param goodsEvaluation
   * @return R
   */
  @SysLog("修改商品评价")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('goods_evaluation_edit')")
  public R update(@RequestBody GoodsEvaluation goodsEvaluation){
    return new R<>(goodsEvaluationService.updateById(goodsEvaluation));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除商品评价")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('goods_evaluation_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(goodsEvaluationService.removeById(id));
  }

}
