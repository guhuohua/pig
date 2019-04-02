/*
Navicat MySQL Data Transfer

Source Server         : company
Source Server Version : 50723
Source Host           : 192.168.1.118:3306
Source Database       : pig

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-04-02 17:05:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `name` varchar(20) DEFAULT NULL COMMENT '优惠券名称',
  `type` int(11) DEFAULT NULL COMMENT '优惠券类型',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  `limit_count` int(11) DEFAULT NULL COMMENT '每人限领张数 0、无限制',
  `goods_group_id` int(11) DEFAULT NULL COMMENT '适用范围 0、无限制，-1、不起用',
  `goods_types_id` int(11) DEFAULT NULL COMMENT '适用分类 0、无限制，-1、不起用',
  `time_type` int(11) DEFAULT NULL COMMENT '时间类型',
  `day` int(11) DEFAULT NULL COMMENT '时长 0、永久',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `limit_money` double DEFAULT NULL COMMENT '满多少可用',
  `preferential` double DEFAULT NULL COMMENT '优惠价格',
  `discount` int(11) DEFAULT NULL COMMENT '折扣 0-100',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠券';

-- ----------------------------
-- Records of coupon
-- ----------------------------

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history` VALUES ('1', '1554104801', 'add init', 'SQL', 'V1554104801__add_init.sql', '0', 'root', '2019-04-01 16:12:21', '10', '1');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `title` varchar(50) DEFAULT NULL COMMENT '商品标题',
  `desc` varchar(200) DEFAULT NULL COMMENT '商品简介',
  `recommend` int(11) DEFAULT NULL COMMENT '推荐状态 0、非推荐，1、推荐中',
  `sale` int(11) DEFAULT NULL COMMENT '出售状态 0、未出售，1、出售中',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `type` int(11) DEFAULT NULL COMMENT '分类',
  `inventory` int(11) DEFAULT NULL COMMENT '库存',
  `original_price` decimal(10,0) DEFAULT NULL COMMENT '原价',
  `present_price` decimal(10,0) DEFAULT NULL COMMENT '现价',
  `sn` varchar(16) DEFAULT NULL COMMENT '商品编码',
  `member_discount` int(11) DEFAULT NULL COMMENT '会员折扣',
  `limit_buy` int(11) DEFAULT NULL COMMENT '限购 为0不限购',
  `sales_volume` int(11) DEFAULT NULL COMMENT '销量',
  `category_id` int(11) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息';

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goods_details
-- ----------------------------
DROP TABLE IF EXISTS `goods_details`;
CREATE TABLE `goods_details` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `details` varchar(4000) DEFAULT NULL COMMENT '描述',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情';

-- ----------------------------
-- Records of goods_details
-- ----------------------------

-- ----------------------------
-- Table structure for goods_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `goods_evaluation`;
CREATE TABLE `goods_evaluation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名称',
  `details` varchar(200) DEFAULT NULL COMMENT '详情',
  `score` int(11) DEFAULT NULL COMMENT '评分 0-5',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评价';

-- ----------------------------
-- Records of goods_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for goods_group
-- ----------------------------
DROP TABLE IF EXISTS `goods_group`;
CREATE TABLE `goods_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `title` varchar(20) DEFAULT NULL COMMENT '分组名称',
  `type` int(11) DEFAULT NULL COMMENT '分类',
  `goods_ids` varchar(4000) DEFAULT NULL COMMENT '商品id',
  `goods_types` varchar(4000) DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分组';

-- ----------------------------
-- Records of goods_group
-- ----------------------------

-- ----------------------------
-- Table structure for goods_image
-- ----------------------------
DROP TABLE IF EXISTS `goods_image`;
CREATE TABLE `goods_image` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品id',
  `url` varchar(100) DEFAULT NULL COMMENT '图片地址',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片';

-- ----------------------------
-- Records of goods_image
-- ----------------------------

-- ----------------------------
-- Table structure for goods_share
-- ----------------------------
DROP TABLE IF EXISTS `goods_share`;
CREATE TABLE `goods_share` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `type` int(11) DEFAULT NULL COMMENT '分享返现',
  `open` int(11) DEFAULT NULL COMMENT '是否开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分享';

-- ----------------------------
-- Records of goods_share
-- ----------------------------

-- ----------------------------
-- Table structure for goods_specification
-- ----------------------------
DROP TABLE IF EXISTS `goods_specification`;
CREATE TABLE `goods_specification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `attrs` varchar(4000) DEFAULT NULL COMMENT '子属性 多个子属性id',
  `original_price` decimal(10,0) DEFAULT NULL COMMENT '原价',
  `present_price` decimal(10,0) DEFAULT NULL COMMENT '现价',
  `inventory` int(11) DEFAULT NULL COMMENT '库存',
  `sn` varchar(16) DEFAULT NULL COMMENT '编码',
  `sale` int(11) DEFAULT NULL COMMENT '销量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格表';

-- ----------------------------
-- Records of goods_specification
-- ----------------------------

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `title` varchar(20) DEFAULT NULL COMMENT '分类标题',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点 0、顶级分类',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `ad` varchar(100) DEFAULT NULL COMMENT '广告url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商品分类信息';

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES ('1', '1', '0', '2019-02-28 18:55:03', '2019-02-28 18:55:05', '0', '新分类', 'http://pig-gateway:8089/image/da5b0bc9dda037923edffccdf523d030.png', '0', '1', 'http://pig-gateway:8089/image/43757b4e29e0f5e087febee8d2c4456f.jpg');
INSERT INTO `goods_type` VALUES ('3', '1', '0', null, null, null, '刚刚', 'http://pig-gateway:8089/image/43757b4e29e0f5e087febee8d2c4456f.jpg', '0', '3', 'http://pig-gateway:8089/image/982329133004c3ca9764b12c9346bba6.png');
INSERT INTO `goods_type` VALUES ('4', '1', '0', null, null, null, '刚刚', 'http://pig-gateway:8089/image/43757b4e29e0f5e087febee8d2c4456f.jpg', '0', '3', 'http://pig-gateway:8089/image/982329133004c3ca9764b12c9346bba6.png');
INSERT INTO `goods_type` VALUES ('5', '1', '0', null, null, null, '非官方的观点', 'http://pig-gateway:8089/image/982329133004c3ca9764b12c9346bba6.png', '0', '3', 'http://pig-gateway:8089/image/43757b4e29e0f5e087febee8d2c4456f.jpg');
INSERT INTO `goods_type` VALUES ('6', '1', '0', null, null, null, '非官方的观点', 'http://pig-gateway:8089/image/982329133004c3ca9764b12c9346bba6.png', '0', '3', 'http://pig-gateway:8089/image/43757b4e29e0f5e087febee8d2c4456f.jpg');
INSERT INTO `goods_type` VALUES ('7', '1', '0', null, null, null, '水电费', 'http://pig-gateway:8089/image/43757b4e29e0f5e087febee8d2c4456f.jpg', '0', '4', 'http://pig-gateway:8089/image/982329133004c3ca9764b12c9346bba6.png');

-- ----------------------------
-- Table structure for member_card
-- ----------------------------
DROP TABLE IF EXISTS `member_card`;
CREATE TABLE `member_card` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `count` int(11) DEFAULT NULL COMMENT '天数',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `discount` int(11) DEFAULT NULL COMMENT '折扣 0-100',
  `max_price` double DEFAULT NULL COMMENT '最大累计额度 0、无上限',
  `free_shipping` int(11) DEFAULT NULL COMMENT '是否包邮',
  `type` int(11) DEFAULT NULL COMMENT '会员卡类型',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员卡设置';

-- ----------------------------
-- Records of member_card
-- ----------------------------

-- ----------------------------
-- Table structure for member_level
-- ----------------------------
DROP TABLE IF EXISTS `member_level`;
CREATE TABLE `member_level` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `up_price` double DEFAULT NULL COMMENT '升级金额 累计升级金额',
  `discount` varchar(4000) DEFAULT NULL COMMENT '折扣 0-100折扣率',
  `free_shipping` int(11) DEFAULT NULL COMMENT '是否包邮 0、不包邮，1、包邮',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员等级';

-- ----------------------------
-- Records of member_level
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `addr_id` int(11) DEFAULT NULL COMMENT '用户地址',
  `goods_id` varchar(4000) DEFAULT NULL COMMENT '商品id',
  `specification_id` varchar(4000) DEFAULT NULL COMMENT '规格id',
  `order_sn` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `oreer_str` varchar(16) DEFAULT NULL COMMENT '随机字符串',
  `all_price` double DEFAULT NULL COMMENT '订单金额',
  `actual_price` double DEFAULT NULL COMMENT '实际支付',
  `shipping_price` double DEFAULT NULL COMMENT '邮费',
  `pay_status` int(11) DEFAULT NULL COMMENT '支付状态',
  `pay_way` int(11) DEFAULT NULL COMMENT '支付方式',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_type` int(11) DEFAULT NULL COMMENT '订单类型',
  `buy_message` varchar(200) DEFAULT NULL COMMENT '买家留言',
  `courier_sn` varchar(32) DEFAULT NULL COMMENT '快递单号',
  `emp_id` int(11) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_attr
-- ----------------------------
DROP TABLE IF EXISTS `order_attr`;
CREATE TABLE `order_attr` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `order_id` int(11) DEFAULT NULL COMMENT '订单编号',
  `key` varchar(20) DEFAULT NULL COMMENT '属性名',
  `value` varchar(100) DEFAULT NULL COMMENT '属性值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单属性';

-- ----------------------------
-- Records of order_attr
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_account_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `title` varchar(20) DEFAULT NULL COMMENT '店铺名称',
  `address` varchar(100) DEFAULT NULL COMMENT '店铺地址',
  `share_title` varchar(20) DEFAULT NULL COMMENT '店铺分享标题',
  `tel` varchar(20) DEFAULT NULL COMMENT '客服电话',
  `service_time` varchar(17) DEFAULT '09:00-19:00' COMMENT '客服时间 客服时间段',
  `logo` varchar(100) DEFAULT NULL COMMENT '店铺logo',
  `share_image` varchar(100) DEFAULT NULL COMMENT '店铺分享标题',
  `status` decimal(1,0) DEFAULT '0' COMMENT '状态 0、正常，1、禁用',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `IDX_shop_info_shop_id` (`shop_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='店铺信息 店铺信息';

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '1', '球球一号店', '湖北武汉市', '球球超级一号店', '13437277575', '14:25-15:25', 'http://pig-gateway:8089/image/982329133004c3ca9764b12c9346bba6.png', 'https://www.inbole.com/image/43757b4e29e0f5e087febee8d2c4456f.jpg', '1', '2019-02-22 10:33:49', '2019-02-22 10:33:53');

-- ----------------------------
-- Table structure for shop_account
-- ----------------------------
DROP TABLE IF EXISTS `shop_account`;
CREATE TABLE `shop_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `flag` varchar(5) DEFAULT NULL COMMENT '标识 标识',
  `desc` varchar(50) DEFAULT NULL COMMENT '描述 描述',
  `status` int(11) DEFAULT NULL COMMENT '状态 状态',
  `del_Flag` int(11) DEFAULT NULL COMMENT '删除标识 删除标识',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间 结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间 创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间 更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商铺账户信息 商铺账户信息';

-- ----------------------------
-- Records of shop_account
-- ----------------------------

-- ----------------------------
-- Table structure for shop_global_setting
-- ----------------------------
DROP TABLE IF EXISTS `shop_global_setting`;
CREATE TABLE `shop_global_setting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `index_recommend` varchar(4000) DEFAULT NULL COMMENT '首页推荐商品 多个用逗号分隔',
  `index_coupon` varchar(4000) DEFAULT NULL COMMENT '首页赠送优惠券 多个用逗号分隔',
  `shopcar_recommend` varchar(4000) DEFAULT NULL COMMENT '购物车推荐商品 多个用逗号分隔',
  `order_recommend` varchar(4000) DEFAULT NULL COMMENT '订单页推荐商品 多个用逗号分隔',
  `search_key` varchar(4000) DEFAULT NULL COMMENT '收索页关键词 多个用逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺全局设置';

-- ----------------------------
-- Records of shop_global_setting
-- ----------------------------

-- ----------------------------
-- Table structure for shop_member_set
-- ----------------------------
DROP TABLE IF EXISTS `shop_member_set`;
CREATE TABLE `shop_member_set` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `up_type` int(11) NOT NULL DEFAULT '0' COMMENT '会员升级方式 0、购物升级，1、付费升级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺会员设置';

-- ----------------------------
-- Records of shop_member_set
-- ----------------------------

-- ----------------------------
-- Table structure for shop_mini_program
-- ----------------------------
DROP TABLE IF EXISTS `shop_mini_program`;
CREATE TABLE `shop_mini_program` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id 店铺id',
  `app_id` varchar(32) DEFAULT NULL COMMENT 'appid',
  `secret` varchar(32) DEFAULT NULL COMMENT 'secret',
  `back_url` varchar(200) DEFAULT NULL COMMENT '地址',
  `mch_idd` varchar(16) DEFAULT NULL COMMENT '商户id',
  `app_key` varchar(32) DEFAULT NULL COMMENT '支付密匙',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `IDX_shop_miram_shop_id3686` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='微信小程序信息 微信小程序信息';

-- ----------------------------
-- Records of shop_mini_program
-- ----------------------------
INSERT INTO `shop_mini_program` VALUES ('1', '1', 'appid', 'asdf', 'www.baidu.com', '100000', 'appkey', '1', '2019-02-22 10:33:49', '2019-02-22 10:33:53');
INSERT INTO `shop_mini_program` VALUES ('2', null, '', '', '', '', '', null, null, null);
INSERT INTO `shop_mini_program` VALUES ('3', null, '', '', '', '', '', null, null, null);
INSERT INTO `shop_mini_program` VALUES ('4', null, '', '', '', '', '', null, null, null);
INSERT INTO `shop_mini_program` VALUES ('5', null, '1', '', '', '', '', null, null, null);

-- ----------------------------
-- Table structure for shop_order
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `free_shipping` double NOT NULL DEFAULT '0' COMMENT '包邮金额 全部免邮费置0即可',
  `order_close_normal` int(11) NOT NULL DEFAULT '120' COMMENT '常规订单关闭时间 单位：分钟',
  `order_close_active` int(11) NOT NULL DEFAULT '15' COMMENT '活动订单关闭时间 单位：分钟',
  `confirm_day` int(11) NOT NULL DEFAULT '3' COMMENT '订单自动确定收货时间 单位：天',
  `template_cancel` varchar(32) DEFAULT NULL COMMENT '订单取消通知',
  `template_delivery` varchar(32) DEFAULT NULL COMMENT '订单发货通知',
  `template_evaluation` varchar(32) DEFAULT NULL COMMENT '订单评价通知',
  `template_complete` varchar(32) DEFAULT NULL COMMENT '订单完成通知',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单设置';

-- ----------------------------
-- Records of shop_order
-- ----------------------------
INSERT INTO `shop_order` VALUES ('1', '1', '1', null, null, '0', '100', '120', '15', '5', '', '', '', '');

-- ----------------------------
-- Table structure for shop_order_distribution
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_distribution`;
CREATE TABLE `shop_order_distribution` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `type_name` varchar(10) DEFAULT NULL COMMENT '配送方式名称',
  `free_shipping` int(11) NOT NULL DEFAULT '0' COMMENT '是否包邮 0、包邮，1、不包邮',
  `valuation` varchar(4000) DEFAULT NULL COMMENT '计价方式 0、按件，1、按重量',
  `min_count` int(11) DEFAULT NULL COMMENT '范围内数量',
  `max_count` int(11) DEFAULT NULL COMMENT '范围外数量',
  `min_price` double DEFAULT NULL COMMENT '范围内价格',
  `max_price` double DEFAULT NULL COMMENT '范围外价格',
  `min_weight` double DEFAULT NULL COMMENT '范围内重量',
  `max_weight` double DEFAULT NULL COMMENT '范围外重量',
  `defaulty` int(11) DEFAULT NULL COMMENT '是否是默认的 0、默认，1、非默认的',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='包邮设置';

-- ----------------------------
-- Records of shop_order_distribution
-- ----------------------------

-- ----------------------------
-- Table structure for shop_setting
-- ----------------------------
DROP TABLE IF EXISTS `shop_setting`;
CREATE TABLE `shop_setting` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `shop_index` int(11) DEFAULT NULL COMMENT '商铺序号',
  `shop_name` varchar(20) DEFAULT NULL COMMENT '商铺名称',
  `shop_address` varchar(200) DEFAULT NULL COMMENT '商铺地址',
  `shop_tel` varchar(16) DEFAULT NULL COMMENT '商铺联系方式',
  `longitude` varchar(16) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(16) DEFAULT NULL COMMENT '纬度',
  `del_flag` varchar(4000) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='多店铺设置';

-- ----------------------------
-- Records of shop_setting
-- ----------------------------

-- ----------------------------
-- Table structure for specification
-- ----------------------------
DROP TABLE IF EXISTS `specification`;
CREATE TABLE `specification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `title` varchar(20) DEFAULT NULL COMMENT '规格名称',
  `type` varchar(20) DEFAULT NULL COMMENT '规格类型',
  `icon` varchar(100) DEFAULT NULL COMMENT '规格icon',
  `sort` int(11) DEFAULT NULL COMMENT '规格排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格';

-- ----------------------------
-- Records of specification
-- ----------------------------

-- ----------------------------
-- Table structure for specification_attribute
-- ----------------------------
DROP TABLE IF EXISTS `specification_attribute`;
CREATE TABLE `specification_attribute` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `specification_id` int(11) DEFAULT NULL COMMENT '规格id',
  `name` varchar(20) DEFAULT NULL COMMENT '属性名称',
  `type` varchar(20) DEFAULT NULL COMMENT '属性类型',
  `icon` varchar(100) DEFAULT NULL COMMENT '属性图标',
  `sort` int(11) DEFAULT NULL COMMENT '属性排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格属性';

-- ----------------------------
-- Records of specification_attribute
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '山东农信', null, '2018-01-22 19:00:23', '2018-09-13 01:46:29', '0', '0');
INSERT INTO `sys_dept` VALUES ('2', '沙县国际', null, '2018-01-22 19:00:38', '2018-09-13 01:46:30', '0', '0');
INSERT INTO `sys_dept` VALUES ('3', '潍坊农信', null, '2018-01-22 19:00:44', '2018-09-13 01:46:31', '0', '1');
INSERT INTO `sys_dept` VALUES ('4', '高新农信', null, '2018-01-22 19:00:52', '2018-10-06 10:41:52', '0', '3');
INSERT INTO `sys_dept` VALUES ('5', '院校农信', '1', '2018-01-22 19:00:57', '2019-02-22 15:12:15', '0', '4');
INSERT INTO `sys_dept` VALUES ('6', '潍院农信', null, '2018-01-22 19:01:06', '2019-01-09 10:58:18', '1', '5');
INSERT INTO `sys_dept` VALUES ('7', '山东沙县', null, '2018-01-22 19:01:57', '2018-09-13 01:46:42', '0', '2');
INSERT INTO `sys_dept` VALUES ('8', '潍坊沙县', null, '2018-01-22 19:02:03', '2018-09-13 01:46:43', '0', '7');
INSERT INTO `sys_dept` VALUES ('9', '高新沙县', null, '2018-01-22 19:02:14', '2018-09-13 01:46:44', '1', '8');
INSERT INTO `sys_dept` VALUES ('10', '租户2', null, '2018-11-18 13:27:11', '2018-11-18 13:42:19', '0', '0');
INSERT INTO `sys_dept` VALUES ('11', '院校沙县', null, '2018-12-10 21:19:26', null, '0', '8');

-- ----------------------------
-- Table structure for sys_dept_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_relation`;
CREATE TABLE `sys_dept_relation` (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`),
  KEY `idx1` (`ancestor`),
  KEY `idx2` (`descendant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='部门关系表';

-- ----------------------------
-- Records of sys_dept_relation
-- ----------------------------
INSERT INTO `sys_dept_relation` VALUES ('1', '1');
INSERT INTO `sys_dept_relation` VALUES ('1', '3');
INSERT INTO `sys_dept_relation` VALUES ('1', '4');
INSERT INTO `sys_dept_relation` VALUES ('1', '5');
INSERT INTO `sys_dept_relation` VALUES ('2', '2');
INSERT INTO `sys_dept_relation` VALUES ('2', '7');
INSERT INTO `sys_dept_relation` VALUES ('2', '8');
INSERT INTO `sys_dept_relation` VALUES ('2', '11');
INSERT INTO `sys_dept_relation` VALUES ('3', '3');
INSERT INTO `sys_dept_relation` VALUES ('3', '4');
INSERT INTO `sys_dept_relation` VALUES ('3', '5');
INSERT INTO `sys_dept_relation` VALUES ('4', '4');
INSERT INTO `sys_dept_relation` VALUES ('4', '5');
INSERT INTO `sys_dept_relation` VALUES ('5', '5');
INSERT INTO `sys_dept_relation` VALUES ('7', '7');
INSERT INTO `sys_dept_relation` VALUES ('7', '8');
INSERT INTO `sys_dept_relation` VALUES ('7', '11');
INSERT INTO `sys_dept_relation` VALUES ('8', '8');
INSERT INTO `sys_dept_relation` VALUES ('8', '11');
INSERT INTO `sys_dept_relation` VALUES ('10', '10');
INSERT INTO `sys_dept_relation` VALUES ('11', '11');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(10) NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`label`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '9', '异常', 'log_type', '日志异常', '1', '2018-07-09 06:16:14', '2018-11-24 07:25:11', '日志异常', '0');
INSERT INTO `sys_dict` VALUES ('2', '0', '正常', 'log_type', '正常', '0', '2018-07-09 06:15:40', '2018-11-24 07:25:14', '正常', '0');
INSERT INTO `sys_dict` VALUES ('3', 'WX', '微信', 'social_type', '微信登录', '0', '2018-08-16 14:01:45', '2018-11-24 07:25:16', '微信登录', '0');
INSERT INTO `sys_dict` VALUES ('4', 'QQ', 'QQ', 'social_type', 'QQ登录', '1', '2018-07-09 06:15:40', '2018-11-24 07:25:18', 'QQ登录', '0');
INSERT INTO `sys_dict` VALUES ('5', '0', '正常', 'log_type', '日志类型', '0', '2018-09-30 02:33:53', '2018-11-24 07:25:20', '日志正常', '0');
INSERT INTO `sys_dict` VALUES ('6', '0', '未提交', 'leave_status', '请假状态', '0', '2018-09-30 02:34:45', '2018-11-24 07:25:23', '请假状态', '0');
INSERT INTO `sys_dict` VALUES ('7', '1', '审批中', 'leave_status', '请假状态', '1', '2018-09-30 02:35:16', '2018-11-24 07:25:25', '请假状态', '0');
INSERT INTO `sys_dict` VALUES ('8', '2', '完成', 'leave_status', '请假状态', '2', '2018-09-30 02:35:58', '2018-11-24 07:25:28', '请假状态', '0');
INSERT INTO `sys_dict` VALUES ('9', '9', '驳回', 'leave_status', '请假状态', '9', '2018-09-30 02:36:31', '2018-11-24 07:25:31', '请假状态', '0');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `time` mediumtext COMMENT '执行时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('51', '0', '添加角色', 'test', 'admin', '2019-01-24 20:56:43', null, '0:0:0:0:0:0:0:1', 'PostmanRuntime/7.6.0', '/role', 'POST', 'Authorization=%5B%5D', '65', '0', null);
INSERT INTO `sys_log` VALUES ('52', '0', '新增菜单', 'pig', 'admin', '2019-02-15 11:59:14', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '9', '0', null);
INSERT INTO `sys_log` VALUES ('53', '0', '更新角色菜单', 'pig', 'admin', '2019-02-15 12:00:21', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C5000%2C5003%2C9999%2C%5D', '30', '0', null);
INSERT INTO `sys_log` VALUES ('54', '0', '新增菜单', 'pig', 'admin', '2019-02-15 14:23:43', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '7', '0', null);
INSERT INTO `sys_log` VALUES ('55', '0', '新增菜单', 'pig', 'admin', '2019-02-15 14:28:51', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '4', '0', null);
INSERT INTO `sys_log` VALUES ('56', '0', '更新角色菜单', 'pig', 'admin', '2019-02-15 14:32:43', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C5000%2C5003%2C9999%2C%5D', '30', '0', null);
INSERT INTO `sys_log` VALUES ('57', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:34:09', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('58', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:34:13', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '11', '0', null);
INSERT INTO `sys_log` VALUES ('59', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:41:23', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '6', '0', null);
INSERT INTO `sys_log` VALUES ('60', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:42:20', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('61', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:42:35', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '4', '0', null);
INSERT INTO `sys_log` VALUES ('62', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:47:22', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('63', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:47:54', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('64', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:48:14', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '8', '0', null);
INSERT INTO `sys_log` VALUES ('65', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:48:41', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('66', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:48:50', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('67', '0', '更新菜单', 'pig', 'admin', '2019-02-15 14:48:56', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('68', '0', '新增菜单', 'pig', 'admin', '2019-02-15 14:57:00', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '8', '0', null);
INSERT INTO `sys_log` VALUES ('69', '0', '更新角色菜单', 'pig', 'admin', '2019-02-15 14:57:18', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3100%2C3200%2C5000%2C5003%2C5200%2C9999%2C3000%5D', '24', '0', null);
INSERT INTO `sys_log` VALUES ('70', '0', '更新角色菜单', 'pig', 'admin', '2019-02-15 15:02:46', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C5000%2C5003%2C5200%2C9999%2C%5D', '22', '0', null);
INSERT INTO `sys_log` VALUES ('71', '0', '更新菜单', 'pig', 'admin', '2019-02-15 15:03:55', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('72', '0', '更新菜单', 'pig', 'admin', '2019-02-15 15:04:02', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('73', '0', '更新菜单', 'pig', 'admin', '2019-02-15 15:04:07', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '3', '0', null);
INSERT INTO `sys_log` VALUES ('74', '0', '更新菜单', 'pig', 'admin', '2019-02-15 15:04:14', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '4', '0', null);
INSERT INTO `sys_log` VALUES ('75', '0', '更新菜单', 'pig', 'admin', '2019-02-15 15:17:54', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '6', '0', null);
INSERT INTO `sys_log` VALUES ('76', '0', '更新菜单', 'pig', 'admin', '2019-02-15 19:02:48', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '34', '0', null);
INSERT INTO `sys_log` VALUES ('77', '0', '更新菜单', 'pig', 'admin', '2019-02-15 19:02:54', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '6', '0', null);
INSERT INTO `sys_log` VALUES ('78', '0', '更新菜单', 'pig', 'admin', '2019-02-15 19:02:58', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '6', '0', null);
INSERT INTO `sys_log` VALUES ('79', '0', '更新菜单', 'pig', 'admin', '2019-02-15 19:03:13', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '6', '0', null);
INSERT INTO `sys_log` VALUES ('80', '0', '更新菜单', 'pig', 'admin', '2019-02-15 19:03:17', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '4', '0', null);
INSERT INTO `sys_log` VALUES ('81', '0', '更新菜单', 'pig', 'admin', '2019-02-15 19:04:06', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '4', '0', null);
INSERT INTO `sys_log` VALUES ('82', '0', '更新菜单', 'pig', 'admin', '2019-02-15 19:05:28', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('83', '0', '新增菜单', 'pig', 'admin', '2019-02-16 14:51:14', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '48', '0', null);
INSERT INTO `sys_log` VALUES ('84', '0', '新增菜单', 'pig', 'admin', '2019-02-16 14:52:09', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '5', '0', null);
INSERT INTO `sys_log` VALUES ('85', '0', '更新角色菜单', 'pig', 'admin', '2019-02-16 14:52:25', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C5000%2C5003%2C5200%2C9999%2C%5D', '61', '0', null);
INSERT INTO `sys_log` VALUES ('86', '0', '更新菜单', 'pig', 'admin', '2019-02-16 14:53:28', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '12', '0', null);
INSERT INTO `sys_log` VALUES ('87', '0', '新增菜单', 'pig', 'admin', '2019-02-16 15:28:37', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '7', '0', null);
INSERT INTO `sys_log` VALUES ('88', '0', '更新角色菜单', 'pig', 'admin', '2019-02-16 15:28:52', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C5000%2C5003%2C5200%2C9999%2C%5D', '36', '0', null);
INSERT INTO `sys_log` VALUES ('89', '0', '新增菜单', 'pig', 'admin', '2019-02-16 15:58:38', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '11', '0', null);
INSERT INTO `sys_log` VALUES ('90', '0', '更新角色菜单', 'pig', 'admin', '2019-02-16 15:58:49', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C5000%2C5003%2C5200%2C9999%2C%5D', '28', '0', null);
INSERT INTO `sys_log` VALUES ('91', '0', '更新菜单', 'pig', 'admin', '2019-02-16 15:59:26', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '13', '0', null);
INSERT INTO `sys_log` VALUES ('92', '0', '新增菜单', 'pig', 'admin', '2019-02-18 14:15:57', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '335', '0', null);
INSERT INTO `sys_log` VALUES ('93', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:16:15', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '274', '0', null);
INSERT INTO `sys_log` VALUES ('94', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:16:22', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '41', '0', null);
INSERT INTO `sys_log` VALUES ('95', '0', '更新角色菜单', 'pig', 'admin', '2019-02-18 14:16:39', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5003%2C5200%2C9999%2C%5D', '236', '0', null);
INSERT INTO `sys_log` VALUES ('96', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:46:05', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '109', '0', null);
INSERT INTO `sys_log` VALUES ('97', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:46:39', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('98', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:47:31', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '0', '0', null);
INSERT INTO `sys_log` VALUES ('99', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:49:21', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '0', '0', null);
INSERT INTO `sys_log` VALUES ('100', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:49:53', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '15', '0', null);
INSERT INTO `sys_log` VALUES ('101', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:52:38', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '63', '0', null);
INSERT INTO `sys_log` VALUES ('102', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:53:11', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('103', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:53:29', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('104', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:53:42', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('105', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:53:58', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('106', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:54:50', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('107', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:55:00', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '94', '0', null);
INSERT INTO `sys_log` VALUES ('108', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:55:06', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('109', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:55:15', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '15', '0', null);
INSERT INTO `sys_log` VALUES ('110', '0', '删除菜单', 'pig', 'admin', '2019-02-18 14:55:25', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu/5500', 'DELETE', '', '62', '0', null);
INSERT INTO `sys_log` VALUES ('111', '0', '更新角色菜单', 'pig', 'admin', '2019-02-18 14:56:19', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5200%2C5300%2C5400%2C9999%2C%5D', '109', '0', null);
INSERT INTO `sys_log` VALUES ('112', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:56:42', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('113', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:58:25', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('114', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:58:49', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('115', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:59:39', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '63', '0', null);
INSERT INTO `sys_log` VALUES ('116', '0', '更新菜单', 'pig', 'admin', '2019-02-18 14:59:53', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '15', '0', null);
INSERT INTO `sys_log` VALUES ('117', '0', '更新菜单', 'pig', 'admin', '2019-02-18 15:00:07', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '15', '0', null);
INSERT INTO `sys_log` VALUES ('118', '0', '新增菜单', 'pig', 'admin', '2019-02-18 16:40:24', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('119', '0', '更新菜单', 'pig', 'admin', '2019-02-18 16:40:44', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('120', '0', '更新角色菜单', 'pig', 'admin', '2019-02-18 16:40:55', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5200%2C5300%2C5400%2C9999%2C%5D', '63', '0', null);
INSERT INTO `sys_log` VALUES ('121', '0', '更新角色菜单', 'pig', 'admin', '2019-02-18 16:48:02', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5200%2C5300%2C5400%2C9999%2C%5D', '78', '0', null);
INSERT INTO `sys_log` VALUES ('122', '0', '更新菜单', 'pig', 'admin', '2019-02-18 16:59:30', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('123', '0', '更新角色菜单', 'pig', 'admin', '2019-02-18 17:00:12', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5200%2C5300%2C5400%2C9999%2C%5D', '78', '0', null);
INSERT INTO `sys_log` VALUES ('124', '0', '更新菜单', 'pig', 'admin', '2019-02-18 17:12:21', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('125', '0', '更新菜单', 'pig', 'admin', '2019-02-18 17:16:34', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '32', '0', null);
INSERT INTO `sys_log` VALUES ('126', '0', '更新菜单', 'pig', 'admin', '2019-02-18 19:00:29', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('127', '0', '更新菜单', 'pig', 'admin', '2019-02-21 14:20:13', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '203', '0', null);
INSERT INTO `sys_log` VALUES ('128', '0', '更新角色菜单', 'pig', 'admin', '2019-02-21 14:20:58', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5200%2C5300%2C5400%2C9999%2C%5D', '172', '0', null);
INSERT INTO `sys_log` VALUES ('129', '0', '新增菜单', 'pig', 'admin', '2019-02-21 15:02:41', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '63', '0', null);
INSERT INTO `sys_log` VALUES ('130', '0', '新增菜单', 'pig', 'admin', '2019-02-21 15:03:44', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '62', '0', null);
INSERT INTO `sys_log` VALUES ('131', '0', '更新菜单', 'pig', 'admin', '2019-02-21 15:03:50', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '32', '0', null);
INSERT INTO `sys_log` VALUES ('132', '0', '更新角色菜单', 'pig', 'admin', '2019-02-21 15:04:06', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5102%2C5103%2C5200%2C5300%2C5400%2C9999%2C%5D', '140', '0', null);
INSERT INTO `sys_log` VALUES ('133', '0', '更新菜单', 'pig', 'admin', '2019-02-21 15:04:36', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('134', '0', '更新角色菜单', 'pig', 'admin', '2019-02-21 15:06:02', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5101%2C5102%2C5200%2C5300%2C5400%2C9999%2C5000%2C5100%5D', '94', '0', null);
INSERT INTO `sys_log` VALUES ('135', '0', '更新角色菜单', 'pig', 'admin', '2019-02-21 15:06:51', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5102%2C5103%2C5200%2C5300%2C5400%2C9999%2C%5D', '156', '0', null);
INSERT INTO `sys_log` VALUES ('136', '0', '更新菜单', 'pig', 'admin', '2019-02-21 18:20:32', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '79', '0', null);
INSERT INTO `sys_log` VALUES ('137', '0', '更新用户信息', 'pig', 'admin', '2019-02-22 15:02:50', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/user', 'PUT', '', '375', '0', null);
INSERT INTO `sys_log` VALUES ('138', '0', '添加用户', 'pig', 'admin', '2019-02-22 15:03:38', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/user', 'POST', '', '125', '0', null);
INSERT INTO `sys_log` VALUES ('139', '0', '更新角色菜单', 'pig', 'admin', '2019-02-22 15:10:48', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B2%5D&menuIds=%5B1400%2C1401%2C1402%2C1403%2C1000%5D', '125', '0', null);
INSERT INTO `sys_log` VALUES ('140', '0', '更新角色菜单', 'pig', 'admin', '2019-02-22 15:11:08', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B2%5D&menuIds=%5B1400%2C1401%2C1402%2C1403%2C3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5102%2C5103%2C5200%2C5300%2C5301%2C5302%2C5303%2C5304%2C5305%2C5306%2C5307%2C5308%2C5309%2C5400%2C5401%2C5402%2C5403%2C5500%2C5501%2C5502%2C5503%2C1000%5D', '62', '0', null);
INSERT INTO `sys_log` VALUES ('141', '0', '编辑部门', 'pig', 'qiu', '2019-02-22 15:12:14', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/dept', 'PUT', '', '94', '0', null);
INSERT INTO `sys_log` VALUES ('142', '0', '更新角色菜单', 'pig', 'admin', '2019-02-22 15:13:48', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B2%5D&menuIds=%5B3000%2C3100%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5102%2C5103%2C5200%2C5300%2C5301%2C5302%2C5303%2C5304%2C5305%2C5306%2C5307%2C5308%2C5309%2C5400%2C5401%2C5402%2C5403%2C5500%2C5501%2C5502%2C5503%2C%5D', '78', '0', null);
INSERT INTO `sys_log` VALUES ('143', '0', '更新菜单', 'pig', 'admin', '2019-02-22 15:25:53', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '46', '0', null);
INSERT INTO `sys_log` VALUES ('144', '0', '更新菜单', 'pig', 'admin', '2019-02-22 15:26:52', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '62', '0', null);
INSERT INTO `sys_log` VALUES ('145', '0', '更新菜单', 'pig', 'admin', '2019-02-22 15:28:51', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('146', '0', '更新菜单', 'pig', 'admin', '2019-02-22 15:40:35', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '109', '0', null);
INSERT INTO `sys_log` VALUES ('147', '0', '更新菜单', 'pig', 'admin', '2019-02-22 15:41:24', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '46', '0', null);
INSERT INTO `sys_log` VALUES ('148', '0', '更新菜单', 'pig', 'admin', '2019-02-22 16:30:09', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '329', '0', null);
INSERT INTO `sys_log` VALUES ('149', '0', '更新菜单', 'pig', 'admin', '2019-02-22 16:30:38', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('150', '0', '新增菜单', 'pig', 'admin', '2019-02-23 11:55:53', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '196', '0', null);
INSERT INTO `sys_log` VALUES ('151', '0', '更新菜单', 'pig', 'admin', '2019-02-23 11:56:22', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '157', '0', null);
INSERT INTO `sys_log` VALUES ('152', '0', '新增菜单', 'pig', 'admin', '2019-02-23 11:56:53', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '65', '0', null);
INSERT INTO `sys_log` VALUES ('153', '0', '更新菜单', 'pig', 'admin', '2019-02-23 11:56:59', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '54', '0', null);
INSERT INTO `sys_log` VALUES ('154', '0', '新增菜单', 'pig', 'admin', '2019-02-23 11:57:28', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'POST', '', '73', '0', null);
INSERT INTO `sys_log` VALUES ('155', '0', '更新角色菜单', 'pig', 'admin', '2019-02-23 11:57:44', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3101%2C3102%2C3103%2C3200%2C3300%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5102%2C5103%2C5200%2C5300%2C5301%2C5302%2C5303%2C5304%2C5305%2C5306%2C5307%2C5308%2C5309%2C5400%2C5401%2C5402%2C5403%2C5500%2C5501%2C5502%2C5503%2C9999%2C%5D', '588', '0', null);
INSERT INTO `sys_log` VALUES ('156', '0', '新增店铺信息 店铺信息', 'pig', 'admin', '2019-02-23 14:31:49', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/info', 'POST', '', '190', '0', null);
INSERT INTO `sys_log` VALUES ('157', '0', '更新菜单', 'pig', 'admin', '2019-02-23 15:52:31', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/menu', 'PUT', '', '58', '0', null);
INSERT INTO `sys_log` VALUES ('158', '0', '更新角色菜单', 'pig', 'admin', '2019-02-23 16:08:45', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3101%2C3102%2C3103%2C3200%2C3300%2C3301%2C3302%2C3303%2C3400%2C3500%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5102%2C5103%2C5200%2C5300%2C5301%2C5302%2C5303%2C5304%2C5305%2C5306%2C5307%2C5308%2C5309%2C5400%2C5401%2C5402%2C5403%2C5500%2C5501%2C5502%2C5503%2C9999%2C%5D', '544', '0', null);
INSERT INTO `sys_log` VALUES ('159', '0', '修改微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-23 16:08:56', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'PUT', '', '92', '0', null);
INSERT INTO `sys_log` VALUES ('160', '0', '修改微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-23 16:09:02', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'PUT', '', '47', '0', null);
INSERT INTO `sys_log` VALUES ('161', '0', '修改微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-23 16:09:12', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'PUT', '', '67', '0', null);
INSERT INTO `sys_log` VALUES ('162', '0', '修改微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-23 16:11:35', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'PUT', '', '80', '0', null);
INSERT INTO `sys_log` VALUES ('163', '0', '修改微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-23 16:11:40', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'PUT', '', '37', '0', null);
INSERT INTO `sys_log` VALUES ('164', '0', '修改微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-23 16:11:42', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'PUT', '', '49', '0', null);
INSERT INTO `sys_log` VALUES ('165', '0', '新增微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-23 16:12:48', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'POST', '', '80', '0', null);
INSERT INTO `sys_log` VALUES ('166', '0', '新增微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-25 11:51:18', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'POST', '', '443', '0', null);
INSERT INTO `sys_log` VALUES ('167', '0', '新增微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-25 11:51:18', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'POST', '', '87', '0', null);
INSERT INTO `sys_log` VALUES ('168', '0', '新增微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-25 11:51:29', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'POST', '', '69', '0', null);
INSERT INTO `sys_log` VALUES ('169', '0', '新增微信小程序信息 微信小程序信息', 'pig', 'admin', '2019-02-25 11:51:39', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/miniprogram', 'POST', '', '72', '0', null);
INSERT INTO `sys_log` VALUES ('170', '0', '更新角色菜单', 'pig', 'admin', '2019-02-25 15:07:55', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B1%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C2000%2C2100%2C2101%2C2200%2C2201%2C2202%2C2203%2C2300%2C2400%2C2401%2C2402%2C2403%2C2500%2C2600%2C2601%2C3000%2C3100%2C3101%2C3102%2C3103%2C3200%2C3300%2C3301%2C3302%2C3303%2C3400%2C3500%2C3501%2C3502%2C3503%2C3600%2C4000%2C4100%2C4200%2C4300%2C4400%2C5000%2C5100%2C5101%2C5102%2C5103%2C5200%2C5300%2C5301%2C5302%2C5303%2C5304%2C5305%2C5306%2C5307%2C5308%2C5309%2C5400%2C5401%2C5402%2C5403%2C5500%2C5501%2C5502%2C5503%2C9999%2C%5D', '581', '0', null);
INSERT INTO `sys_log` VALUES ('171', '0', '新增订单设置', 'pig', 'admin', '2019-02-25 15:08:07', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'POST', '', '89', '0', null);
INSERT INTO `sys_log` VALUES ('172', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:10:51', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '92', '0', null);
INSERT INTO `sys_log` VALUES ('173', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:11:01', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '61', '0', null);
INSERT INTO `sys_log` VALUES ('174', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:11:03', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '71', '0', null);
INSERT INTO `sys_log` VALUES ('175', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:11:04', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '49', '0', null);
INSERT INTO `sys_log` VALUES ('176', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:11:05', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '41', '0', null);
INSERT INTO `sys_log` VALUES ('177', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:11:06', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '36', '0', null);
INSERT INTO `sys_log` VALUES ('178', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:11:27', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '43', '0', null);
INSERT INTO `sys_log` VALUES ('179', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:12:14', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '275', '0', null);
INSERT INTO `sys_log` VALUES ('180', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:12:38', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '41', '0', null);
INSERT INTO `sys_log` VALUES ('181', '0', '修改订单设置', 'pig', 'admin', '2019-02-25 15:13:43', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/order', 'PUT', '', '89', '0', null);
INSERT INTO `sys_log` VALUES ('182', '0', '修改店铺信息 店铺信息', 'pig', 'admin', '2019-02-28 17:15:11', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/info', 'PUT', '', '184', '0', null);
INSERT INTO `sys_log` VALUES ('183', '0', '新增商品分类信息', 'pig', 'admin', '2019-02-28 18:37:20', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'POST', '', '112', '0', null);
INSERT INTO `sys_log` VALUES ('184', '0', '新增商品分类信息', 'pig', 'admin', '2019-02-28 18:56:39', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'POST', '', '50', '0', null);
INSERT INTO `sys_log` VALUES ('185', '0', '修改商品分类信息', 'pig', 'admin', '2019-02-28 18:57:44', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'PUT', '', '60', '0', null);
INSERT INTO `sys_log` VALUES ('186', '0', '修改商品分类信息', 'pig', 'admin', '2019-02-28 18:57:58', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'PUT', '', '71', '0', null);
INSERT INTO `sys_log` VALUES ('187', '0', '修改商品分类信息', 'pig', 'admin', '2019-02-28 18:58:44', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'PUT', '', '31', '0', null);
INSERT INTO `sys_log` VALUES ('188', '0', '修改商品分类信息', 'pig', 'admin', '2019-02-28 18:58:49', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'PUT', '', '126', '0', null);
INSERT INTO `sys_log` VALUES ('189', '0', '修改商品分类信息', 'pig', 'admin', '2019-02-28 19:01:21', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'PUT', '', '7', '0', null);
INSERT INTO `sys_log` VALUES ('190', '0', '新增商品分类信息', 'pig', 'admin', '2019-02-28 19:04:10', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'POST', '', '62', '0', null);
INSERT INTO `sys_log` VALUES ('191', '0', '新增商品分类信息', 'pig', 'admin', '2019-02-28 19:04:19', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'POST', '', '78', '0', null);
INSERT INTO `sys_log` VALUES ('192', '0', '新增商品分类信息', 'pig', 'admin', '2019-02-28 19:05:42', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'POST', '', '58', '0', null);
INSERT INTO `sys_log` VALUES ('193', '0', '新增商品分类信息', 'pig', 'admin', '2019-02-28 19:05:48', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'POST', '', '33', '0', null);
INSERT INTO `sys_log` VALUES ('194', '0', '新增商品分类信息', 'pig', 'admin', '2019-02-28 19:06:31', null, '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36', '/type', 'POST', '', '50', '0', null);
INSERT INTO `sys_log` VALUES ('195', '0', '添加角色', 'pig', 'admin', '2019-02-28 19:20:03', null, '192.168.1.108', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36', '/role', 'POST', '', '45', '0', null);
INSERT INTO `sys_log` VALUES ('196', '0', '添加用户', 'pig', 'admin', '2019-02-28 19:21:00', null, '192.168.1.108', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36', '/user', 'POST', '', '410', '0', null);
INSERT INTO `sys_log` VALUES ('197', '0', '更新角色菜单', 'pig', 'admin', '2019-02-28 19:22:48', null, '192.168.1.108', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36', '/role/menu', 'PUT', 'roleId=%5B3%5D&menuIds=%5B1000%2C1100%2C1101%2C1102%2C1103%2C1200%2C1201%2C1202%2C1203%2C1300%2C1301%2C1302%2C1303%2C1304%2C1400%2C1401%2C1402%2C1403%2C%5D', '379', '0', null);
INSERT INTO `sys_log` VALUES ('198', '0', '新增菜单', 'pig', 'admin', '2019-03-12 11:23:52', null, '192.168.1.118', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36', '/menu', 'POST', '', '203', '0', null);
INSERT INTO `sys_log` VALUES ('199', '0', '删除菜单', 'pig', 'admin', '2019-03-12 11:24:28', null, '192.168.1.118', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.119 Safari/537.36', '/menu/100', 'DELETE', '', '500', '0', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(64) DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `keep_alive` char(1) DEFAULT '0' COMMENT '0-开启，1- 关闭',
  `type` char(1) DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '逻辑删除标记(0--正常 1--删除)',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('100', '标题1', null, null, '-1', null, null, '10', '0', '0', '2019-03-12 11:23:50', '2019-03-12 11:24:28', '1');
INSERT INTO `sys_menu` VALUES ('1000', '权限管理', null, '/upms', '-1', 'icon-quanxianguanli', 'Layout', '0', '0', '0', '2018-09-28 08:29:53', '2018-09-28 08:53:01', '0');
INSERT INTO `sys_menu` VALUES ('1100', '用户管理', null, 'user', '1000', 'icon-yonghuguanli', 'views/admin/user/index', '1', '0', '0', '2017-11-02 22:24:37', '2018-09-28 09:00:41', '0');
INSERT INTO `sys_menu` VALUES ('1101', '用户新增', 'sys_user_add', null, '1100', null, null, null, '0', '1', '2017-11-08 09:52:09', '2018-09-28 09:06:34', '0');
INSERT INTO `sys_menu` VALUES ('1102', '用户修改', 'sys_user_edit', null, '1100', null, null, null, '0', '1', '2017-11-08 09:52:48', '2018-09-28 09:06:37', '0');
INSERT INTO `sys_menu` VALUES ('1103', '用户删除', 'sys_user_del', null, '1100', null, null, null, '0', '1', '2017-11-08 09:54:01', '2018-09-28 09:06:42', '0');
INSERT INTO `sys_menu` VALUES ('1200', '菜单管理', null, 'menu', '1000', 'icon-caidanguanli', 'views/admin/menu/index', '2', '0', '0', '2017-11-08 09:57:27', '2018-09-28 09:00:45', '0');
INSERT INTO `sys_menu` VALUES ('1201', '菜单新增', 'sys_menu_add', null, '1200', null, null, null, '0', '1', '2017-11-08 10:15:53', '2018-09-28 09:07:16', '0');
INSERT INTO `sys_menu` VALUES ('1202', '菜单修改', 'sys_menu_edit', null, '1200', null, null, null, '0', '1', '2017-11-08 10:16:23', '2018-09-28 09:07:18', '0');
INSERT INTO `sys_menu` VALUES ('1203', '菜单删除', 'sys_menu_del', null, '1200', null, null, null, '0', '1', '2017-11-08 10:16:43', '2018-09-28 09:07:22', '0');
INSERT INTO `sys_menu` VALUES ('1300', '角色管理', null, 'role', '1000', 'icon-jiaoseguanli', 'views/admin/role/index', '3', '0', '0', '2017-11-08 10:13:37', '2018-09-28 09:00:48', '0');
INSERT INTO `sys_menu` VALUES ('1301', '角色新增', 'sys_role_add', null, '1300', null, null, null, '0', '1', '2017-11-08 10:14:18', '2018-09-28 09:07:46', '0');
INSERT INTO `sys_menu` VALUES ('1302', '角色修改', 'sys_role_edit', null, '1300', null, null, null, '0', '1', '2017-11-08 10:14:41', '2018-09-28 09:07:49', '0');
INSERT INTO `sys_menu` VALUES ('1303', '角色删除', 'sys_role_del', null, '1300', null, null, null, '0', '1', '2017-11-08 10:14:59', '2018-09-28 09:07:53', '0');
INSERT INTO `sys_menu` VALUES ('1304', '分配权限', 'sys_role_perm', null, '1300', null, null, null, '0', '1', '2018-04-20 07:22:55', '2018-09-28 09:13:23', '0');
INSERT INTO `sys_menu` VALUES ('1400', '部门管理', null, 'dept', '1000', 'icon-web-icon-', 'views/admin/dept/index', '4', '0', '0', '2018-01-20 13:17:19', '2018-12-09 16:35:12', '0');
INSERT INTO `sys_menu` VALUES ('1401', '部门新增', 'sys_dept_add', null, '1400', null, null, null, '0', '1', '2018-01-20 14:56:16', '2018-09-28 09:08:13', '0');
INSERT INTO `sys_menu` VALUES ('1402', '部门修改', 'sys_dept_edit', null, '1400', null, null, null, '0', '1', '2018-01-20 14:56:59', '2018-09-28 09:08:16', '0');
INSERT INTO `sys_menu` VALUES ('1403', '部门删除', 'sys_dept_del', null, '1400', null, null, null, '0', '1', '2018-01-20 14:57:28', '2018-09-28 09:08:18', '0');
INSERT INTO `sys_menu` VALUES ('2000', '系统管理', null, '/admin', '-1', 'icon-xitongguanli', 'Layout', '1', '0', '0', '2017-11-07 20:56:00', '2018-09-28 08:53:18', '0');
INSERT INTO `sys_menu` VALUES ('2100', '日志管理', null, 'log', '2000', 'icon-rizhiguanli', 'views/admin/log/index', '5', '0', '0', '2017-11-20 14:06:22', '2018-09-28 09:01:52', '0');
INSERT INTO `sys_menu` VALUES ('2101', '日志删除', 'sys_log_del', null, '2100', null, null, null, '0', '1', '2017-11-20 20:37:37', '2018-09-28 09:08:44', '0');
INSERT INTO `sys_menu` VALUES ('2200', '字典管理', null, 'dict', '2000', 'icon-navicon-zdgl', 'views/admin/dict/index', '6', '0', '0', '2017-11-29 11:30:52', '2018-09-28 09:01:47', '0');
INSERT INTO `sys_menu` VALUES ('2201', '字典删除', 'sys_dict_del', null, '2200', null, null, null, '0', '1', '2017-11-29 11:30:11', '2018-09-28 09:09:10', '0');
INSERT INTO `sys_menu` VALUES ('2202', '字典新增', 'sys_dict_add', null, '2200', null, null, null, '0', '1', '2018-05-11 22:34:55', '2018-09-28 09:09:12', '0');
INSERT INTO `sys_menu` VALUES ('2203', '字典修改', 'sys_dict_edit', null, '2200', null, null, null, '0', '1', '2018-05-11 22:36:03', '2018-09-28 09:09:16', '0');
INSERT INTO `sys_menu` VALUES ('2300', '代码生成', '', 'gen', '2000', 'icon-weibiaoti46', 'views/gen/index', '8', '0', '0', '2018-01-20 13:17:19', '2018-11-24 05:21:01', '0');
INSERT INTO `sys_menu` VALUES ('2400', '终端管理', '', 'client', '2000', 'icon-shouji', 'views/admin/client/index', '9', '0', '0', '2018-01-20 13:17:19', '2018-09-28 09:01:43', '0');
INSERT INTO `sys_menu` VALUES ('2401', '客户端新增', 'sys_client_add', null, '2400', '1', null, null, '0', '1', '2018-05-15 21:35:18', '2018-09-28 09:10:25', '0');
INSERT INTO `sys_menu` VALUES ('2402', '客户端修改', 'sys_client_edit', null, '2400', null, null, null, '0', '1', '2018-05-15 21:37:06', '2018-09-28 09:10:27', '0');
INSERT INTO `sys_menu` VALUES ('2403', '客户端删除', 'sys_client_del', null, '2400', null, null, null, '0', '1', '2018-05-15 21:39:16', '2018-09-28 09:10:30', '0');
INSERT INTO `sys_menu` VALUES ('2500', '服务监控', null, 'http://pig-gateway:5001', '2000', 'icon-server', null, '10', '0', '0', '2018-06-26 10:50:32', '2019-02-01 20:41:30', '0');
INSERT INTO `sys_menu` VALUES ('2600', '令牌管理', null, 'token', '2000', 'icon-denglvlingpai', 'views/admin/token/index', '11', '0', '0', '2018-09-04 05:58:41', '2018-09-28 09:01:38', '0');
INSERT INTO `sys_menu` VALUES ('2601', '令牌删除', 'sys_token_del', null, '2600', null, null, '1', '0', '1', '2018-09-04 05:59:50', '2018-09-28 09:11:24', '0');
INSERT INTO `sys_menu` VALUES ('3000', '店铺设置', null, '/shop', '-1', 'icon-xitongguanli', 'Layout', '3', '0', '0', '2019-02-15 11:59:14', '2019-02-15 11:59:56', '0');
INSERT INTO `sys_menu` VALUES ('3100', '店铺信息', null, 'info', '3000', 'icon-rizhiguanli', 'views/shop/info', '31', '0', '0', '2019-02-15 14:23:43', '2019-02-15 14:46:32', '0');
INSERT INTO `sys_menu` VALUES ('3101', '商铺信息添加', 'shop_info_add', null, '3100', null, null, '1', '0', '1', '2019-02-23 11:55:53', '2019-02-23 11:56:22', '0');
INSERT INTO `sys_menu` VALUES ('3102', '商铺信息编辑', 'shop_info_edit', null, '3100', null, null, '2', '0', '1', '2019-02-23 11:56:52', null, '0');
INSERT INTO `sys_menu` VALUES ('3103', '商铺信息删除', 'shop_info_del', null, '3100', null, null, '3', '0', '1', '2019-02-23 11:57:28', null, '0');
INSERT INTO `sys_menu` VALUES ('3200', '商铺设置', null, 'setting', '3000', 'icon-navicon-zdgl', 'views/shop/setting', '32', '0', '0', '2019-02-15 14:28:51', '2019-02-15 14:46:32', '0');
INSERT INTO `sys_menu` VALUES ('3300', '小程序设置', null, 'minigrogram', '3000', 'icon-caidanguanli', 'views/shop/miniprogram', '33', '0', '0', '2019-02-15 14:44:36', '2019-02-15 15:05:52', '0');
INSERT INTO `sys_menu` VALUES ('3301', '微信小程序信息新增', 'shop_miniprogram_add', null, '3300', '1', null, '0', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('3302', '微信小程序信息修改', 'shop_miniprogram_edit', null, '3300', '1', null, '1', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('3303', '微信小程序信息删除', 'shop_miniprogram_del', null, '3300', '1', null, '2', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('3400', '会员设置', null, 'members', '3000', 'icon-caidanguanli', 'views/shop/members', '34', '0', '0', '2019-02-15 14:44:27', '2019-02-15 14:46:24', '0');
INSERT INTO `sys_menu` VALUES ('3500', '订单设置', null, 'order', '3000', 'icon-caidanguanli', 'views/shop/order', '35', '0', '0', '2019-02-15 14:44:50', '2019-02-15 15:05:58', '0');
INSERT INTO `sys_menu` VALUES ('3501', '店铺订单设置新增', 'shop_order_add', null, '3500', '1', null, '0', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('3502', '店铺订单设置修改', 'shop_order_edit', null, '3500', '1', null, '1', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('3503', '店铺订单设置删除', 'shop_order_del', null, '3500', '1', null, '2', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('3600', '全局设置', null, 'global', '3000', 'icon-caidanguanli', 'views/shop/global', '36', '0', '0', '2019-02-15 14:43:56', '2019-02-15 15:05:39', '0');
INSERT INTO `sys_menu` VALUES ('4000', '用户管理', '', '/user', '-1', 'icon-xitongguanli', 'Layout', '3', '0', '0', '2019-02-16 14:51:13', null, '0');
INSERT INTO `sys_menu` VALUES ('4100', '用户列表', null, 'list', '4000', 'icon-caidanguanli', 'views/user/list', '41', '0', '0', '2019-02-16 14:52:09', '2019-02-16 14:53:28', '0');
INSERT INTO `sys_menu` VALUES ('4200', '用户标签', null, 'tag', '4000', 'icon-caidanguanli', 'views/user/tag', '42', '0', '0', '2019-02-16 15:28:37', null, '0');
INSERT INTO `sys_menu` VALUES ('4300', '收货地址', null, 'address', '4000', 'icon-caidanguanli', 'views/user/address', '43', '0', '0', '2019-02-16 15:58:38', '2019-02-16 15:59:26', '0');
INSERT INTO `sys_menu` VALUES ('4400', '用户反馈', null, 'feedback', '4000', 'icon-caidanguanli', 'views/user/feedback', '44', '0', '0', '2019-02-18 14:15:56', '2019-02-18 14:16:15', '0');
INSERT INTO `sys_menu` VALUES ('5000', '商品管理', null, '/goods', '-1', 'icon-caidanguanli', 'Layout', '4', '0', '0', '2018-08-28 01:50:22', '2018-09-28 08:58:20', '0');
INSERT INTO `sys_menu` VALUES ('5100', '商品分类', null, 'type/list', '5000', 'icon-caidanguanli', 'views/goods/type/list', '51', '0', '0', '2018-08-28 01:50:48', '2019-02-18 14:51:58', '0');
INSERT INTO `sys_menu` VALUES ('5101', '添加分类', 'goods_type_add', 'type/add', '5100', null, 'views/goods/type/add', '511', '0', '1', '2019-02-18 16:40:24', '2019-02-18 16:40:44', '0');
INSERT INTO `sys_menu` VALUES ('5102', '编辑分类', 'goods_type_edit', null, '5100', null, null, '512', '0', '1', '2019-02-21 15:02:41', '2019-02-21 15:03:50', '0');
INSERT INTO `sys_menu` VALUES ('5103', '删除分类', 'goods_type_del', null, '5100', null, null, '513', '0', '1', '2019-02-21 15:03:44', '2019-02-21 15:04:36', '0');
INSERT INTO `sys_menu` VALUES ('5200', '商品规格', null, 'specification/list', '5000', 'icon-caidanguanli', 'views/goods/specification/list', '52', '0', '0', '2019-02-15 14:57:00', '2019-02-18 14:53:11', '0');
INSERT INTO `sys_menu` VALUES ('5300', '商品管理', null, 'manage/list', '5000', 'icon-caidanguanli', 'views/goods/manage/list', '53', '0', '0', '2018-08-28 01:51:23', '2019-02-18 14:51:58', '0');
INSERT INTO `sys_menu` VALUES ('5301', '商品详情新增', 'goods_details_add', 'goods/details', '5300', '1', null, '0', '0', '1', '2018-05-15 21:35:18', '2019-02-21 17:00:43', '0');
INSERT INTO `sys_menu` VALUES ('5302', '商品详情修改', 'goods_details_edit', 'goods/details', '5300', '1', null, '1', '0', '1', '2018-05-15 21:35:18', '2019-02-21 17:00:43', '0');
INSERT INTO `sys_menu` VALUES ('5303', '商品详情删除', 'goods_details_del', 'goods/details', '5300', '1', null, '2', '0', '1', '2018-05-15 21:35:18', '2019-02-21 17:00:43', '0');
INSERT INTO `sys_menu` VALUES ('5304', '商品图片新增', 'goods_image_add', null, '5300', '1', null, '0', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('5305', '商品图片修改', 'goods_image_edit', null, '5300', '1', null, '1', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('5306', '商品图片删除', 'goods_image_del', null, '5300', '1', null, '2', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('5307', '商品规格表新增', 'goods_specification_add', null, '5300', '1', null, '0', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('5308', '商品规格表修改', 'goods_specification_edit', null, '5300', '1', null, '1', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('5309', '商品规格表删除', 'goods_specification_del', null, '5300', '1', null, '2', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('5400', '商品评价', null, 'evaluation/list', '5000', 'icon-caidanguanli', 'views/goods/evaluation/list', '54', '0', '0', '2018-11-21 17:49:18', '2019-02-18 14:51:37', '0');
INSERT INTO `sys_menu` VALUES ('5401', '商品评价新增', 'goods_evaluation_add', 'goods/evaluation', '5400', '1', null, '0', '0', '1', '2018-05-15 21:35:18', '2019-02-21 17:01:02', '0');
INSERT INTO `sys_menu` VALUES ('5402', '商品评价修改', 'goods_evaluation_edit', 'goods/evaluation', '5400', '1', null, '1', '0', '1', '2018-05-15 21:35:18', '2019-02-21 17:01:02', '0');
INSERT INTO `sys_menu` VALUES ('5403', '商品评价删除', 'goods_evaluation_del', 'goods/evaluation', '5400', '1', null, '2', '0', '1', '2018-05-15 21:35:18', '2019-02-21 17:01:02', '0');
INSERT INTO `sys_menu` VALUES ('5500', '商品分享', '', 'goods/share', '5000', 'icon-bangzhushouji', 'views/goods/share/index', '55', '0', '0', '2018-01-20 13:17:19', '2018-07-29 13:38:19', '0');
INSERT INTO `sys_menu` VALUES ('5501', '商品分享新增', 'goods_share_add', null, '5500', '1', null, '0', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('5502', '商品分享修改', 'goods_share_edit', null, '5500', '1', null, '1', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('5503', '商品分享删除', 'goods_share_del', null, '5500', '1', null, '2', '0', '1', '2018-05-15 21:35:18', '2018-07-29 13:38:59', '0');
INSERT INTO `sys_menu` VALUES ('9999', '系统官网', null, 'https://pig4cloud.com/#/', '-1', 'icon-guanwangfangwen', null, '9', '0', '0', '2019-01-17 17:05:19', '2019-01-17 17:29:06', '0');

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(32) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='终端信息表';

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('app', null, 'app', 'server', 'password,refresh_token', null, null, null, null, null, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('daemon', null, 'daemon', 'server', 'password,refresh_token', null, null, null, null, null, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('gen', null, 'gen', 'server', 'password,refresh_token', null, null, null, null, null, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('pig', null, 'pig', 'server', 'password,refresh_token,authorization_code,client_credentials', 'http://localhost:4040/sso1/login,http://localhost:4041/sso1/login', null, null, null, null, 'true');
INSERT INTO `sys_oauth_client_details` VALUES ('test', null, 'test', 'server', 'password,refresh_token', null, null, null, null, null, 'true');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `role_code` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `role_desc` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_idx1_role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'ROLE_ADMIN', '管理员', '2017-10-29 15:45:51', '2018-12-26 14:09:11', '0');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_CQQ', 'ROLE_CQQ', 'ROLE_CQQ', '2018-11-11 19:42:26', '2018-12-26 14:09:07', '0');
INSERT INTO `sys_role` VALUES ('3', 'admin', 'aaa\'', 'aaa\'', '2019-02-28 19:20:00', null, '0');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL COMMENT '角色ID',
  `dept_id` int(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色与部门对应关系';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES ('1', '1', '8');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1000');
INSERT INTO `sys_role_menu` VALUES ('1', '1100');
INSERT INTO `sys_role_menu` VALUES ('1', '1101');
INSERT INTO `sys_role_menu` VALUES ('1', '1102');
INSERT INTO `sys_role_menu` VALUES ('1', '1103');
INSERT INTO `sys_role_menu` VALUES ('1', '1200');
INSERT INTO `sys_role_menu` VALUES ('1', '1201');
INSERT INTO `sys_role_menu` VALUES ('1', '1202');
INSERT INTO `sys_role_menu` VALUES ('1', '1203');
INSERT INTO `sys_role_menu` VALUES ('1', '1300');
INSERT INTO `sys_role_menu` VALUES ('1', '1301');
INSERT INTO `sys_role_menu` VALUES ('1', '1302');
INSERT INTO `sys_role_menu` VALUES ('1', '1303');
INSERT INTO `sys_role_menu` VALUES ('1', '1304');
INSERT INTO `sys_role_menu` VALUES ('1', '1400');
INSERT INTO `sys_role_menu` VALUES ('1', '1401');
INSERT INTO `sys_role_menu` VALUES ('1', '1402');
INSERT INTO `sys_role_menu` VALUES ('1', '1403');
INSERT INTO `sys_role_menu` VALUES ('1', '2000');
INSERT INTO `sys_role_menu` VALUES ('1', '2100');
INSERT INTO `sys_role_menu` VALUES ('1', '2101');
INSERT INTO `sys_role_menu` VALUES ('1', '2200');
INSERT INTO `sys_role_menu` VALUES ('1', '2201');
INSERT INTO `sys_role_menu` VALUES ('1', '2202');
INSERT INTO `sys_role_menu` VALUES ('1', '2203');
INSERT INTO `sys_role_menu` VALUES ('1', '2300');
INSERT INTO `sys_role_menu` VALUES ('1', '2400');
INSERT INTO `sys_role_menu` VALUES ('1', '2401');
INSERT INTO `sys_role_menu` VALUES ('1', '2402');
INSERT INTO `sys_role_menu` VALUES ('1', '2403');
INSERT INTO `sys_role_menu` VALUES ('1', '2500');
INSERT INTO `sys_role_menu` VALUES ('1', '2600');
INSERT INTO `sys_role_menu` VALUES ('1', '2601');
INSERT INTO `sys_role_menu` VALUES ('1', '3000');
INSERT INTO `sys_role_menu` VALUES ('1', '3100');
INSERT INTO `sys_role_menu` VALUES ('1', '3101');
INSERT INTO `sys_role_menu` VALUES ('1', '3102');
INSERT INTO `sys_role_menu` VALUES ('1', '3103');
INSERT INTO `sys_role_menu` VALUES ('1', '3200');
INSERT INTO `sys_role_menu` VALUES ('1', '3300');
INSERT INTO `sys_role_menu` VALUES ('1', '3301');
INSERT INTO `sys_role_menu` VALUES ('1', '3302');
INSERT INTO `sys_role_menu` VALUES ('1', '3303');
INSERT INTO `sys_role_menu` VALUES ('1', '3400');
INSERT INTO `sys_role_menu` VALUES ('1', '3500');
INSERT INTO `sys_role_menu` VALUES ('1', '3501');
INSERT INTO `sys_role_menu` VALUES ('1', '3502');
INSERT INTO `sys_role_menu` VALUES ('1', '3503');
INSERT INTO `sys_role_menu` VALUES ('1', '3600');
INSERT INTO `sys_role_menu` VALUES ('1', '4000');
INSERT INTO `sys_role_menu` VALUES ('1', '4100');
INSERT INTO `sys_role_menu` VALUES ('1', '4200');
INSERT INTO `sys_role_menu` VALUES ('1', '4300');
INSERT INTO `sys_role_menu` VALUES ('1', '4400');
INSERT INTO `sys_role_menu` VALUES ('1', '5000');
INSERT INTO `sys_role_menu` VALUES ('1', '5100');
INSERT INTO `sys_role_menu` VALUES ('1', '5101');
INSERT INTO `sys_role_menu` VALUES ('1', '5102');
INSERT INTO `sys_role_menu` VALUES ('1', '5103');
INSERT INTO `sys_role_menu` VALUES ('1', '5200');
INSERT INTO `sys_role_menu` VALUES ('1', '5300');
INSERT INTO `sys_role_menu` VALUES ('1', '5301');
INSERT INTO `sys_role_menu` VALUES ('1', '5302');
INSERT INTO `sys_role_menu` VALUES ('1', '5303');
INSERT INTO `sys_role_menu` VALUES ('1', '5304');
INSERT INTO `sys_role_menu` VALUES ('1', '5305');
INSERT INTO `sys_role_menu` VALUES ('1', '5306');
INSERT INTO `sys_role_menu` VALUES ('1', '5307');
INSERT INTO `sys_role_menu` VALUES ('1', '5308');
INSERT INTO `sys_role_menu` VALUES ('1', '5309');
INSERT INTO `sys_role_menu` VALUES ('1', '5400');
INSERT INTO `sys_role_menu` VALUES ('1', '5401');
INSERT INTO `sys_role_menu` VALUES ('1', '5402');
INSERT INTO `sys_role_menu` VALUES ('1', '5403');
INSERT INTO `sys_role_menu` VALUES ('1', '5500');
INSERT INTO `sys_role_menu` VALUES ('1', '5501');
INSERT INTO `sys_role_menu` VALUES ('1', '5502');
INSERT INTO `sys_role_menu` VALUES ('1', '5503');
INSERT INTO `sys_role_menu` VALUES ('1', '9999');
INSERT INTO `sys_role_menu` VALUES ('2', '3000');
INSERT INTO `sys_role_menu` VALUES ('2', '3100');
INSERT INTO `sys_role_menu` VALUES ('2', '3200');
INSERT INTO `sys_role_menu` VALUES ('2', '3300');
INSERT INTO `sys_role_menu` VALUES ('2', '3400');
INSERT INTO `sys_role_menu` VALUES ('2', '3500');
INSERT INTO `sys_role_menu` VALUES ('2', '3600');
INSERT INTO `sys_role_menu` VALUES ('2', '4000');
INSERT INTO `sys_role_menu` VALUES ('2', '4100');
INSERT INTO `sys_role_menu` VALUES ('2', '4200');
INSERT INTO `sys_role_menu` VALUES ('2', '4300');
INSERT INTO `sys_role_menu` VALUES ('2', '4400');
INSERT INTO `sys_role_menu` VALUES ('2', '5000');
INSERT INTO `sys_role_menu` VALUES ('2', '5100');
INSERT INTO `sys_role_menu` VALUES ('2', '5101');
INSERT INTO `sys_role_menu` VALUES ('2', '5102');
INSERT INTO `sys_role_menu` VALUES ('2', '5103');
INSERT INTO `sys_role_menu` VALUES ('2', '5200');
INSERT INTO `sys_role_menu` VALUES ('2', '5300');
INSERT INTO `sys_role_menu` VALUES ('2', '5301');
INSERT INTO `sys_role_menu` VALUES ('2', '5302');
INSERT INTO `sys_role_menu` VALUES ('2', '5303');
INSERT INTO `sys_role_menu` VALUES ('2', '5304');
INSERT INTO `sys_role_menu` VALUES ('2', '5305');
INSERT INTO `sys_role_menu` VALUES ('2', '5306');
INSERT INTO `sys_role_menu` VALUES ('2', '5307');
INSERT INTO `sys_role_menu` VALUES ('2', '5308');
INSERT INTO `sys_role_menu` VALUES ('2', '5309');
INSERT INTO `sys_role_menu` VALUES ('2', '5400');
INSERT INTO `sys_role_menu` VALUES ('2', '5401');
INSERT INTO `sys_role_menu` VALUES ('2', '5402');
INSERT INTO `sys_role_menu` VALUES ('2', '5403');
INSERT INTO `sys_role_menu` VALUES ('2', '5500');
INSERT INTO `sys_role_menu` VALUES ('2', '5501');
INSERT INTO `sys_role_menu` VALUES ('2', '5502');
INSERT INTO `sys_role_menu` VALUES ('2', '5503');
INSERT INTO `sys_role_menu` VALUES ('3', '1000');
INSERT INTO `sys_role_menu` VALUES ('3', '1100');
INSERT INTO `sys_role_menu` VALUES ('3', '1101');
INSERT INTO `sys_role_menu` VALUES ('3', '1102');
INSERT INTO `sys_role_menu` VALUES ('3', '1103');
INSERT INTO `sys_role_menu` VALUES ('3', '1200');
INSERT INTO `sys_role_menu` VALUES ('3', '1201');
INSERT INTO `sys_role_menu` VALUES ('3', '1202');
INSERT INTO `sys_role_menu` VALUES ('3', '1203');
INSERT INTO `sys_role_menu` VALUES ('3', '1300');
INSERT INTO `sys_role_menu` VALUES ('3', '1301');
INSERT INTO `sys_role_menu` VALUES ('3', '1302');
INSERT INTO `sys_role_menu` VALUES ('3', '1303');
INSERT INTO `sys_role_menu` VALUES ('3', '1304');
INSERT INTO `sys_role_menu` VALUES ('3', '1400');
INSERT INTO `sys_role_menu` VALUES ('3', '1401');
INSERT INTO `sys_role_menu` VALUES ('3', '1402');
INSERT INTO `sys_role_menu` VALUES ('3', '1403');

-- ----------------------------
-- Table structure for sys_shop
-- ----------------------------
DROP TABLE IF EXISTS `sys_shop`;
CREATE TABLE `sys_shop` (
  `Id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='账户店铺关系表';

-- ----------------------------
-- Records of sys_shop
-- ----------------------------
INSERT INTO `sys_shop` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `salt` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '简介',
  `avatar` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `lock_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0-正常，9-锁定',
  `del_flag` char(1) COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0-正常，1-删除',
  `wx_openid` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信openid',
  `qq_openid` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'QQ openid',
  PRIMARY KEY (`user_id`),
  KEY `user_wx_openid` (`wx_openid`),
  KEY `user_qq_openid` (`qq_openid`),
  KEY `user_idx1_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$RpFJjxYiXdEsAGnWp/8fsOetMuOON96Ntk/Ym2M/RKRyU0GZseaDC', null, '17034642999', '', '2', '2018-04-20 07:15:18', '2019-02-22 15:50:07', '0', '0', 'o_0FT0uyg_H1vVy2H0JpSwlVGhWQ', null);
INSERT INTO `sys_user` VALUES ('3', 'qiu', '$2a$10$EjEhbLzElL8leED6gwVMp.QRndx.WvcjdXDpvFBP8SyXuROWLd8de', null, '123456', null, '7', '2019-02-22 15:03:38', '2019-02-22 15:50:07', '0', '0', null, null);
INSERT INTO `sys_user` VALUES ('4', 'test', '$2a$10$O7cCBy3CaSLME0hE8nQRU.vV7qy3Ry./d1UYmRyIU1QJ2dZEVcd/i', null, '', null, '1', '2019-02-28 19:21:00', null, '0', '0', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2');
INSERT INTO `sys_user_role` VALUES ('4', '3');

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `name` varchar(16) DEFAULT NULL COMMENT '联系人姓名',
  `tel` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `defaulty` int(11) DEFAULT NULL COMMENT '是否默认的',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址';

-- ----------------------------
-- Records of user_address
-- ----------------------------

-- ----------------------------
-- Table structure for user_delivery_address
-- ----------------------------
DROP TABLE IF EXISTS `user_delivery_address`;
CREATE TABLE `user_delivery_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `is_default` bit(1) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `province_id` bigint(20) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `address_name` varchar(100) DEFAULT NULL COMMENT '地址名称',
  `street` varchar(255) DEFAULT NULL,
  `street_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_delivery_address
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `tel` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别 0、男，1、女',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_member
-- ----------------------------
DROP TABLE IF EXISTS `user_member`;
CREATE TABLE `user_member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `member_level_id` int(11) DEFAULT NULL COMMENT '会员等级id',
  `member_card_id` int(11) DEFAULT NULL COMMENT '会员卡id',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `sum_price` double DEFAULT NULL COMMENT '累计优惠价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户会员信息';

-- ----------------------------
-- Records of user_member
-- ----------------------------

-- ----------------------------
-- Table structure for user_tag
-- ----------------------------
DROP TABLE IF EXISTS `user_tag`;
CREATE TABLE `user_tag` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT NULL COMMENT '删除标识',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `count` int(11) DEFAULT NULL COMMENT '总用户数量',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户标签';

-- ----------------------------
-- Records of user_tag
-- ----------------------------

-- ----------------------------
-- Table structure for user_tag_group
-- ----------------------------
DROP TABLE IF EXISTS `user_tag_group`;
CREATE TABLE `user_tag_group` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `tag_id` int(11) DEFAULT NULL COMMENT '标签id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户标签组';

-- ----------------------------
-- Records of user_tag_group
-- ----------------------------
