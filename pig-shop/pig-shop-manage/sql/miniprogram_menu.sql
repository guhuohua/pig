-- 该脚本不要执行，请完善 ID 对应关系,注意层级关系 !!!!

-- 菜单SQL
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '父菜单ID', 'views/shop.api/miniprogram/index', '', '0', 'miniprogram', 'icon-bangzhushouji', '菜单ID', '0', '2018-01-20 13:17:19', '8', '2018-07-29 13:38:19', '微信小程序信息 微信小程序信息管理');

-- 菜单对应按钮SQL
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '3300', null, 'shop_miniprogram_add', '1', null, '1', '3301', '0', '2018-05-15 21:35:18', '0', '2018-07-29 13:38:59', '微信小程序信息新增');
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '3300', null, 'shop_miniprogram_edit', '1', null, '1', '3302', '0', '2018-05-15 21:35:18', '1', '2018-07-29 13:38:59', '微信小程序信息修改');
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '3300', null, 'shop_miniprogram_del', '1', null, '1', '3303', '0', '2018-05-15 21:35:18', '2', '2018-07-29 13:38:59', '微信小程序信息删除');
