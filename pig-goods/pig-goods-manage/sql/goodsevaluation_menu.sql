-- 该脚本不要执行，请完善 ID 对应关系,注意层级关系 !!!!

-- 菜单SQL
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '父菜单ID', 'views/pig.goods.api/goodsevaluation/index', '', '0', 'goodsevaluation', 'icon-bangzhushouji', '菜单ID', '0', '2018-01-20 13:17:19', '8', '2018-07-29 13:38:19', '商品评价管理');

-- 菜单对应按钮SQL
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5400', null, 'goods_evaluation_add', '1', null, '1', '5401', '0', '2018-05-15 21:35:18', '0', '2018-07-29 13:38:59', '商品评价新增');
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5400', null, 'goods_evaluation_edit', '1', null, '1', '5402', '0', '2018-05-15 21:35:18', '1', '2018-07-29 13:38:59', '商品评价修改');
insert into `sys_menu` ( `parent_id`, `component`, `permission`, `type`, `path`, `icon`, `menu_id`, `del_flag`, `create_time`, `sort`, `update_time`, `name`)
    values ( '5400', null, 'goods_evaluation_del', '1', null, '1', '5403', '0', '2018-05-15 21:35:18', '2', '2018-07-29 13:38:59', '商品评价删除');
