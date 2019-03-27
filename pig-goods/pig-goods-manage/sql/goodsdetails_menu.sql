-- 该脚本不要执行，请完善 ID 对应关系,注意层级关系 !!!!

-- 菜单SQL
insert into `sys_menu` ( `parent_id`,
 `component`,
  `permission`,
   `type`,
    `path`,
     `icon`,
      `menu_id`,
       `del_flag`,
        `create_time`,
         `sort`,
          `update_time`,
           `name`)
    values ( '父菜单ID',
     'views/pig.goods.api/goodsdetails/index',
      '',
       '0',
        'goodsdetails',
         'icon-bangzhushouji',
          '菜单ID',
           '0',
            '2018-01-20 13:17:19',
             '8',
              '2018-07-29 13:38:19',
               '商品详情管理');

-- 菜单对应按钮SQL
insert into `sys_menu` ( `parent_id`,
 `component`,
  `permission`,
   `type`,
    `path`,
     `icon`,
      `menu_id`,
       `del_flag`,
        `create_time`,
         `sort`,
          `update_time`,
           `name`)
    values ( '5300',
     null,
      'goods_details_add',
       '1',
        null,
         '1',
          '5301',
           '0',
            '2018-05-15 21:35:18',
             '0',
              '2018-07-29 13:38:59',
               '商品详情新增');
insert into `sys_menu` ( `parent_id`,
 `component`,
  `permission`,
   `type`,
    `path`,
     `icon`,
      `menu_id`,
       `del_flag`,
        `create_time`,
         `sort`,
          `update_time`,
           `name`)
    values ( '5300',
     null,
      'goods_details_edit',
       '1',
        null,
         '1',
          '5302',
           '0',
            '2018-05-15 21:35:18',
             '1',
              '2018-07-29 13:38:59',
               '商品详情修改');
insert into `sys_menu` ( `parent_id`,
 `component`,
  `permission`,
   `type`,
    `path`,
     `icon`,
      `menu_id`,
       `del_flag`,
        `create_time`,
         `sort`,
          `update_time`,
           `name`)
    values ( '5300',
     null,
      'goods_details_del',
       '1',
        null,
         '1',
          '5303',
           '0',
            '2018-05-15 21:35:18',
             '2',
              '2018-07-29 13:38:59',
               '商品详情删除');



ALTER TABLE `coupon`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `goods`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `goods_details`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `goods_evaluation`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `goods_group`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `goods_image`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `goods_share`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `goods_specification`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `goods_type`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `member_card`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `member_level`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `order`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `order_attr`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `shop_account`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `shop_global_setting`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `shop_info`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `shop_member_set`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `shop_mini_program`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `shop_order`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `shop_order_distribution`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `shop_setting`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `specification`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `specification_attribute`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `user_address`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `user_info`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `user_member`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `user_tag`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

ALTER TABLE `user_tag_group`
MODIFY COLUMN `id`  int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' FIRST ;

