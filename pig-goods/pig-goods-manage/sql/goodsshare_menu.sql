-- 该脚本不要执行，请完善 ID 对应关系,注意层级关系 !!!!

-- 菜单SQL
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5000', 'views/pig.goods.api/goodsshare/index', '', '0', 'goodsshare', 'icon-bangzhushouji', '5500', '0', '2018-01-20 13:17:19', '8', '2018-07-29 13:38:19', '商品分享管理');

-- 菜单对应按钮SQL
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5500', null, 'goods_share_add', '1', null, '1', '子按钮ID1', '0', '2018-05-15 21:35:18', '0', '2018-07-29 13:38:59', '商品分享新增');
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5500', null, 'goods_share_edit', '1', null, '1', '子按钮ID2', '0', '2018-05-15 21:35:18', '1', '2018-07-29 13:38:59', '商品分享修改');
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5500', null, 'goods_share_del', '1', null, '1', '子按钮ID3', '0', '2018-05-15 21:35:18', '2', '2018-07-29 13:38:59', '商品分享删除');
