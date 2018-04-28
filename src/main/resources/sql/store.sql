/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : store

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-28 16:27:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `dict_type` varchar(50) DEFAULT NULL COMMENT '字典类别',
  `dict_code` varchar(50) DEFAULT NULL COMMENT '字典代码',
  `dict_name` varchar(32) DEFAULT NULL COMMENT '字典名称',
  `remark` varchar(32) DEFAULT NULL COMMENT '备注信息',
  `type_name` varchar(50) DEFAULT NULL COMMENT '字典别名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  KEY `FK_nw2b22gy7plh7pqows186odmq` (`dict_name`) USING BTREE,
  KEY `FK_3q40mr4ebtd0cvx79matl39x1` (`remark`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典内容表';

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES ('4028b88160ba292f0160ba29385f0000', '4028b88160ba0c320160ba0c3b2c0000', '1', '四级', null, null, '2018-01-23 14:51:01', '2018-01-23 14:51:01');
INSERT INTO `dict_type` VALUES ('4028b881611ce30101611ce30d770000', '4028b88160ba0c320160ba0c3b2c0000', '003', '三级', null, null, '2018-01-23 11:34:18', '2018-01-23 11:34:18');
INSERT INTO `dict_type` VALUES ('4028b8816120f501016120f6bec20000', '4028b88161086e070161086e13d70000', '002', '二级', null, null, '2018-01-23 11:25:28', '2018-01-23 11:25:28');
INSERT INTO `dict_type` VALUES ('4028b8816121c884016121cf51490000', '4028b88160ba0c320160ba0c3b2c0000', '111', '11', '2222', null, '2018-01-23 14:58:16', '2018-01-23 14:58:16');

-- ----------------------------
-- Table structure for dict_type_group
-- ----------------------------
DROP TABLE IF EXISTS `dict_type_group`;
CREATE TABLE `dict_type_group` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `typegroupcode` varchar(50) DEFAULT NULL COMMENT '字典分组编码',
  `typegroupname` varchar(50) DEFAULT NULL COMMENT '字典分组名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  KEY `index_typegroupcode` (`typegroupcode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典分组表';

-- ----------------------------
-- Records of dict_type_group
-- ----------------------------
INSERT INTO `dict_type_group` VALUES ('4028b88160ba0c320160ba0c3b2c0000', '0012', '父级类1', '2018-01-22 14:32:56', '2018-01-22 14:32:56');
INSERT INTO `dict_type_group` VALUES ('4028b88161086e070161086e13d70000', '002', '父级类2', '2018-01-18 16:41:27', '2018-01-22 09:14:31');
INSERT INTO `dict_type_group` VALUES ('4028b881611c7d8701611c7f8ea60000', '11', '11', '2018-01-22 15:06:22', '2018-01-22 15:06:22');
INSERT INTO `dict_type_group` VALUES ('4028b881611cb01901611cb0947a0001', '22', '22', '2018-01-22 15:06:29', '2018-01-22 15:06:29');
INSERT INTO `dict_type_group` VALUES ('4028b8816121928d016121b8ba500000', '22222', '22222', '2018-01-23 14:33:29', '2018-01-23 14:33:29');

-- ----------------------------
-- Table structure for hospital_number
-- ----------------------------
DROP TABLE IF EXISTS `hospital_number`;
CREATE TABLE `hospital_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `hos_num` varchar(16) NOT NULL COMMENT '住院号',
  `department` varchar(256) NOT NULL COMMENT '所属科室',
  `build` varchar(128) DEFAULT NULL COMMENT '所属建筑',
  `storey` int(11) DEFAULT NULL COMMENT '所在楼层',
  `room_num` varchar(8) DEFAULT NULL COMMENT '所在房间号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='住院号管理';

-- ----------------------------
-- Records of hospital_number
-- ----------------------------
INSERT INTO `hospital_number` VALUES ('1', '1', '11', '1', '1', '1', '2017-12-27 17:02:07', '2017-12-27 17:02:07');
INSERT INTO `hospital_number` VALUES ('2', '2', '22', '2', '22', '2', '2017-12-27 17:02:28', '2017-12-27 17:02:28');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) NOT NULL COMMENT '菜单名称',
  `pid` int(11) DEFAULT NULL COMMENT '父ID',
  `uri` varchar(128) DEFAULT NULL COMMENT '资源路径',
  `icon` varchar(128) DEFAULT NULL COMMENT '菜单图标',
  `type` tinyint(4) DEFAULT '0' COMMENT '资源类型 0-菜单 1-功能',
  `seq` tinyint(4) DEFAULT NULL COMMENT '资源序列',
  `status` tinyint(4) DEFAULT NULL COMMENT '资源状态',
  `code` varchar(32) NOT NULL COMMENT '唯一标识符',
  `sortno` tinyint(4) DEFAULT NULL COMMENT '资源排序',
  `remark` varchar(128) DEFAULT NULL COMMENT '资源备注',
  `belong` varchar(64) DEFAULT NULL COMMENT '菜单适用人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '设置', '0', 'http://abc.efg.higk/action', 'http://abad.sfsfs.123.pang', '1', '0', '1', 'orderfood', null, '显示设置菜单', null);
INSERT INTO `menu` VALUES ('2', '设置_1', '0', 'http://abc.efg.higk/action_1', 'http://abad.sfsfs.123.pang_1', '1', '0', '1', 'orderfood_1', null, '显示设置菜单_1', null);

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL COMMENT '详情ID',
  `order_id` varchar(32) NOT NULL COMMENT '订单ID',
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(10,0) NOT NULL COMMENT '商品价格',
  `product_quantity` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品详情';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('8a9a398f4a9d4073b111cbf6dd1f7ee2', 'ba6330b37f034fccbce617c5a5148bb9', '201702010', '10元营养套餐', '10', '2', 'http://www.123.com/xx.jpg', '2017-12-26 14:00:35', '2017-12-26 14:00:35');
INSERT INTO `order_detail` VALUES ('a022a779982e49d494464c4e42dae8a0', '7072483bbc1c4ada87db9a909a7e6f41', '201702011', '15元营养套餐', '10', '3', 'http://www.123.com/xx1.jpg', '2017-12-25 16:14:08', '2017-12-25 16:14:08');
INSERT INTO `order_detail` VALUES ('b2cb099f546945f6a17f056dc752d4b7', 'ddc1556350ec4fa997c1b0878f90d184', '201702010', '10元营养套餐', '10', '2', 'http://www.123.com/xx.jpg', '2017-12-25 14:24:32', '2017-12-25 14:24:32');
INSERT INTO `order_detail` VALUES ('e99a4720a08f4cd5a83443b4afc468ae', 'ddc1556350ec4fa997c1b0878f90d184', '201702011', '15元营养套餐', '10', '3', 'http://www.123.com/xx1.jpg', '2017-12-25 14:24:32', '2017-12-25 14:24:32');
INSERT INTO `order_detail` VALUES ('fbb4da3220af4b08b39961069f1150a9', '7072483bbc1c4ada87db9a909a7e6f41', '201702010', '10元营养套餐', '10', '2', 'http://www.123.com/xx.jpg', '2017-12-25 16:14:08', '2017-12-25 16:14:08');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL COMMENT 'ID',
  `buyer_id` varchar(32) NOT NULL COMMENT '买家ID',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家电话',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态,默认为新订单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态,默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('7072483bbc1c4ada87db9a909a7e6f41', '712220', '12345678909', '土屋路 ', 'abc', '50.00', '0', '0', '2017-12-25 16:14:08', '2017-12-25 17:30:06');
INSERT INTO `order_master` VALUES ('ddc1556350ec4fa997c1b0878f90d184', '712220', '12345678909', '土屋路 ', 'abc', '50.00', '0', '1', '2017-12-25 14:24:32', '2017-12-26 09:48:10');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类别名称',
  `category_serial` int(11) NOT NULL COMMENT '类别编号',
  `category_type` tinyint(4) DEFAULT NULL COMMENT '类目分类 1--精致正餐 2-->小炒菜品  ',
  `category_model` tinyint(4) DEFAULT NULL COMMENT '类目类别 1--菜品 2--套餐',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='商品类别';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '推荐套餐', '1', '1', '2', '2018-01-08 09:19:22', '2018-01-29 17:01:29');
INSERT INTO `product_category` VALUES ('2', '荤菜', '2', '1', '1', '2018-01-08 09:19:35', '2018-01-29 17:01:30');
INSERT INTO `product_category` VALUES ('3', '荤素菜', '3', '1', '1', '2018-01-08 09:19:43', '2018-01-29 17:01:30');
INSERT INTO `product_category` VALUES ('4', '素菜', '4', '1', '1', '2018-01-08 09:19:54', '2018-01-29 17:01:31');
INSERT INTO `product_category` VALUES ('5', '主食', '5', '1', '1', '2018-01-08 09:20:01', '2018-01-29 17:01:31');
INSERT INTO `product_category` VALUES ('6', '汤粥', '6', '1', '1', '2018-01-08 09:20:14', '2018-01-29 17:01:33');
INSERT INTO `product_category` VALUES ('10', '荤素菜', '8', '2', '1', '2018-01-08 17:20:28', '2018-01-29 17:01:34');
INSERT INTO `product_category` VALUES ('11', '素菜', '9', '2', '1', '2018-01-08 17:20:44', '2018-01-29 17:01:34');
INSERT INTO `product_category` VALUES ('12', '主食', '11', '2', '1', '2018-01-08 17:20:53', '2018-01-29 17:01:35');

-- ----------------------------
-- Table structure for product_combo
-- ----------------------------
DROP TABLE IF EXISTS `product_combo`;
CREATE TABLE `product_combo` (
  `id` varchar(32) NOT NULL,
  `combo_name` varchar(32) DEFAULT NULL COMMENT '套餐名称',
  `combo_stock` int(11) DEFAULT NULL COMMENT '数量',
  `combo_price` varchar(32) DEFAULT NULL COMMENT '价格',
  `combo_icon` varchar(36) DEFAULT NULL COMMENT '图片',
  `dishes` varchar(500) DEFAULT NULL COMMENT '菜品',
  `soups_broths` varchar(500) DEFAULT NULL COMMENT '汤品',
  `staple_food` varchar(500) DEFAULT NULL COMMENT '主食',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='套餐详情表';

-- ----------------------------
-- Records of product_combo
-- ----------------------------
INSERT INTO `product_combo` VALUES ('1516004247866437957', '糖尿病膳食1', '999', '38~98', '4cfe717f-2d21-4ede-9c10-5f3fb74e4698', '1515490089790414236a,1515490566469650843a,1515651454443312529a', '1515721715823103176a,1515721837277321329a', '1515721512695599842a,1515721603431203393a', '2018-01-18 09:54:08', '2018-01-18 09:54:08');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_stock` int(11) DEFAULT NULL COMMENT '商品库存',
  `product_description` varchar(255) DEFAULT NULL COMMENT '商品详情',
  `product_icon` varchar(36) NOT NULL COMMENT '商品图标',
  `product_status` tinyint(4) DEFAULT '0' COMMENT '商品图标',
  `category_type` int(11) NOT NULL COMMENT '商品类目',
  `if_combo` tinyint(4) DEFAULT NULL COMMENT '是否套餐 0否1是',
  `dishes` varchar(500) DEFAULT NULL,
  `soups_broths` varchar(500) DEFAULT NULL,
  `staple_food` varchar(500) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品信息';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1515490089790414236a', '三汁焖鸡翅', '35.00', '201', '今天这道美味的焖锅。我感觉里面的大蒜、红薯（地瓜）、土豆比鸡翅还美味，这些蔬菜吸收了酱汁及鸡肉的味道，特别是加了黄油，味道更加丰富。在这阴雨绵绵的秋季，来这样一大锅全家人吃的美美的。吃完以后锅内剩下底料加汤后还可以涮土豆、豆皮、青菜、面筋、泡饼等，非常入味。', '5b3e3067-a9d9-4cd4-9bc7-4e4e479bcc73', '0', '2', '0', null, null, null, '2018-01-11 14:43:58', '2018-01-30 14:53:39');
