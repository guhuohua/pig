package com.pig4cloud.shop.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.shop.api.entity.Info;
import com.pig4cloud.shop.manage.service.InfoService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 店铺信息 店铺信息
 *
 * @author 球球
 * @date 2019-02-21 16:06:42
 */
@RestController
@AllArgsConstructor
@RequestMapping("/info")
public class InfoController {

	private final InfoService infoService;

	/**
	 * 简单分页查询
	 *
	 * @param page 分页对象
	 * @param info 店铺信息 店铺信息
	 * @return
	 */
	@GetMapping("/page")
	public R<IPage<Info>> getInfoPage(Page<Info> page, Info info) {
		return new R<>(infoService.getInfoPage(page, info));
	}


	/**
	 * 根据 user 获取 所在的店铺信息
	 */
	@GetMapping
	public R<Info> getByUser() {
		final Integer shopId = SecurityUtils.getLoginUserShopId();
		Info info = infoService.findByShopId(shopId);
		return R.ok(info);
	}

	/**
	 * 通过id查询单条记录
	 *
	 * @param id
	 * @return R
	 */
	@GetMapping("/{id}")
	public R<Info> getById(@PathVariable("id") Integer id) {
		return new R<>(infoService.getById(id));
	}

	/**
	 * 新增记录
	 *
	 * @param info
	 * @return R
	 */
	@SysLog("新增店铺信息 店铺信息")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('shop_info_add')")
	public R save(@RequestBody Info info) {
		return new R<>(infoService.save(info));
	}

	/**
	 * 修改记录
	 *
	 * @param info
	 * @return R
	 */
	@SysLog("修改店铺信息 店铺信息")
	@PutMapping
	@PreAuthorize("@pms.hasPermission('shop_info_edit')")
	public R update(@RequestBody Info info) {
		return new R<>(infoService.updateById(info));
	}

	/**
	 * 通过id删除一条记录
	 *
	 * @param id
	 * @return R
	 */
	@SysLog("删除店铺信息 店铺信息")
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('shop_info_del')")
	public R removeById(@PathVariable Integer id) {
		return new R<>(infoService.removeById(id));
	}

}
