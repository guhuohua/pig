-- 该脚本不要执行，请完善 ID 对应关系,注意层级关系 !!!!

-- 菜单SQL
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '父菜单ID', 'views/pig.goods.api/goodsimage/index', '', '0', 'goodsimage', 'icon-bangzhushouji', '菜单ID', '0', '2018-01-20 13:17:19', '8', '2018-07-29 13:38:19', '商品图片管理');

-- 菜单对应按钮SQL
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5300', null, 'goods_image_add', '1', null, '1', '5304', '0', '2018-05-15 21:35:18', '0', '2018-07-29 13:38:59', '商品图片新增');
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5300', null, 'goods_image_edit', '1', null, '1', '5305', '0', '2018-05-15 21:35:18', '1', '2018-07-29 13:38:59', '商品图片修改');
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5300', null, 'goods_image_del', '1', null, '1', '5306', '0', '2018-05-15 21:35:18', '2', '2018-07-29 13:38:59', '商品图片删除');