INSERT INTO `product_info` VALUES ('1515490566469650843a', '西红柿炒鸡蛋', '16.00', '50', '西红柿炒鸡蛋', 'be60a7d2-3a5f-4e62-b6a0-589aa968976e', '0', '3', '0', null, null, null, '2018-01-11 15:20:52', '2018-01-30 14:53:39');
INSERT INTO `product_info` VALUES ('1515651454443312529a', '清炒莴笋', '123456.01', '1001', '12313', '4d252539-dcc2-4d77-9d98-5e0ee31dbbbf', '0', '4', '0', null, null, null, '2018-01-11 15:30:34', '2018-01-30 14:53:40');
INSERT INTO `product_info` VALUES ('1515721512695599842a', '米饭', '1.50', '9999', '米饭可与五味调配，几乎可以供给全身所需营养。大米性平、味甘； 有补中益气、健脾养胃、益精强志、和五脏、通血脉、聪耳明目、止烦、止渴、止泻的功效。留有胚与糊粉层的大米饭含有人体90%的必需营养元素，且各种营养素十分均衡，所以是最佳主食。', '09e97dff-f548-4ce3-aaff-730735100645', '0', '5', '0', null, null, null, '2018-01-12 09:45:12', '2018-01-30 14:53:40');
INSERT INTO `product_info` VALUES ('1515721603431203393a', '馒头', '1.00', '9999', '酵后的酵母还是一种很强的抗氧化物，可以保护肝脏，有一定的解毒作用。 酵母里的硒、铬等矿物质能抗衰老、抗肿瘤、预防动脉硬化，并提高人体的免疫力。发酵后，面粉里一种影响钙、镁、铁等元素吸收的植酸可被分解，从而提高人体对这些营养物质的吸收和利用。', '7e9df9d0-9b46-4121-a0b1-d50de1ec464f', '0', '5', '0', null, null, null, '2018-01-12 09:46:43', '2018-01-30 14:53:41');
INSERT INTO `product_info` VALUES ('1515721715823103176a', '冬瓜汤', '10.00', '999', '冬瓜是常见的一种蔬菜，味清甜，多水份。冬瓜是一种药食兼用的蔬菜，具有多种保健功效。', '76e72197-811f-4b71-a2e8-d2e05bab9612', '0', '6', '0', null, null, null, '2018-01-12 09:48:35', '2018-01-30 14:53:42');
INSERT INTO `product_info` VALUES ('1515721837277321329a', '猪肝汤', '15.00', '999', '猪肝中铁质丰富，是补血食品中最常用的食物，食用猪肝可调节和改善贫血病人造血系统的生理功能。', '19b20210-8622-495c-815c-13f9802bfdf3', '0', '6', '0', null, null, null, '2018-01-12 09:50:37', '2018-01-30 14:53:43');
INSERT INTO `product_info` VALUES ('1517562115858683990', '11', '11.00', '111', '111', '0a6d4e36-4b12-4d3e-a928-37e594246b46', '0', '1', '1', '1515721715823103176a,1515651454443312529a,1515721837277321329a,1515721512695599842a,1515490566469650843a', '1515721715823103176a,1515651454443312529a,1515721837277321329a', '1515721715823103176a,1515651454443312529a,1515721837277321329a', '2018-02-02 17:01:55', '2018-02-02 17:01:55');
INSERT INTO `product_info` VALUES ('1524031869071797516', '11', '11.00', '111', '111', 'c194bc40-a366-4ad4-a330-7050f5bddaed', '0', '1', '1', '1515721715823103176a,1515651454443312529a,1515721837277321329a,1515721512695599842a,1515490566469650843a', '1515721715823103176a,1515651454443312529a,1515721837277321329a', '1515721715823103176a,1515651454443312529a,1515721837277321329a', '2018-04-18 14:11:09', '2018-04-18 14:11:09');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(16) NOT NULL COMMENT '角色名称',
  `description` varchar(128) DEFAULT NULL COMMENT '角色说明',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `whether_default` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否默认管理角色 0--是 1--不是',
  `wx_mark` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '管理员', '2018-02-01 15:29:07', '0', '1111');
