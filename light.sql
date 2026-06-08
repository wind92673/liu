/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37)
 Source Host           : localhost:3306
 Source Schema         : light

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37)
 File Encoding         : 65001

 Date: 02/05/2026 22:41:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for command_log
-- ----------------------------
DROP TABLE IF EXISTS `command_log`;
CREATE TABLE `command_log`  (
  `id` int NOT NULL COMMENT '自增id',
  `event_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `command` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指令内容',
  `timestamp` datetime NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of command_log
-- ----------------------------

-- ----------------------------
-- Table structure for device_group_bound
-- ----------------------------
DROP TABLE IF EXISTS `device_group_bound`;
CREATE TABLE `device_group_bound`  (
  `id` int NOT NULL COMMENT '自增主键',
  `group_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组id',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_group_bound
-- ----------------------------

-- ----------------------------
-- Table structure for device_info
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info`  (
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备主键',
  `system_mode` tinyint NULL DEFAULT 0 COMMENT '运行状态,0:自动模式,1:手动模式',
  `light_threshold` int NULL DEFAULT 400 COMMENT '光照阈值',
  `day_start` int NULL DEFAULT 8 COMMENT '白天开始时间',
  `day_end` int NULL DEFAULT 10 COMMENT '夜晚开始时间',
  `online` tinyint NULL DEFAULT 0 COMMENT '在线状态，0：在线，1：离线',
  `report_interval` int NULL DEFAULT NULL COMMENT '上报间隔',
  PRIMARY KEY (`device_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_info
-- ----------------------------

-- ----------------------------
-- Table structure for group_info
-- ----------------------------
DROP TABLE IF EXISTS `group_info`;
CREATE TABLE `group_info`  (
  `group_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组主键',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_people` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_info
-- ----------------------------

-- ----------------------------
-- Table structure for report_light
-- ----------------------------
DROP TABLE IF EXISTS `report_light`;
CREATE TABLE `report_light`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `light_state` tinyint NULL DEFAULT NULL COMMENT '灯光状态',
  `bright_ness` int NULL DEFAULT NULL COMMENT '灯光亮度',
  `ambient_light` int NULL DEFAULT NULL COMMENT '环境亮度',
  `current_hour` int NULL DEFAULT NULL COMMENT '当前小时，0-23',
  `report_time` datetime NULL DEFAULT NULL COMMENT '上报时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时上报亮度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_light
-- ----------------------------

-- ----------------------------
-- Table structure for result_info
-- ----------------------------
DROP TABLE IF EXISTS `result_info`;
CREATE TABLE `result_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` int NULL DEFAULT NULL COMMENT '告警工单id',
  `user_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理人员id',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `repair_time` datetime NULL DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '处理结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of result_info
-- ----------------------------

-- ----------------------------
-- Table structure for save_info
-- ----------------------------
DROP TABLE IF EXISTS `save_info`;
CREATE TABLE `save_info`  (
  `id` int NOT NULL COMMENT '自增id',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `current_time` datetime NULL DEFAULT NULL COMMENT '当前时间，精确到天',
  `current_hour` int NULL DEFAULT NULL COMMENT '当前小时',
  `power` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节电量',
  `carbon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节碳量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '节能统计表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of save_info
-- ----------------------------

-- ----------------------------
-- Table structure for user_device_bound
-- ----------------------------
DROP TABLE IF EXISTS `user_device_bound`;
CREATE TABLE `user_device_bound`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户设备关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_device_bound
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `right` tinyint NULL DEFAULT 0 COMMENT '权限：0：普通用户，1：运维人员，2；管理员',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `idx_key_phone`(`phone` ASC) USING BTREE COMMENT '电话号唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------

-- ----------------------------
-- Table structure for warn_order
-- ----------------------------
DROP TABLE IF EXISTS `warn_order`;
CREATE TABLE `warn_order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `report_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '告警信息',
  `report_time` datetime NULL DEFAULT NULL COMMENT '告警时间',
  `user_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理人员id',
  `state` tinyint NULL DEFAULT NULL COMMENT '是否处理，0：未处理，1：已处理',
  `repair_time` datetime NULL DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '告警工单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warn_order
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
