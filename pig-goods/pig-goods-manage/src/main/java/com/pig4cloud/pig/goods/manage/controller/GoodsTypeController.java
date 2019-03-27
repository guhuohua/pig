package com.pig4cloud.pig.goods.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.goods.api.entity.GoodsType;
import com.pig4cloud.pig.goods.manage.service.GoodsTypeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
 * 商品分类信息
 *
 * @author 球球
 * @date 2019-02-21 12:01:02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/type")
public class GoodsTypeController {

	private final GoodsTypeService goodsTypeService;


	/**
	 * 简单分页查询
	 *
	 * @param page      分页对象
	 * @param goodsType 商品分类信息
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<GoodsType>> getGoodsTypePage(Page<GoodsType> page, GoodsType goodsType) {
		return new R<>(goodsTypeService.getGoodsTypePage(page, goodsType));
	}


	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R<GoodsType> getById(@PathVariable("id") Integer id) {
		return new R<>(goodsTypeService.getById(id));
	}


	/**
	 * 返回商品分类信息，顶级商品分类信息
	 *
	 * @return
	 */
	@GetMapping("/list")
	public R getTopTypeList(){
		Integer shopId = SecurityUtils.getLoginUserShopId();
		return R.ok(goodsTypeService.getTypeList(shopId));
	}

	/**
	 * 新增记录
	 *
	 * @param goodsType
	 * @return R
	 */
	@SysLog("新增商品分类信息")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('goods_type_add')")
	public R save(@RequestBody GoodsType goodsType) {
		Integer shopId = SecurityUtils.getLoginUserShopId();
		goodsType.setShopId(shopId);
		goodsType.setStatus(0);
		goodsType.setCreateTime(LocalDateTime.now());
		goodsType.setUpdateTime(LocalDateTime.now());
		goodsType.setDelFlag(0);
		return new R<>(goodsTypeService.save(goodsType));
	}

	/**
	 * 修改记录
	 *
	 * @param goodsType
	 * @return R
	 */
	@SysLog("修改商品分类信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('goods_type_edit')")
	public R update(@RequestBody GoodsType goodsType) {
		goodsType.setUpdateTime(LocalDateTime.now());
		return new R<>(goodsTypeService.updateById(goodsType));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@SysLog("删除商品分类信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('goods_type_del')")
	public R removeById(@PathVariable Integer id) {
		return new R<>(goodsTypeService.removeById(id));
	}



}