INSERT INTO `role` VALUES ('2', '销售', '销售', '2018-02-01 15:29:38', '0', '22222');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户资源';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1');
INSERT INTO `role_menu` VALUES ('2', '2', '1');

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户资源';

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('1111', '7', '1');
INSERT INTO `role_user` VALUES ('2222', '7', '2');

-- ----------------------------
-- Table structure for sys_verifycode
-- ----------------------------
DROP TABLE IF EXISTS `sys_verifycode`;
CREATE TABLE `sys_verifycode` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `code` varchar(6) DEFAULT NULL COMMENT '验证码',
  `isuse` int(1) DEFAULT NULL COMMENT '是否应用',
  `type` int(2) DEFAULT NULL COMMENT '验证码类型',
  `EFFECT_MINUTE` int(6) DEFAULT NULL COMMENT '有效时长单位分钟0为无限制',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信验证码';

-- ----------------------------
-- Records of sys_verifycode
-- ----------------------------
INSERT INTO `sys_verifycode` VALUES ('4028b8816125ff0f016125ff18190000', '15863157845', '898989', '0', '11', '5', '2018-01-24 10:41:44', '2018-01-24 10:41:44');

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file` (
  `id` varchar(36) NOT NULL,
  `file_module` varchar(16) NOT NULL COMMENT '业务',
  `file_path` varchar(128) DEFAULT NULL COMMENT '路径',
  `file_type` varchar(8) DEFAULT NULL COMMENT '类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上传文件表';

-- ----------------------------
-- Records of upload_file
-- ----------------------------
INSERT INTO `upload_file` VALUES ('00a49a5d-1112-4b2b-88a1-659951517bc6', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\00a49a5d-1112-4b2b-88a1-659951517bc6.jpg', '.jpg', '2018-01-12 16:07:02', '2018-01-12 16:07:02');
INSERT INTO `upload_file` VALUES ('096cf071-a54e-47b5-9242-17a76b85131b', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\18\\096cf071-a54e-47b5-9242-17a76b85131b.jpg', '.jpg', '2018-01-18 09:51:17', '2018-01-18 09:51:17');
INSERT INTO `upload_file` VALUES ('09e97dff-f548-4ce3-aaff-730735100645', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\09e97dff-f548-4ce3-aaff-730735100645.jpg', '.jpg', '2018-01-12 09:45:10', '2018-01-12 09:45:10');
INSERT INTO `upload_file` VALUES ('0a6d4e36-4b12-4d3e-a928-37e594246b46', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\02\\02\\0a6d4e36-4b12-4d3e-a928-37e594246b46.jpg', '.jpg', '2018-02-02 17:01:54', '2018-02-02 17:01:54');
INSERT INTO `upload_file` VALUES ('1568d188-2ec0-4b07-b824-ebe0a24586c1', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\30\\1568d188-2ec0-4b07-b824-ebe0a24586c1.jpg', '.jpg', '2018-01-30 14:05:46', '2018-01-30 14:05:46');
INSERT INTO `upload_file` VALUES ('171a1792-055d-4fd7-bef5-4cd0fa0e3e11', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\171a1792-055d-4fd7-bef5-4cd0fa0e3e11.jpg', '.jpg', '2018-01-12 16:38:43', '2018-01-12 16:38:43');
INSERT INTO `upload_file` VALUES ('18ad500c-d5fb-40e1-9559-16c18cfa3aac', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\18ad500c-d5fb-40e1-9559-16c18cfa3aac.jpg', '.jpg', '2018-01-12 16:47:26', '2018-01-12 16:47:26');
INSERT INTO `upload_file` VALUES ('1956c44f-6c41-434a-9009-b48b81f74b39', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\1956c44f-6c41-434a-9009-b48b81f74b39.jpg', '.jpg', '2018-01-11 16:38:29', '2018-01-11 16:38:29');
INSERT INTO `upload_file` VALUES ('19b20210-8622-495c-815c-13f9802bfdf3', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\19b20210-8622-495c-815c-13f9802bfdf3.jpg', '.jpg', '2018-01-12 09:50:35', '2018-01-12 09:50:35');
INSERT INTO `upload_file` VALUES ('24c38560-9405-4d80-8cf9-74f35ce1392e', 'productInfo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\24c38560-9405-4d80-8cf9-74f35ce1392e.jpg', '.jpg', '2018-01-12 16:04:09', '2018-01-12 16:04:09');
INSERT INTO `upload_file` VALUES ('25522e42-4a8e-4368-8410-231438128dcd', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\25522e42-4a8e-4368-8410-231438128dcd.jpg', '.jpg', '2018-01-11 14:17:33', '2018-01-11 14:17:33');
INSERT INTO `upload_file` VALUES ('26aab316-62f3-4b1c-9c4f-006e675d5d12', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\26aab316-62f3-4b1c-9c4f-006e675d5d12.jpg', '.jpg', '2018-01-11 14:22:27', '2018-01-11 14:22:27');
INSERT INTO `upload_file` VALUES ('2738d472-6bc9-496a-8bf8-6a0a2ca96e20', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\31\\2738d472-6bc9-496a-8bf8-6a0a2ca96e20.jpg', '.jpg', '2018-01-31 09:21:06', '2018-01-31 09:21:06');
INSERT INTO `upload_file` VALUES ('2bbfb606-3d89-4ac6-93c2-68622621ca31', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\2bbfb606-3d89-4ac6-93c2-68622621ca31.jpg', '.jpg', '2018-01-11 14:31:02', '2018-01-11 14:31:02');
INSERT INTO `upload_file` VALUES ('2c627975-22de-4a9c-a448-99cb281b2590', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\2c627975-22de-4a9c-a448-99cb281b2590.jpg', '.jpg', '2018-01-11 14:20:40', '2018-01-11 14:20:40');
INSERT INTO `upload_file` VALUES ('31ccb156-7988-45c2-afe9-61ac59cc8390', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\31ccb156-7988-45c2-afe9-61ac59cc8390.jpg', '.jpg', '2018-01-12 15:57:48', '2018-01-12 15:57:48');
INSERT INTO `upload_file` VALUES ('375188e6-135b-4551-92bf-f355b0ab4592', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\30\\375188e6-135b-4551-92bf-f355b0ab4592.jpg', '.jpg', '2018-01-30 14:01:51', '2018-01-30 14:01:51');
INSERT INTO `upload_file` VALUES ('376eea10-a95a-442e-b1eb-5dfb30414d36', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\376eea10-a95a-442e-b1eb-5dfb30414d36.jpg', '.jpg', '2018-01-11 14:21:10', '2018-01-11 14:21:10');
INSERT INTO `upload_file` VALUES ('3a1ee0b1-8ff8-4199-9871-e6b1801575ae', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\09\\3a1ee0b1-8ff8-4199-9871-e6b1801575ae.jpg', '.jpg', '2018-01-09 17:34:36', '2018-01-09 17:34:36');
INSERT INTO `upload_file` VALUES ('3f65532c-0ba9-4ebb-abe2-088d4bb8dfc3', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\3f65532c-0ba9-4ebb-abe2-088d4bb8dfc3.jpg', '.jpg', '2018-01-12 16:44:29', '2018-01-12 16:44:29');
INSERT INTO `upload_file` VALUES ('44f7cfeb-5d20-4b1e-8e58-43f917839727', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\44f7cfeb-5d20-4b1e-8e58-43f917839727.jpg', '.jpg', '2018-01-11 16:38:53', '2018-01-11 16:38:53');
INSERT INTO `upload_file` VALUES ('495b9ea8-c96c-4d10-8c51-f99542670896', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\31\\495b9ea8-c96c-4d10-8c51-f99542670896.jpg', '.jpg', '2018-01-31 11:30:23', '2018-01-31 11:30:23');
INSERT INTO `upload_file` VALUES ('4cfe717f-2d21-4ede-9c10-5f3fb74e4698', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\15\\4cfe717f-2d21-4ede-9c10-5f3fb74e4698.jpg', '.jpg', '2018-01-15 16:17:23', '2018-01-15 16:17:23');
INSERT INTO `upload_file` VALUES ('4d252539-dcc2-4d77-9d98-5e0ee31dbbbf', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\4d252539-dcc2-4d77-9d98-5e0ee31dbbbf.jpg', '.jpg', '2018-01-11 16:40:17', '2018-01-11 16:40:17');
INSERT INTO `upload_file` VALUES ('55f8565e-3685-4c28-9afb-cf31c65097ea', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\55f8565e-3685-4c28-9afb-cf31c65097ea.jpg', '.jpg', '2018-01-11 14:17:53', '2018-01-11 14:17:53');
INSERT INTO `upload_file` VALUES ('56307ffe-7979-4017-8953-e98fc9d9ce85', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\56307ffe-7979-4017-8953-e98fc9d9ce85.jpg', '.jpg', '2018-01-12 17:29:43', '2018-01-12 17:29:43');
INSERT INTO `upload_file` VALUES ('5762a652-5da4-42be-ad0c-faaee8daa5b4', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\5762a652-5da4-42be-ad0c-faaee8daa5b4.jpg', '.jpg', '2018-01-11 14:16:47', '2018-01-11 14:16:47');
INSERT INTO `upload_file` VALUES ('59467e9e-5b17-4357-8f84-59b50b9d9ff7', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\59467e9e-5b17-4357-8f84-59b50b9d9ff7.jpg', '.jpg', '2018-01-12 15:44:23', '2018-01-12 15:44:23');
INSERT INTO `upload_file` VALUES ('5a3af539-3e57-4b2e-b2a7-6f9629c12747', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\15\\5a3af539-3e57-4b2e-b2a7-6f9629c12747.jpg', '.jpg', '2018-01-15 13:49:10', '2018-01-15 13:49:10');
INSERT INTO `upload_file` VALUES ('5b3e3067-a9d9-4cd4-9bc7-4e4e479bcc73', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\09\\5b3e3067-a9d9-4cd4-9bc7-4e4e479bcc73.jpg', '.jpg', '2018-01-09 17:27:55', '2018-01-09 17:27:55');
INSERT INTO `upload_file` VALUES ('5bce99d4-70fe-4fbe-a610-2380a0578123', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\5bce99d4-70fe-4fbe-a610-2380a0578123.jpg', '.jpg', '2018-01-11 14:31:59', '2018-01-11 14:31:59');
INSERT INTO `upload_file` VALUES ('5cb3634a-67f0-4ec9-ad95-2609f54603be', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\30\\5cb3634a-67f0-4ec9-ad95-2609f54603be.jpg', '.jpg', '2018-01-30 16:51:58', '2018-01-30 16:51:58');
INSERT INTO `upload_file` VALUES ('5fd3aff0-9984-438b-80cb-87ee7e89351d', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\5fd3aff0-9984-438b-80cb-87ee7e89351d.jpg', '.jpg', '2018-01-11 16:45:32', '2018-01-11 16:45:32');
INSERT INTO `upload_file` VALUES ('639542e1-0555-4d44-a91f-f8afedfdaf6f', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\639542e1-0555-4d44-a91f-f8afedfdaf6f.jpg', '.jpg', '2018-01-11 16:00:21', '2018-01-11 16:00:21');
INSERT INTO `upload_file` VALUES ('76e72197-811f-4b71-a2e8-d2e05bab9612', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\76e72197-811f-4b71-a2e8-d2e05bab9612.jpg', '.jpg', '2018-01-12 09:48:30', '2018-01-12 09:48:30');
INSERT INTO `upload_file` VALUES ('7bcdf63b-abc7-471b-bb6d-24bf9897189c', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\7bcdf63b-abc7-471b-bb6d-24bf9897189c.jpg', '.jpg', '2018-01-11 14:27:07', '2018-01-11 14:27:07');
INSERT INTO `upload_file` VALUES ('7e9df9d0-9b46-4121-a0b1-d50de1ec464f', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\7e9df9d0-9b46-4121-a0b1-d50de1ec464f.jpg', '.jpg', '2018-01-12 09:46:41', '2018-01-12 09:46:41');
INSERT INTO `upload_file` VALUES ('81443902-1b9b-47a0-9269-6bf66bd42a76', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\18\\81443902-1b9b-47a0-9269-6bf66bd42a76.jpg', '.jpg', '2018-01-18 09:55:08', '2018-01-18 09:55:08');
INSERT INTO `upload_file` VALUES ('860efb33-f064-4923-bd39-207b16a2ffb7', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\860efb33-f064-4923-bd39-207b16a2ffb7.jpg', '.jpg', '2018-01-11 16:43:16', '2018-01-11 16:43:16');
INSERT INTO `upload_file` VALUES ('8774a08e-8603-4e46-be66-69a4862ac964', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\8774a08e-8603-4e46-be66-69a4862ac964.jpg', '.jpg', '2018-01-11 14:26:56', '2018-01-11 14:26:56');
INSERT INTO `upload_file` VALUES ('8838ccc5-3a6e-4231-8c00-ae1fbb6cf6ab', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\09\\8838ccc5-3a6e-4231-8c00-ae1fbb6cf6ab.jpg', '.jpg', '2018-01-09 17:30:18', '2018-01-09 17:30:18');
INSERT INTO `upload_file` VALUES ('887e3236-ebdf-4607-a7fd-6fbb6de8b6ac', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\887e3236-ebdf-4607-a7fd-6fbb6de8b6ac.jpg', '.jpg', '2018-01-12 15:58:08', '2018-01-12 15:58:08');
INSERT INTO `upload_file` VALUES ('956c9313-cd6e-4bcf-a4fb-040be6244d39', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\956c9313-cd6e-4bcf-a4fb-040be6244d39.jpg', '.jpg', '2018-01-12 15:57:22', '2018-01-12 15:57:22');
INSERT INTO `upload_file` VALUES ('9576f228-5f04-42f5-b47d-5c3c3149c011', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\9576f228-5f04-42f5-b47d-5c3c3149c011.jpg', '.jpg', '2018-01-11 14:34:00', '2018-01-11 14:34:00');
INSERT INTO `upload_file` VALUES ('9686a1dd-2f8e-4378-aa93-76d14b79ca54', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\9686a1dd-2f8e-4378-aa93-76d14b79ca54.jpg', '.jpg', '2018-01-12 16:17:45', '2018-01-12 16:17:45');
INSERT INTO `upload_file` VALUES ('9ac5256a-12bf-4497-9d4d-bd6dac0d944e', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\9ac5256a-12bf-4497-9d4d-bd6dac0d944e.jpg', '.jpg', '2018-01-11 14:33:10', '2018-01-11 14:33:10');
INSERT INTO `upload_file` VALUES ('9ec802bd-5681-4367-8501-4448a65faebf', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\9ec802bd-5681-4367-8501-4448a65faebf.jpg', '.jpg', '2018-01-11 16:48:26', '2018-01-11 16:48:26');
INSERT INTO `upload_file` VALUES ('a20a74a2-50cd-4e03-854b-4ce61c42fdd2', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\a20a74a2-50cd-4e03-854b-4ce61c42fdd2.jpg', '.jpg', '2018-01-11 14:25:20', '2018-01-11 14:25:20');
INSERT INTO `upload_file` VALUES ('a38fb471-8fa2-43cc-b12d-6617e0002df7', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\29\\a38fb471-8fa2-43cc-b12d-6617e0002df7.jpg', '.jpg', '2018-01-29 15:39:42', '2018-01-29 15:39:42');
INSERT INTO `upload_file` VALUES ('a3fd74bc-fc7f-4e5a-8092-e9a076c5d06b', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\a3fd74bc-fc7f-4e5a-8092-e9a076c5d06b.jpg', '.jpg', '2018-01-11 14:18:48', '2018-01-11 14:18:48');
INSERT INTO `upload_file` VALUES ('ab130988-2aaf-4849-a436-2ca6e9a72075', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\31\\ab130988-2aaf-4849-a436-2ca6e9a72075.jpg', '.jpg', '2018-01-31 11:29:22', '2018-01-31 11:29:22');
INSERT INTO `upload_file` VALUES ('ab686840-0130-4fc4-8ad2-f7c52ef22c1a', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\30\\ab686840-0130-4fc4-8ad2-f7c52ef22c1a.jpg', '.jpg', '2018-01-30 14:01:02', '2018-01-30 14:01:02');
INSERT INTO `upload_file` VALUES ('b695fc86-8771-4480-8224-c486f1b5239d', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\b695fc86-8771-4480-8224-c486f1b5239d.jpg', '.jpg', '2018-01-12 16:35:44', '2018-01-12 16:35:44');
INSERT INTO `upload_file` VALUES ('b8eda115-d35a-4e92-bfcf-484576701fa9', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\09\\b8eda115-d35a-4e92-bfcf-484576701fa9.jpg', '.jpg', '2018-01-09 17:31:59', '2018-01-09 17:31:59');
INSERT INTO `upload_file` VALUES ('bb36a07a-8142-469b-b5cd-e9dfe61442d1', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\bb36a07a-8142-469b-b5cd-e9dfe61442d1.jpg', '.jpg', '2018-01-11 14:25:42', '2018-01-11 14:25:42');
INSERT INTO `upload_file` VALUES ('bbc07693-b54a-4e21-9c30-5976bf02a791', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\bbc07693-b54a-4e21-9c30-5976bf02a791.jpg', '.jpg', '2018-01-11 14:32:24', '2018-01-11 14:32:24');
INSERT INTO `upload_file` VALUES ('bc290200-9243-4443-b355-ec606dd0f968', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\bc290200-9243-4443-b355-ec606dd0f968.jpg', '.jpg', '2018-01-12 15:58:55', '2018-01-12 15:58:55');
INSERT INTO `upload_file` VALUES ('bcee0924-3d1b-4835-aa36-5e4ac6d97305', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\31\\bcee0924-3d1b-4835-aa36-5e4ac6d97305.jpg', '.jpg', '2018-01-31 09:22:16', '2018-01-31 09:22:16');
INSERT INTO `upload_file` VALUES ('be3615c7-85be-47b9-9a4c-2110b6872b01', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\10\\be3615c7-85be-47b9-9a4c-2110b6872b01.jpg', '.jpg', '2018-01-10 15:16:26', '2018-01-10 15:16:26');
INSERT INTO `upload_file` VALUES ('be60a7d2-3a5f-4e62-b6a0-589aa968976e', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\12\\be60a7d2-3a5f-4e62-b6a0-589aa968976e.jpg', '.jpg', '2018-01-12 16:22:45', '2018-01-12 16:22:45');
INSERT INTO `upload_file` VALUES ('c194bc40-a366-4ad4-a330-7050f5bddaed', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\04\\18\\c194bc40-a366-4ad4-a330-7050f5bddaed.jpg', '.jpg', '2018-04-18 14:11:05', '2018-04-18 14:11:05');
INSERT INTO `upload_file` VALUES ('c3688755-62b5-4224-a8e5-88e9c16295ff', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\c3688755-62b5-4224-a8e5-88e9c16295ff.jpg', '.jpg', '2018-01-12 16:47:32', '2018-01-12 16:47:32');
INSERT INTO `upload_file` VALUES ('ca482cd2-9e92-40dd-a4a5-12dcdf2a4a18', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\31\\ca482cd2-9e92-40dd-a4a5-12dcdf2a4a18.jpg', '.jpg', '2018-01-31 11:30:28', '2018-01-31 11:30:28');
INSERT INTO `upload_file` VALUES ('cbe1b03b-969d-48b7-a054-e40630cfca0e', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\15\\cbe1b03b-969d-48b7-a054-e40630cfca0e.jpg', '.jpg', '2018-01-15 08:58:03', '2018-01-15 08:58:03');
INSERT INTO `upload_file` VALUES ('cf949c04-7de7-49a3-aaff-687d56f2cbd2', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\cf949c04-7de7-49a3-aaff-687d56f2cbd2.jpg', '.jpg', '2018-01-11 14:21:23', '2018-01-11 14:21:23');
INSERT INTO `upload_file` VALUES ('d1919508-b357-4283-b167-1cd3a2b9b320', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\d1919508-b357-4283-b167-1cd3a2b9b320.jpg', '.jpg', '2018-01-11 14:35:49', '2018-01-11 14:35:49');
INSERT INTO `upload_file` VALUES ('d74a4056-0458-4760-9fd5-54ba1226d2a0', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\d74a4056-0458-4760-9fd5-54ba1226d2a0.jpg', '.jpg', '2018-01-11 14:21:57', '2018-01-11 14:21:57');
INSERT INTO `upload_file` VALUES ('d813a5f4-fc0b-4824-a4fe-579b3a79c408', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\d813a5f4-fc0b-4824-a4fe-579b3a79c408.jpg', '.jpg', '2018-01-11 14:28:01', '2018-01-11 14:28:01');
INSERT INTO `upload_file` VALUES ('df071c2c-9556-4695-9970-300dd6ea9a03', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\df071c2c-9556-4695-9970-300dd6ea9a03.jpg', '.jpg', '2018-01-12 16:48:06', '2018-01-12 16:48:06');
INSERT INTO `upload_file` VALUES ('df45e464-86dd-4397-9d7d-421c6c1497d9', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\10\\df45e464-86dd-4397-9d7d-421c6c1497d9.jpg', '.jpg', '2018-01-10 15:19:26', '2018-01-10 15:19:26');
INSERT INTO `upload_file` VALUES ('dfd5402a-7f67-4840-b5cb-a8014e547830', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\dfd5402a-7f67-4840-b5cb-a8014e547830.jpg', '.jpg', '2018-01-11 16:46:51', '2018-01-11 16:46:51');
INSERT INTO `upload_file` VALUES ('e2bc4ba6-56bf-4be3-be32-adf141cdfbab', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\e2bc4ba6-56bf-4be3-be32-adf141cdfbab.jpg', '.jpg', '2018-01-11 14:29:13', '2018-01-11 14:29:13');
INSERT INTO `upload_file` VALUES ('e5189a04-1bb1-4c25-a19b-dabe3fda2c98', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\09\\e5189a04-1bb1-4c25-a19b-dabe3fda2c98.jpg', '.jpg', '2018-01-09 17:33:28', '2018-01-09 17:33:28');
INSERT INTO `upload_file` VALUES ('ea979f44-8f3b-42b5-95d0-31927addbfdf', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\30\\ea979f44-8f3b-42b5-95d0-31927addbfdf.jpg', '.jpg', '2018-01-30 14:19:10', '2018-01-30 14:19:10');
INSERT INTO `upload_file` VALUES ('ec200cdf-3fd7-44a4-a9ce-a850b51bfe44', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\12\\ec200cdf-3fd7-44a4-a9ce-a850b51bfe44.jpg', '.jpg', '2018-01-12 16:40:57', '2018-01-12 16:40:57');
INSERT INTO `upload_file` VALUES ('ee2fa16f-579e-4024-9cf3-90673c886631', 'productCombo', 'D:\\temp\\Uploads\\productCombo\\2018\\01\\15\\ee2fa16f-579e-4024-9cf3-90673c886631.jpg', '.jpg', '2018-01-15 13:55:41', '2018-01-15 13:55:41');
INSERT INTO `upload_file` VALUES ('efb53489-83bf-4ab5-8c88-47cb57fdd6d5', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\efb53489-83bf-4ab5-8c88-47cb57fdd6d5.jpg', '.jpg', '2018-01-11 14:27:50', '2018-01-11 14:27:50');
INSERT INTO `upload_file` VALUES ('f11c1d06-d18b-4416-9547-f4d0b5249a02', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\10\\f11c1d06-d18b-4416-9547-f4d0b5249a02.jpg', '.jpg', '2018-01-10 15:17:39', '2018-01-10 15:17:39');
INSERT INTO `upload_file` VALUES ('f1bb6f18-0d7c-48fe-9524-1fb09c563431', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\f1bb6f18-0d7c-48fe-9524-1fb09c563431.jpg', '.jpg', '2018-01-11 14:17:47', '2018-01-11 14:17:47');
INSERT INTO `upload_file` VALUES ('ffb8cc91-c944-4bce-a1b5-a36cb8d36d8d', 'productInfo', 'D:\\temp\\Uploads\\productInfo\\2018\\01\\11\\ffb8cc91-c944-4bce-a1b5-a36cb8d36d8d.jpg', '.jpg', '2018-01-11 14:23:16', '2018-01-11 14:23:16');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) DEFAULT NULL COMMENT '微信openid',
  `status` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) NOT NULL COMMENT '用户类型 1-患者用户 2-职工用户 3-订/送餐员 4-厨房',
  `nickname` varchar(128) DEFAULT NULL COMMENT '用户昵称',
  `hos_Num` varchar(32) DEFAULT NULL COMMENT '住院号',
  `balance` decimal(10,2) DEFAULT '0.00' COMMENT '用户余额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'admin', 'H1AF2G39C90F59F00H5DHA574BA4EE3H', null, '1', '99', '超级管理员', '456', '0.00', '2018-01-19 09:30:45', '2018-04-25 15:25:38');
INSERT INTO `user` VALUES ('81', '15863157845', 'E10ADC3949BA59ABBE56E057F20F883E', null, '1', '1', null, null, '0.00', '2018-04-26 17:21:14', '2018-04-26 17:21:14');
INSERT INTO `user` VALUES ('84', '15863157812', 'E10ADC3949BA59ABBE56E057F20F883E', null, '1', '1', null, '123', '0.00', '2018-04-26 17:29:28', '2018-04-26 17:29:41');

-- ----------------------------
-- Table structure for user_dietary_habit_info
-- ----------------------------
DROP TABLE IF EXISTS `user_dietary_habit_info`;
CREATE TABLE `user_dietary_habit_info` (
  `id` varchar(36) NOT NULL,
  `user_id` int(11) NOT NULL COMMENT '用户信息表',
  `height` varchar(8) DEFAULT NULL COMMENT '身高',
  `weight` varchar(8) DEFAULT NULL COMMENT '体重',
  `belief` tinyint(4) DEFAULT NULL COMMENT '宗教禁忌',
  `food_allergy` varchar(64) DEFAULT NULL COMMENT '食物过敏史',
  `food_allergy_remark` varchar(128) DEFAULT NULL COMMENT '食物过敏史说明',
  `dietetic` tinyint(4) DEFAULT NULL COMMENT '有误饮食禁忌',
  `dietetic_remark` varchar(128) DEFAULT NULL COMMENT '饮食禁忌说明',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户饮食习惯信息表';

-- ----------------------------
-- Records of user_dietary_habit_info
-- ----------------------------
INSERT INTO `user_dietary_habit_info` VALUES ('706189e15f2c4d22b9bf7a582fca1da2', '1', '175', '200', '1', '5', '都过敏', '1', '酸甜苦辣咸都不吃', '2018-01-17 16:52:28', '2018-02-01 10:25:16');
INSERT INTO `user_dietary_habit_info` VALUES ('c5b162db0097436daf6cc1b368ca6b53', '2', '555', '555', '1', '5', '12313', '1', '1231321', '2018-01-19 09:23:29', '2018-02-01 10:25:18');
