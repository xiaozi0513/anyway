/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : anyway

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 15/10/2018 15:35:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aw_cust
-- ----------------------------
DROP TABLE IF EXISTS `aw_cust`;
CREATE TABLE `aw_cust` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `cust_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客户姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '手机号',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别（0-未知，1-男，2-女）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of aw_cust
-- ----------------------------
BEGIN;
INSERT INTO `aw_cust` VALUES (1, '王辉', '13194691439', 1, '2018-07-19 20:23:37', '2018-07-19 20:23:44', '');
INSERT INTO `aw_cust` VALUES (2, '白若男', '1550110751', 2, '2018-07-19 20:23:32', '2018-07-19 20:23:32', '');
INSERT INTO `aw_cust` VALUES (3, '白若石', '15512345678', 1, '2018-07-19 20:24:33', '2018-07-19 20:24:33', '');
INSERT INTO `aw_cust` VALUES (4, '李思琢', '15512345679', 2, '2018-07-19 20:24:51', '2018-07-19 20:24:51', '');
INSERT INTO `aw_cust` VALUES (5, '白学辉', '15512341234', 2, '2018-07-19 20:25:11', '2018-07-19 20:25:11', '');
INSERT INTO `aw_cust` VALUES (6, '李钢铁', '15512341235', 1, '2018-07-19 20:25:14', '2018-07-19 20:25:29', '');
INSERT INTO `aw_cust` VALUES (7, '赵一', '', 0, '2018-09-12 11:52:29', '2018-09-12 11:52:29', '');
INSERT INTO `aw_cust` VALUES (8, '赵二', '', 0, '2018-09-12 11:52:43', '2018-09-12 11:52:43', '');
INSERT INTO `aw_cust` VALUES (9, '赵三', '', 0, '2018-09-12 11:52:46', '2018-09-12 11:52:51', '');
INSERT INTO `aw_cust` VALUES (10, '赵四', '', 0, '2018-09-12 11:52:59', '2018-09-12 11:52:59', '');
INSERT INTO `aw_cust` VALUES (11, '赵五', '', 0, '2018-09-12 11:53:10', '2018-09-12 11:53:10', '');
INSERT INTO `aw_cust` VALUES (12, '赵六', '', 0, '2018-09-12 11:53:21', '2018-09-12 11:53:21', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `pid` bigint(20) NOT NULL COMMENT '父菜单ID',
  `url` varchar(200) DEFAULT '' COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT '' COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` tinyint(1) NOT NULL COMMENT '类型  0-目录，1-菜单，2-按钮',
  `icon` varchar(50) DEFAULT '' COMMENT '菜单图标',
  `order_num` int(11) NOT NULL COMMENT '排序',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态  0-无效，1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '我的安利', 0, '', '', 0, '', 0, 1);
INSERT INTO `sys_menu` VALUES (2, '客户管理', 1, '/modules/aw/cust.html', '', 1, '', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `gender` tinyint(1) NOT NULL DEFAULT '0' COMMENT '性别（0-未知，1-男，2-女）',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '排序值',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0-正常，1-封禁）',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'system', '11111111', 0, '13194691439', 0, 0, '2018-10-10 12:03:09');
INSERT INTO `sys_user` VALUES (2, 'wanghui', '11111111', 1, '13194691439', 0, 0, '2018-10-10 12:03:33');
INSERT INTO `sys_user` VALUES (3, 'bairuonan', '11111111', 2, '', 0, 0, '2018-10-10 12:03:54');
INSERT INTO `sys_user` VALUES (4, 'bairuoshi', '11111111', 1, NULL, 0, 0, '2018-10-10 16:28:08');
INSERT INTO `sys_user` VALUES (5, 'zhangyu', '11111111', 0, NULL, 0, 0, '2018-10-10 17:49:09');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
