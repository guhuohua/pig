package com.pig4cloud.goods.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.goods.api.entity.GoodsImage;
import com.pig4cloud.goods.manage.service.GoodsImageService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 商品图片
 *
 * @author 球球
 * @date 2019-02-21 15:35:04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class GoodsImageController {

  private final GoodsImageService goodsImageService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param goodsImage 商品图片
   * @return
   */
  @GetMapping("/page")
  public R<IPage<GoodsImage>> getGoodsImagePage(Page<GoodsImage> page, GoodsImage goodsImage) {
    return  new R<>(goodsImageService.getGoodsImagePage(page,goodsImage));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<GoodsImage> getById(@PathVariable("id") Integer id){
    return new R<>(goodsImageService.getById(id));
  }

  /**
   * 新增记录
   * @param goodsImage
   * @return R
   */
  @SysLog("新增商品图片")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('goods_image_add')")
  public R save(@RequestBody GoodsImage goodsImage){
    return new R<>(goodsImageService.save(goodsImage));
  }

  /**
   * 修改记录
   * @param goodsImage
   * @return R
   */
  @SysLog("修改商品图片")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('goods_image_edit')")
  public R update(@RequestBody GoodsImage goodsImage){
    return new R<>(goodsImageService.updateById(goodsImage));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除商品图片")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('goods_image_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(goodsImageService.removeById(id));
  }

}
