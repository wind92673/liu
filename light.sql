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

 Date: 08/06/2026 15:36:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for command_log
-- ----------------------------
DROP TABLE IF EXISTS `command_log`;
CREATE TABLE `command_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `event_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `command` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指令内容',
  `timestamp` datetime NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of command_log
-- ----------------------------
INSERT INTO `command_log` VALUES (1, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":400,\"dayStartTime\":6}}', '2026-05-12 15:21:08');
INSERT INTO `command_log` VALUES (2, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":401,\"dayStartTime\":6}}', '2026-05-12 15:23:44');
INSERT INTO `command_log` VALUES (3, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":402,\"dayStartTime\":6}}', '2026-05-12 15:31:10');
INSERT INTO `command_log` VALUES (4, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":403,\"dayStartTime\":6}}', '2026-05-12 15:33:14');
INSERT INTO `command_log` VALUES (5, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":404,\"dayStartTime\":6}}', '2026-05-12 15:34:39');
INSERT INTO `command_log` VALUES (6, 'SYNC_TIME', 'corridor_light_001', '{\"eventType\":\"SYNC_TIME\",\"deviceId\":\"corridor_light_001\",\"config\":{\"currentTime\":\"20260518221552\"}}', '2026-05-18 22:15:53');
INSERT INTO `command_log` VALUES (7, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-18 22:16:25');
INSERT INTO `command_log` VALUES (8, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":0}}', '2026-05-18 22:16:25');
INSERT INTO `command_log` VALUES (9, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-18 22:16:44');
INSERT INTO `command_log` VALUES (10, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":404,\"dayStartTime\":6}}', '2026-05-18 22:17:10');
INSERT INTO `command_log` VALUES (11, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":404,\"dayStartTime\":6}}', '2026-05-18 22:18:16');
INSERT INTO `command_log` VALUES (12, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":410,\"dayStartTime\":6}}', '2026-05-18 22:19:04');
INSERT INTO `command_log` VALUES (13, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":18,\"lightThreshold\":412,\"dayStartTime\":6}}', '2026-05-18 22:21:43');
INSERT INTO `command_log` VALUES (14, 'SYNC_TIME', 'corridor_light_001', '{\"eventType\":\"SYNC_TIME\",\"deviceId\":\"corridor_light_001\",\"config\":{\"currentTime\":\"20260518222714\"}}', '2026-05-18 22:27:14');
INSERT INTO `command_log` VALUES (15, 'SYNC_TIME', 'corridor_light_001', '{\"eventType\":\"SYNC_TIME\",\"deviceId\":\"corridor_light_001\",\"config\":{\"currentTime\":\"20260518224422\"}}', '2026-05-18 22:44:23');
INSERT INTO `command_log` VALUES (16, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":20,\"lightThreshold\":400,\"dayStartTime\":6}}', '2026-05-18 22:45:10');
INSERT INTO `command_log` VALUES (17, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":20,\"lightThreshold\":400,\"dayStartTime\":6}}', '2026-05-18 22:45:25');
INSERT INTO `command_log` VALUES (18, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":20,\"lightThreshold\":400,\"dayStartTime\":6}}', '2026-05-18 22:49:35');
INSERT INTO `command_log` VALUES (19, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-18 22:49:35');
INSERT INTO `command_log` VALUES (20, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":20,\"lightThreshold\":400,\"dayStartTime\":6}}', '2026-05-18 22:49:48');
INSERT INTO `command_log` VALUES (21, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-18 22:49:48');
INSERT INTO `command_log` VALUES (22, 'SYNC_TIME', 'corridor_light_001', '{\"eventType\":\"SYNC_TIME\",\"deviceId\":\"corridor_light_001\",\"config\":{\"currentTime\":\"20260519174515\"}}', '2026-05-19 17:45:15');
INSERT INTO `command_log` VALUES (23, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":20,\"lightThreshold\":400,\"dayStartTime\":6}}', '2026-05-19 18:05:17');
INSERT INTO `command_log` VALUES (24, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-19 18:05:17');
INSERT INTO `command_log` VALUES (25, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":20,\"lightThreshold\":400,\"dayStartTime\":6}}', '2026-05-19 18:06:04');
INSERT INTO `command_log` VALUES (26, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:06:04');
INSERT INTO `command_log` VALUES (27, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:06:37');
INSERT INTO `command_log` VALUES (28, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":50}}', '2026-05-19 18:06:37');
INSERT INTO `command_log` VALUES (29, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-19 18:07:08');
INSERT INTO `command_log` VALUES (30, 'SYNC_TIME', 'corridor_light_001', '{\"eventType\":\"SYNC_TIME\",\"deviceId\":\"corridor_light_001\",\"config\":{\"currentTime\":\"20260519180810\"}}', '2026-05-19 18:08:11');
INSERT INTO `command_log` VALUES (31, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:09:06');
INSERT INTO `command_log` VALUES (32, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":50}}', '2026-05-19 18:09:06');
INSERT INTO `command_log` VALUES (33, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-19 18:09:26');
INSERT INTO `command_log` VALUES (34, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":22,\"lightThreshold\":400,\"dayStartTime\":6}}', '2026-05-19 18:09:47');
INSERT INTO `command_log` VALUES (35, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-19 18:09:47');
INSERT INTO `command_log` VALUES (36, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":22,\"lightThreshold\":200,\"dayStartTime\":6}}', '2026-05-19 18:32:27');
INSERT INTO `command_log` VALUES (37, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-19 18:32:27');
INSERT INTO `command_log` VALUES (38, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:33:12');
INSERT INTO `command_log` VALUES (39, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":50}}', '2026-05-19 18:33:12');
INSERT INTO `command_log` VALUES (40, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:33:21');
INSERT INTO `command_log` VALUES (41, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":100}}', '2026-05-19 18:33:22');
INSERT INTO `command_log` VALUES (42, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:33:47');
INSERT INTO `command_log` VALUES (43, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":0}}', '2026-05-19 18:33:48');
INSERT INTO `command_log` VALUES (44, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:33:58');
INSERT INTO `command_log` VALUES (45, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":25}}', '2026-05-19 18:33:58');
INSERT INTO `command_log` VALUES (46, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:34:07');
INSERT INTO `command_log` VALUES (47, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":31}}', '2026-05-19 18:34:07');
INSERT INTO `command_log` VALUES (48, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":22,\"lightThreshold\":200,\"dayStartTime\":6}}', '2026-05-19 18:37:00');
INSERT INTO `command_log` VALUES (49, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-19 18:37:00');
INSERT INTO `command_log` VALUES (50, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-05-19 18:41:52');
INSERT INTO `command_log` VALUES (51, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":40}}', '2026-05-19 18:41:53');
INSERT INTO `command_log` VALUES (52, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-19 18:42:15');
INSERT INTO `command_log` VALUES (53, 'CONFIGURATION', 'corridor_light_001', '{\"eventType\":\"CONFIGURATION\",\"deviceId\":\"corridor_light_001\",\"config\":{\"reportInterval\":10,\"dayEndTime\":22,\"lightThreshold\":205,\"dayStartTime\":6}}', '2026-05-19 18:42:46');
INSERT INTO `command_log` VALUES (54, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":0}}', '2026-05-19 18:42:46');
INSERT INTO `command_log` VALUES (55, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-06-02 13:05:24');
INSERT INTO `command_log` VALUES (56, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":63}}', '2026-06-02 13:05:25');
INSERT INTO `command_log` VALUES (57, 'SET_MODE', 'corridor_light_001', '{\"eventType\":\"SET_MODE\",\"deviceId\":\"corridor_light_001\",\"command\":{\"mode\":1}}', '2026-06-02 13:05:34');
INSERT INTO `command_log` VALUES (58, 'SET_LIGHT_BRIGHT', 'corridor_light_001', '{\"eventType\":\"SET_LIGHT_BRIGHT\",\"deviceId\":\"corridor_light_001\",\"command\":{\"brightness\":66}}', '2026-06-02 13:05:34');

-- ----------------------------
-- Table structure for device_group_bound
-- ----------------------------
DROP TABLE IF EXISTS `device_group_bound`;
CREATE TABLE `device_group_bound`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组id',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of device_group_bound
-- ----------------------------
INSERT INTO `device_group_bound` VALUES (20, '1', 'corridor_light_001', '2026-05-19 18:42:46');

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
INSERT INTO `device_info` VALUES ('corridor_light_001', 1, 205, 6, 22, 1, 10);

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
INSERT INTO `group_info` VALUES ('1', 'default', '2026-05-09 20:02:11', 'user0001');

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
) ENGINE = InnoDB AUTO_INCREMENT = 309 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时上报亮度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report_light
-- ----------------------------
INSERT INTO `report_light` VALUES (1, 'corridor_light_001', 1, 50, 9, 16, '2026-03-10 16:02:51');
INSERT INTO `report_light` VALUES (2, 'corridor_light_001', 1, 50, 214, 16, '2026-03-10 16:01:13');
INSERT INTO `report_light` VALUES (3, 'corridor_light_001', 1, 50, 214, 16, '2026-03-10 16:01:13');
INSERT INTO `report_light` VALUES (4, 'corridor_light_001', 1, 50, 214, 16, '2026-03-10 16:01:13');
INSERT INTO `report_light` VALUES (5, 'corridor_light_001', 1, 50, 214, 16, '2026-03-10 16:01:13');
INSERT INTO `report_light` VALUES (6, 'corridor_light_001', 1, 50, 214, 16, '2026-05-10 16:01:13');
INSERT INTO `report_light` VALUES (7, 'corridor_light_001', 1, 50, 214, 17, '2026-05-10 17:01:13');
INSERT INTO `report_light` VALUES (8, 'corridor_light_001', 1, 50, 214, 18, '2026-05-10 18:01:13');
INSERT INTO `report_light` VALUES (9, 'corridor_light_001', 1, 50, 214, 11, '2026-05-10 11:01:13');
INSERT INTO `report_light` VALUES (10, 'corridor_light_001', 1, 50, 24, 22, '2026-05-18 22:16:00');
INSERT INTO `report_light` VALUES (11, 'corridor_light_001', 1, 50, 25, 22, '2026-05-18 22:16:14');
INSERT INTO `report_light` VALUES (12, 'corridor_light_001', 1, 50, 18, 22, '2026-05-18 22:16:28');
INSERT INTO `report_light` VALUES (13, 'corridor_light_001', 1, 50, 19, 22, '2026-05-18 22:16:42');
INSERT INTO `report_light` VALUES (14, 'corridor_light_001', 1, 50, 21, 22, '2026-05-18 22:27:22');
INSERT INTO `report_light` VALUES (15, 'corridor_light_001', 1, 50, 20, 22, '2026-05-18 22:27:36');
INSERT INTO `report_light` VALUES (16, 'corridor_light_001', 1, 50, 24, 22, '2026-05-18 22:27:50');
INSERT INTO `report_light` VALUES (17, 'corridor_light_001', 1, 50, 23, 22, '2026-05-18 22:28:04');
INSERT INTO `report_light` VALUES (18, 'corridor_light_001', 1, 50, 24, 22, '2026-05-18 22:28:18');
INSERT INTO `report_light` VALUES (19, 'corridor_light_001', 1, 50, 11, 22, '2026-05-18 22:28:32');
INSERT INTO `report_light` VALUES (20, 'corridor_light_001', 1, 50, 25, 22, '2026-05-18 22:28:46');
INSERT INTO `report_light` VALUES (21, 'corridor_light_001', 1, 50, 6, 22, '2026-05-18 22:29:00');
INSERT INTO `report_light` VALUES (22, 'corridor_light_001', 1, 50, 17, 22, '2026-05-18 22:29:14');
INSERT INTO `report_light` VALUES (23, 'corridor_light_001', 1, 50, 5, 22, '2026-05-18 22:29:28');
INSERT INTO `report_light` VALUES (24, 'corridor_light_001', 1, 50, 5, 22, '2026-05-18 22:29:42');
INSERT INTO `report_light` VALUES (25, 'corridor_light_001', 1, 50, 6, 22, '2026-05-18 22:29:56');
INSERT INTO `report_light` VALUES (26, 'corridor_light_001', 1, 50, 5, 22, '2026-05-18 22:30:10');
INSERT INTO `report_light` VALUES (27, 'corridor_light_001', 1, 50, 20, 22, '2026-05-18 22:30:24');
INSERT INTO `report_light` VALUES (28, 'corridor_light_001', 1, 50, 18, 22, '2026-05-18 22:44:30');
INSERT INTO `report_light` VALUES (29, 'corridor_light_001', 1, 50, 16, 22, '2026-05-18 22:44:44');
INSERT INTO `report_light` VALUES (30, 'corridor_light_001', 1, 50, 15, 22, '2026-05-18 22:44:58');
INSERT INTO `report_light` VALUES (31, 'corridor_light_001', 1, 50, 14, 22, '2026-05-18 22:45:12');
INSERT INTO `report_light` VALUES (32, 'corridor_light_001', 1, 50, 15, 22, '2026-05-18 22:45:26');
INSERT INTO `report_light` VALUES (33, 'corridor_light_001', 1, 50, 15, 22, '2026-05-18 22:45:40');
INSERT INTO `report_light` VALUES (34, 'corridor_light_001', 1, 50, 15, 22, '2026-05-18 22:45:54');
INSERT INTO `report_light` VALUES (35, 'corridor_light_001', 1, 50, 15, 22, '2026-05-18 22:46:08');
INSERT INTO `report_light` VALUES (36, 'corridor_light_001', 1, 50, 15, 22, '2026-05-18 22:46:22');
INSERT INTO `report_light` VALUES (37, 'corridor_light_001', 1, 50, 15, 22, '2026-05-18 22:46:36');
INSERT INTO `report_light` VALUES (38, 'corridor_light_001', 1, 50, 13, 22, '2026-05-18 22:46:50');
INSERT INTO `report_light` VALUES (39, 'corridor_light_001', 1, 50, 13, 22, '2026-05-18 22:47:04');
INSERT INTO `report_light` VALUES (40, 'corridor_light_001', 1, 50, 13, 22, '2026-05-18 22:47:18');
INSERT INTO `report_light` VALUES (41, 'corridor_light_001', 1, 50, 13, 22, '2026-05-18 22:47:32');
INSERT INTO `report_light` VALUES (42, 'corridor_light_001', 1, 50, 13, 22, '2026-05-18 22:47:46');
INSERT INTO `report_light` VALUES (43, 'corridor_light_001', 1, 50, 2, 22, '2026-05-18 22:48:00');
INSERT INTO `report_light` VALUES (44, 'corridor_light_001', 1, 50, 2, 22, '2026-05-18 22:48:14');
INSERT INTO `report_light` VALUES (45, 'corridor_light_001', 1, 50, 2, 22, '2026-05-18 22:48:28');
INSERT INTO `report_light` VALUES (46, 'corridor_light_001', 1, 50, 14, 22, '2026-05-18 22:48:42');
INSERT INTO `report_light` VALUES (47, 'corridor_light_001', 1, 50, 14, 22, '2026-05-18 22:48:56');
INSERT INTO `report_light` VALUES (48, 'corridor_light_001', 1, 50, 14, 22, '2026-05-18 22:49:24');
INSERT INTO `report_light` VALUES (49, 'corridor_light_001', 1, 50, 14, 22, '2026-05-18 22:49:38');
INSERT INTO `report_light` VALUES (50, 'corridor_light_001', 1, 50, 14, 22, '2026-05-18 22:49:52');
INSERT INTO `report_light` VALUES (51, 'corridor_light_001', 1, 50, 14, 22, '2026-05-18 22:50:06');
INSERT INTO `report_light` VALUES (52, 'corridor_light_001', 1, 50, 10, 22, '2026-05-18 22:50:20');
INSERT INTO `report_light` VALUES (53, 'corridor_light_001', 1, 50, 0, 22, '2026-05-18 22:50:34');
INSERT INTO `report_light` VALUES (54, 'corridor_light_001', 1, 50, 0, 22, '2026-05-18 22:50:48');
INSERT INTO `report_light` VALUES (55, 'corridor_light_001', 1, 50, 6, 22, '2026-05-18 22:51:02');
INSERT INTO `report_light` VALUES (56, 'corridor_light_001', 1, 50, 54, 17, '2026-05-19 17:45:23');
INSERT INTO `report_light` VALUES (57, 'corridor_light_001', 1, 50, 50, 17, '2026-05-19 17:45:37');
INSERT INTO `report_light` VALUES (58, 'corridor_light_001', 1, 50, 49, 17, '2026-05-19 17:45:51');
INSERT INTO `report_light` VALUES (59, 'corridor_light_001', 1, 50, 51, 17, '2026-05-19 17:46:05');
INSERT INTO `report_light` VALUES (60, 'corridor_light_001', 1, 50, 49, 17, '2026-05-19 17:46:19');
INSERT INTO `report_light` VALUES (61, 'corridor_light_001', 1, 50, 45, 17, '2026-05-19 17:46:33');
INSERT INTO `report_light` VALUES (62, 'corridor_light_001', 1, 50, 186, 17, '2026-05-19 17:46:47');
INSERT INTO `report_light` VALUES (63, 'corridor_light_001', 1, 50, 209, 17, '2026-05-19 17:47:01');
INSERT INTO `report_light` VALUES (64, 'corridor_light_001', 1, 50, 211, 17, '2026-05-19 17:47:15');
INSERT INTO `report_light` VALUES (65, 'corridor_light_001', 1, 50, 183, 17, '2026-05-19 17:47:29');
INSERT INTO `report_light` VALUES (66, 'corridor_light_001', 1, 50, 87, 17, '2026-05-19 17:47:43');
INSERT INTO `report_light` VALUES (67, 'corridor_light_001', 1, 50, 124, 17, '2026-05-19 17:47:57');
INSERT INTO `report_light` VALUES (68, 'corridor_light_001', 1, 50, 59, 17, '2026-05-19 17:48:11');
INSERT INTO `report_light` VALUES (69, 'corridor_light_001', 1, 50, 204, 17, '2026-05-19 17:48:25');
INSERT INTO `report_light` VALUES (70, 'corridor_light_001', 1, 50, 194, 17, '2026-05-19 17:48:39');
INSERT INTO `report_light` VALUES (71, 'corridor_light_001', 1, 50, 194, 17, '2026-05-19 17:48:53');
INSERT INTO `report_light` VALUES (72, 'corridor_light_001', 1, 50, 194, 17, '2026-05-19 17:49:07');
INSERT INTO `report_light` VALUES (73, 'corridor_light_001', 1, 50, 194, 17, '2026-05-19 17:49:21');
INSERT INTO `report_light` VALUES (74, 'corridor_light_001', 1, 50, 119, 17, '2026-05-19 17:49:35');
INSERT INTO `report_light` VALUES (75, 'corridor_light_001', 1, 50, 98, 17, '2026-05-19 17:49:49');
INSERT INTO `report_light` VALUES (76, 'corridor_light_001', 1, 50, 99, 17, '2026-05-19 17:50:03');
INSERT INTO `report_light` VALUES (77, 'corridor_light_001', 1, 50, 101, 17, '2026-05-19 17:50:17');
INSERT INTO `report_light` VALUES (78, 'corridor_light_001', 1, 50, 101, 17, '2026-05-19 17:50:31');
INSERT INTO `report_light` VALUES (79, 'corridor_light_001', 1, 50, 103, 17, '2026-05-19 17:50:45');
INSERT INTO `report_light` VALUES (80, 'corridor_light_001', 1, 50, 194, 17, '2026-05-19 17:50:59');
INSERT INTO `report_light` VALUES (81, 'corridor_light_001', 1, 50, 160, 17, '2026-05-19 17:51:13');
INSERT INTO `report_light` VALUES (82, 'corridor_light_001', 1, 50, 149, 17, '2026-05-19 17:51:27');
INSERT INTO `report_light` VALUES (83, 'corridor_light_001', 1, 50, 150, 17, '2026-05-19 17:51:41');
INSERT INTO `report_light` VALUES (84, 'corridor_light_001', 1, 50, 165, 17, '2026-05-19 17:51:55');
INSERT INTO `report_light` VALUES (85, 'corridor_light_001', 1, 50, 134, 17, '2026-05-19 17:52:09');
INSERT INTO `report_light` VALUES (86, 'corridor_light_001', 1, 50, 180, 17, '2026-05-19 17:52:23');
INSERT INTO `report_light` VALUES (87, 'corridor_light_001', 1, 50, 194, 17, '2026-05-19 17:52:37');
INSERT INTO `report_light` VALUES (88, 'corridor_light_001', 1, 50, 185, 17, '2026-05-19 17:52:51');
INSERT INTO `report_light` VALUES (89, 'corridor_light_001', 1, 50, 203, 17, '2026-05-19 17:53:05');
INSERT INTO `report_light` VALUES (90, 'corridor_light_001', 1, 50, 201, 17, '2026-05-19 17:53:19');
INSERT INTO `report_light` VALUES (91, 'corridor_light_001', 1, 50, 199, 17, '2026-05-19 17:53:33');
INSERT INTO `report_light` VALUES (92, 'corridor_light_001', 1, 50, 202, 17, '2026-05-19 17:53:47');
INSERT INTO `report_light` VALUES (93, 'corridor_light_001', 1, 50, 87, 17, '2026-05-19 17:54:01');
INSERT INTO `report_light` VALUES (94, 'corridor_light_001', 1, 50, 84, 17, '2026-05-19 17:54:15');
INSERT INTO `report_light` VALUES (95, 'corridor_light_001', 1, 50, 112, 17, '2026-05-19 17:54:29');
INSERT INTO `report_light` VALUES (96, 'corridor_light_001', 1, 50, 199, 17, '2026-05-19 17:54:43');
INSERT INTO `report_light` VALUES (97, 'corridor_light_001', 1, 50, 199, 17, '2026-05-19 17:54:57');
INSERT INTO `report_light` VALUES (98, 'corridor_light_001', 1, 50, 176, 17, '2026-05-19 17:55:11');
INSERT INTO `report_light` VALUES (99, 'corridor_light_001', 1, 50, 206, 17, '2026-05-19 17:55:25');
INSERT INTO `report_light` VALUES (100, 'corridor_light_001', 1, 50, 201, 17, '2026-05-19 17:55:39');
INSERT INTO `report_light` VALUES (101, 'corridor_light_001', 1, 50, 150, 17, '2026-05-19 17:55:53');
INSERT INTO `report_light` VALUES (102, 'corridor_light_001', 1, 50, 139, 17, '2026-05-19 17:56:07');
INSERT INTO `report_light` VALUES (103, 'corridor_light_001', 1, 50, 49, 17, '2026-05-19 17:56:21');
INSERT INTO `report_light` VALUES (104, 'corridor_light_001', 1, 50, 49, 17, '2026-05-19 17:56:35');
INSERT INTO `report_light` VALUES (105, 'corridor_light_001', 1, 50, 49, 17, '2026-05-19 17:56:49');
INSERT INTO `report_light` VALUES (106, 'corridor_light_001', 1, 50, 49, 17, '2026-05-19 17:57:03');
INSERT INTO `report_light` VALUES (107, 'corridor_light_001', 1, 50, 108, 17, '2026-05-19 17:57:17');
INSERT INTO `report_light` VALUES (108, 'corridor_light_001', 1, 50, 168, 17, '2026-05-19 17:57:31');
INSERT INTO `report_light` VALUES (109, 'corridor_light_001', 1, 50, 166, 17, '2026-05-19 17:57:45');
INSERT INTO `report_light` VALUES (110, 'corridor_light_001', 1, 50, 165, 17, '2026-05-19 17:57:59');
INSERT INTO `report_light` VALUES (111, 'corridor_light_001', 1, 50, 175, 17, '2026-05-19 17:58:13');
INSERT INTO `report_light` VALUES (112, 'corridor_light_001', 1, 50, 178, 17, '2026-05-19 17:58:27');
INSERT INTO `report_light` VALUES (113, 'corridor_light_001', 1, 50, 175, 17, '2026-05-19 17:58:41');
INSERT INTO `report_light` VALUES (114, 'corridor_light_001', 1, 50, 175, 17, '2026-05-19 17:58:55');
INSERT INTO `report_light` VALUES (115, 'corridor_light_001', 1, 50, 183, 17, '2026-05-19 17:59:09');
INSERT INTO `report_light` VALUES (116, 'corridor_light_001', 1, 50, 189, 17, '2026-05-19 17:59:23');
INSERT INTO `report_light` VALUES (117, 'corridor_light_001', 1, 50, 184, 17, '2026-05-19 17:59:37');
INSERT INTO `report_light` VALUES (118, 'corridor_light_001', 1, 50, 182, 17, '2026-05-19 17:59:51');
INSERT INTO `report_light` VALUES (119, 'corridor_light_001', 1, 50, 191, 18, '2026-05-19 18:00:05');
INSERT INTO `report_light` VALUES (120, 'corridor_light_001', 1, 50, 188, 18, '2026-05-19 18:00:19');
INSERT INTO `report_light` VALUES (121, 'corridor_light_001', 1, 50, 186, 18, '2026-05-19 18:00:33');
INSERT INTO `report_light` VALUES (122, 'corridor_light_001', 1, 50, 183, 18, '2026-05-19 18:00:47');
INSERT INTO `report_light` VALUES (123, 'corridor_light_001', 1, 50, 180, 18, '2026-05-19 18:01:01');
INSERT INTO `report_light` VALUES (124, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:01:15');
INSERT INTO `report_light` VALUES (125, 'corridor_light_001', 1, 50, 186, 18, '2026-05-19 18:01:29');
INSERT INTO `report_light` VALUES (126, 'corridor_light_001', 1, 50, 160, 18, '2026-05-19 18:01:43');
INSERT INTO `report_light` VALUES (127, 'corridor_light_001', 1, 50, 80, 18, '2026-05-19 18:01:57');
INSERT INTO `report_light` VALUES (128, 'corridor_light_001', 1, 50, 70, 18, '2026-05-19 18:02:11');
INSERT INTO `report_light` VALUES (129, 'corridor_light_001', 1, 50, 70, 18, '2026-05-19 18:02:25');
INSERT INTO `report_light` VALUES (130, 'corridor_light_001', 1, 50, 65, 18, '2026-05-19 18:02:39');
INSERT INTO `report_light` VALUES (131, 'corridor_light_001', 1, 50, 74, 18, '2026-05-19 18:02:53');
INSERT INTO `report_light` VALUES (132, 'corridor_light_001', 1, 50, 79, 18, '2026-05-19 18:03:07');
INSERT INTO `report_light` VALUES (133, 'corridor_light_001', 1, 50, 74, 18, '2026-05-19 18:03:21');
INSERT INTO `report_light` VALUES (134, 'corridor_light_001', 1, 50, 73, 18, '2026-05-19 18:03:35');
INSERT INTO `report_light` VALUES (135, 'corridor_light_001', 1, 50, 74, 18, '2026-05-19 18:03:49');
INSERT INTO `report_light` VALUES (136, 'corridor_light_001', 1, 50, 75, 18, '2026-05-19 18:04:03');
INSERT INTO `report_light` VALUES (137, 'corridor_light_001', 1, 50, 99, 18, '2026-05-19 18:04:17');
INSERT INTO `report_light` VALUES (138, 'corridor_light_001', 1, 50, 99, 18, '2026-05-19 18:04:31');
INSERT INTO `report_light` VALUES (139, 'corridor_light_001', 1, 50, 99, 18, '2026-05-19 18:04:45');
INSERT INTO `report_light` VALUES (140, 'corridor_light_001', 1, 50, 135, 18, '2026-05-19 18:04:59');
INSERT INTO `report_light` VALUES (141, 'corridor_light_001', 1, 50, 138, 18, '2026-05-19 18:05:13');
INSERT INTO `report_light` VALUES (142, 'corridor_light_001', 1, 50, 132, 18, '2026-05-19 18:05:27');
INSERT INTO `report_light` VALUES (143, 'corridor_light_001', 1, 50, 133, 18, '2026-05-19 18:05:41');
INSERT INTO `report_light` VALUES (144, 'corridor_light_001', 1, 50, 133, 18, '2026-05-19 18:05:55');
INSERT INTO `report_light` VALUES (145, 'corridor_light_001', 1, 50, 135, 18, '2026-05-19 18:06:09');
INSERT INTO `report_light` VALUES (146, 'corridor_light_001', 1, 50, 108, 18, '2026-05-19 18:06:23');
INSERT INTO `report_light` VALUES (147, 'corridor_light_001', 1, 50, 108, 18, '2026-05-19 18:06:37');
INSERT INTO `report_light` VALUES (148, 'corridor_light_001', 1, 50, 104, 18, '2026-05-19 18:06:51');
INSERT INTO `report_light` VALUES (149, 'corridor_light_001', 1, 50, 106, 18, '2026-05-19 18:07:05');
INSERT INTO `report_light` VALUES (150, 'corridor_light_001', 1, 50, 111, 18, '2026-05-19 18:07:19');
INSERT INTO `report_light` VALUES (151, 'corridor_light_001', 1, 50, 113, 18, '2026-05-19 18:08:18');
INSERT INTO `report_light` VALUES (152, 'corridor_light_001', 1, 50, 112, 18, '2026-05-19 18:08:32');
INSERT INTO `report_light` VALUES (153, 'corridor_light_001', 1, 50, 113, 18, '2026-05-19 18:08:46');
INSERT INTO `report_light` VALUES (154, 'corridor_light_001', 1, 50, 118, 18, '2026-05-19 18:09:00');
INSERT INTO `report_light` VALUES (155, 'corridor_light_001', 1, 50, 107, 18, '2026-05-19 18:09:14');
INSERT INTO `report_light` VALUES (156, 'corridor_light_001', 1, 50, 75, 18, '2026-05-19 18:09:28');
INSERT INTO `report_light` VALUES (157, 'corridor_light_001', 1, 50, 84, 18, '2026-05-19 18:09:42');
INSERT INTO `report_light` VALUES (158, 'corridor_light_001', 1, 50, 124, 18, '2026-05-19 18:09:56');
INSERT INTO `report_light` VALUES (159, 'corridor_light_001', 1, 50, 141, 18, '2026-05-19 18:10:10');
INSERT INTO `report_light` VALUES (160, 'corridor_light_001', 1, 50, 130, 18, '2026-05-19 18:10:24');
INSERT INTO `report_light` VALUES (161, 'corridor_light_001', 1, 50, 164, 18, '2026-05-19 18:10:38');
INSERT INTO `report_light` VALUES (162, 'corridor_light_001', 1, 50, 172, 18, '2026-05-19 18:10:52');
INSERT INTO `report_light` VALUES (163, 'corridor_light_001', 1, 50, 109, 18, '2026-05-19 18:11:06');
INSERT INTO `report_light` VALUES (164, 'corridor_light_001', 1, 50, 99, 18, '2026-05-19 18:11:20');
INSERT INTO `report_light` VALUES (165, 'corridor_light_001', 1, 50, 64, 18, '2026-05-19 18:11:34');
INSERT INTO `report_light` VALUES (166, 'corridor_light_001', 1, 50, 57, 18, '2026-05-19 18:11:48');
INSERT INTO `report_light` VALUES (167, 'corridor_light_001', 1, 50, 55, 18, '2026-05-19 18:12:02');
INSERT INTO `report_light` VALUES (168, 'corridor_light_001', 1, 50, 55, 18, '2026-05-19 18:12:16');
INSERT INTO `report_light` VALUES (169, 'corridor_light_001', 1, 50, 54, 18, '2026-05-19 18:12:30');
INSERT INTO `report_light` VALUES (170, 'corridor_light_001', 1, 50, 52, 18, '2026-05-19 18:12:44');
INSERT INTO `report_light` VALUES (171, 'corridor_light_001', 1, 50, 53, 18, '2026-05-19 18:12:58');
INSERT INTO `report_light` VALUES (172, 'corridor_light_001', 1, 50, 58, 18, '2026-05-19 18:13:12');
INSERT INTO `report_light` VALUES (173, 'corridor_light_001', 1, 50, 57, 18, '2026-05-19 18:13:26');
INSERT INTO `report_light` VALUES (174, 'corridor_light_001', 1, 50, 54, 18, '2026-05-19 18:13:40');
INSERT INTO `report_light` VALUES (175, 'corridor_light_001', 1, 50, 58, 18, '2026-05-19 18:13:54');
INSERT INTO `report_light` VALUES (176, 'corridor_light_001', 1, 50, 56, 18, '2026-05-19 18:14:08');
INSERT INTO `report_light` VALUES (177, 'corridor_light_001', 1, 50, 59, 18, '2026-05-19 18:14:22');
INSERT INTO `report_light` VALUES (178, 'corridor_light_001', 1, 50, 64, 18, '2026-05-19 18:14:36');
INSERT INTO `report_light` VALUES (179, 'corridor_light_001', 1, 50, 64, 18, '2026-05-19 18:14:50');
INSERT INTO `report_light` VALUES (180, 'corridor_light_001', 1, 50, 64, 18, '2026-05-19 18:15:04');
INSERT INTO `report_light` VALUES (181, 'corridor_light_001', 1, 50, 64, 18, '2026-05-19 18:15:18');
INSERT INTO `report_light` VALUES (182, 'corridor_light_001', 1, 50, 64, 18, '2026-05-19 18:15:32');
INSERT INTO `report_light` VALUES (183, 'corridor_light_001', 1, 50, 64, 18, '2026-05-19 18:15:46');
INSERT INTO `report_light` VALUES (184, 'corridor_light_001', 1, 50, 62, 18, '2026-05-19 18:16:00');
INSERT INTO `report_light` VALUES (185, 'corridor_light_001', 1, 50, 64, 18, '2026-05-19 18:16:14');
INSERT INTO `report_light` VALUES (186, 'corridor_light_001', 1, 50, 60, 18, '2026-05-19 18:16:28');
INSERT INTO `report_light` VALUES (187, 'corridor_light_001', 1, 50, 95, 18, '2026-05-19 18:16:42');
INSERT INTO `report_light` VALUES (188, 'corridor_light_001', 1, 50, 120, 18, '2026-05-19 18:16:56');
INSERT INTO `report_light` VALUES (189, 'corridor_light_001', 1, 50, 78, 18, '2026-05-19 18:17:10');
INSERT INTO `report_light` VALUES (190, 'corridor_light_001', 1, 50, 79, 18, '2026-05-19 18:17:24');
INSERT INTO `report_light` VALUES (191, 'corridor_light_001', 1, 50, 81, 18, '2026-05-19 18:17:38');
INSERT INTO `report_light` VALUES (192, 'corridor_light_001', 1, 50, 81, 18, '2026-05-19 18:17:52');
INSERT INTO `report_light` VALUES (193, 'corridor_light_001', 1, 50, 82, 18, '2026-05-19 18:18:06');
INSERT INTO `report_light` VALUES (194, 'corridor_light_001', 1, 50, 82, 18, '2026-05-19 18:18:20');
INSERT INTO `report_light` VALUES (195, 'corridor_light_001', 1, 50, 81, 18, '2026-05-19 18:18:34');
INSERT INTO `report_light` VALUES (196, 'corridor_light_001', 1, 50, 95, 18, '2026-05-19 18:18:48');
INSERT INTO `report_light` VALUES (197, 'corridor_light_001', 1, 50, 90, 18, '2026-05-19 18:19:02');
INSERT INTO `report_light` VALUES (198, 'corridor_light_001', 1, 50, 89, 18, '2026-05-19 18:19:16');
INSERT INTO `report_light` VALUES (199, 'corridor_light_001', 1, 50, 89, 18, '2026-05-19 18:19:30');
INSERT INTO `report_light` VALUES (200, 'corridor_light_001', 1, 50, 92, 18, '2026-05-19 18:19:44');
INSERT INTO `report_light` VALUES (201, 'corridor_light_001', 1, 50, 92, 18, '2026-05-19 18:19:58');
INSERT INTO `report_light` VALUES (202, 'corridor_light_001', 1, 50, 94, 18, '2026-05-19 18:20:12');
INSERT INTO `report_light` VALUES (203, 'corridor_light_001', 1, 50, 98, 18, '2026-05-19 18:20:26');
INSERT INTO `report_light` VALUES (204, 'corridor_light_001', 1, 50, 95, 18, '2026-05-19 18:20:40');
INSERT INTO `report_light` VALUES (205, 'corridor_light_001', 1, 50, 117, 18, '2026-05-19 18:20:54');
INSERT INTO `report_light` VALUES (206, 'corridor_light_001', 1, 50, 111, 18, '2026-05-19 18:21:08');
INSERT INTO `report_light` VALUES (207, 'corridor_light_001', 1, 50, 111, 18, '2026-05-19 18:21:22');
INSERT INTO `report_light` VALUES (208, 'corridor_light_001', 1, 50, 103, 18, '2026-05-19 18:21:36');
INSERT INTO `report_light` VALUES (209, 'corridor_light_001', 1, 50, 71, 18, '2026-05-19 18:21:50');
INSERT INTO `report_light` VALUES (210, 'corridor_light_001', 1, 50, 165, 18, '2026-05-19 18:22:04');
INSERT INTO `report_light` VALUES (211, 'corridor_light_001', 1, 50, 143, 18, '2026-05-19 18:22:18');
INSERT INTO `report_light` VALUES (212, 'corridor_light_001', 1, 50, 135, 18, '2026-05-19 18:22:32');
INSERT INTO `report_light` VALUES (213, 'corridor_light_001', 1, 50, 288, 18, '2026-05-19 18:22:46');
INSERT INTO `report_light` VALUES (214, 'corridor_light_001', 1, 50, 80, 18, '2026-05-19 18:23:00');
INSERT INTO `report_light` VALUES (215, 'corridor_light_001', 1, 50, 43, 18, '2026-05-19 18:23:14');
INSERT INTO `report_light` VALUES (216, 'corridor_light_001', 1, 50, 70, 18, '2026-05-19 18:23:28');
INSERT INTO `report_light` VALUES (217, 'corridor_light_001', 1, 50, 156, 18, '2026-05-19 18:23:42');
INSERT INTO `report_light` VALUES (218, 'corridor_light_001', 1, 50, 150, 18, '2026-05-19 18:23:56');
INSERT INTO `report_light` VALUES (219, 'corridor_light_001', 1, 50, 158, 18, '2026-05-19 18:24:10');
INSERT INTO `report_light` VALUES (220, 'corridor_light_001', 1, 50, 193, 18, '2026-05-19 18:24:24');
INSERT INTO `report_light` VALUES (221, 'corridor_light_001', 1, 50, 193, 18, '2026-05-19 18:24:38');
INSERT INTO `report_light` VALUES (222, 'corridor_light_001', 1, 50, 192, 18, '2026-05-19 18:24:52');
INSERT INTO `report_light` VALUES (223, 'corridor_light_001', 1, 50, 192, 18, '2026-05-19 18:25:06');
INSERT INTO `report_light` VALUES (224, 'corridor_light_001', 1, 50, 173, 18, '2026-05-19 18:25:20');
INSERT INTO `report_light` VALUES (225, 'corridor_light_001', 1, 50, 174, 18, '2026-05-19 18:25:34');
INSERT INTO `report_light` VALUES (226, 'corridor_light_001', 1, 50, 173, 18, '2026-05-19 18:25:48');
INSERT INTO `report_light` VALUES (227, 'corridor_light_001', 1, 50, 174, 18, '2026-05-19 18:26:02');
INSERT INTO `report_light` VALUES (228, 'corridor_light_001', 1, 50, 173, 18, '2026-05-19 18:26:16');
INSERT INTO `report_light` VALUES (229, 'corridor_light_001', 1, 50, 173, 18, '2026-05-19 18:26:30');
INSERT INTO `report_light` VALUES (230, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:26:44');
INSERT INTO `report_light` VALUES (231, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:26:58');
INSERT INTO `report_light` VALUES (232, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:27:12');
INSERT INTO `report_light` VALUES (233, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:27:26');
INSERT INTO `report_light` VALUES (234, 'corridor_light_001', 1, 50, 176, 18, '2026-05-19 18:27:40');
INSERT INTO `report_light` VALUES (235, 'corridor_light_001', 1, 50, 176, 18, '2026-05-19 18:27:54');
INSERT INTO `report_light` VALUES (236, 'corridor_light_001', 1, 50, 176, 18, '2026-05-19 18:28:08');
INSERT INTO `report_light` VALUES (237, 'corridor_light_001', 1, 50, 176, 18, '2026-05-19 18:28:22');
INSERT INTO `report_light` VALUES (238, 'corridor_light_001', 1, 50, 176, 18, '2026-05-19 18:28:36');
INSERT INTO `report_light` VALUES (239, 'corridor_light_001', 1, 50, 182, 18, '2026-05-19 18:28:50');
INSERT INTO `report_light` VALUES (240, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:29:04');
INSERT INTO `report_light` VALUES (241, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:29:18');
INSERT INTO `report_light` VALUES (242, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:29:32');
INSERT INTO `report_light` VALUES (243, 'corridor_light_001', 1, 50, 176, 18, '2026-05-19 18:29:46');
INSERT INTO `report_light` VALUES (244, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:30:00');
INSERT INTO `report_light` VALUES (245, 'corridor_light_001', 1, 50, 175, 18, '2026-05-19 18:30:14');
INSERT INTO `report_light` VALUES (246, 'corridor_light_001', 1, 50, 171, 18, '2026-05-19 18:30:28');
INSERT INTO `report_light` VALUES (247, 'corridor_light_001', 1, 50, 171, 18, '2026-05-19 18:30:42');
INSERT INTO `report_light` VALUES (248, 'corridor_light_001', 1, 50, 171, 18, '2026-05-19 18:30:56');
INSERT INTO `report_light` VALUES (249, 'corridor_light_001', 1, 50, 172, 18, '2026-05-19 18:31:10');
INSERT INTO `report_light` VALUES (250, 'corridor_light_001', 1, 50, 172, 18, '2026-05-19 18:31:24');
INSERT INTO `report_light` VALUES (251, 'corridor_light_001', 1, 50, 171, 18, '2026-05-19 18:31:38');
INSERT INTO `report_light` VALUES (252, 'corridor_light_001', 1, 50, 170, 18, '2026-05-19 18:31:52');
INSERT INTO `report_light` VALUES (253, 'corridor_light_001', 1, 50, 172, 18, '2026-05-19 18:32:06');
INSERT INTO `report_light` VALUES (254, 'corridor_light_001', 1, 50, 171, 18, '2026-05-19 18:32:20');
INSERT INTO `report_light` VALUES (255, 'corridor_light_001', 1, 50, 143, 18, '2026-05-19 18:32:34');
INSERT INTO `report_light` VALUES (256, 'corridor_light_001', 1, 50, 135, 18, '2026-05-19 18:32:48');
INSERT INTO `report_light` VALUES (257, 'corridor_light_001', 1, 50, 131, 18, '2026-05-19 18:33:02');
INSERT INTO `report_light` VALUES (258, 'corridor_light_001', 1, 50, 168, 18, '2026-05-19 18:33:16');
INSERT INTO `report_light` VALUES (259, 'corridor_light_001', 1, 50, 59, 18, '2026-05-19 18:33:30');
INSERT INTO `report_light` VALUES (260, 'corridor_light_001', 0, 0, 136, 18, '2026-05-19 18:33:44');
INSERT INTO `report_light` VALUES (261, 'corridor_light_001', 0, 0, 100, 18, '2026-05-19 18:33:58');
INSERT INTO `report_light` VALUES (262, 'corridor_light_001', 0, 0, 93, 18, '2026-05-19 18:34:12');
INSERT INTO `report_light` VALUES (263, 'corridor_light_001', 0, 0, 90, 18, '2026-05-19 18:34:26');
INSERT INTO `report_light` VALUES (264, 'corridor_light_001', 0, 0, 90, 18, '2026-05-19 18:34:40');
INSERT INTO `report_light` VALUES (265, 'corridor_light_001', 0, 0, 85, 18, '2026-05-19 18:34:54');
INSERT INTO `report_light` VALUES (266, 'corridor_light_001', 0, 0, 84, 18, '2026-05-19 18:35:08');
INSERT INTO `report_light` VALUES (267, 'corridor_light_001', 0, 0, 91, 18, '2026-05-19 18:35:22');
INSERT INTO `report_light` VALUES (268, 'corridor_light_001', 0, 0, 91, 18, '2026-05-19 18:35:36');
INSERT INTO `report_light` VALUES (269, 'corridor_light_001', 0, 0, 90, 18, '2026-05-19 18:35:50');
INSERT INTO `report_light` VALUES (270, 'corridor_light_001', 0, 0, 86, 18, '2026-05-19 18:36:04');
INSERT INTO `report_light` VALUES (271, 'corridor_light_001', 0, 0, 88, 18, '2026-05-19 18:36:18');
INSERT INTO `report_light` VALUES (272, 'corridor_light_001', 0, 0, 109, 18, '2026-05-19 18:36:32');
INSERT INTO `report_light` VALUES (273, 'corridor_light_001', 0, 0, 69, 18, '2026-05-19 18:36:46');
INSERT INTO `report_light` VALUES (274, 'corridor_light_001', 0, 0, 72, 18, '2026-05-19 18:37:00');
INSERT INTO `report_light` VALUES (275, 'corridor_light_001', 0, 0, 109, 18, '2026-05-19 18:37:14');
INSERT INTO `report_light` VALUES (276, 'corridor_light_001', 0, 0, 131, 18, '2026-05-19 18:37:28');
INSERT INTO `report_light` VALUES (277, 'corridor_light_001', 0, 0, 134, 18, '2026-05-19 18:37:42');
INSERT INTO `report_light` VALUES (278, 'corridor_light_001', 0, 0, 131, 18, '2026-05-19 18:37:56');
INSERT INTO `report_light` VALUES (279, 'corridor_light_001', 0, 0, 132, 18, '2026-05-19 18:38:10');
INSERT INTO `report_light` VALUES (280, 'corridor_light_001', 0, 0, 136, 18, '2026-05-19 18:38:24');
INSERT INTO `report_light` VALUES (281, 'corridor_light_001', 0, 0, 130, 18, '2026-05-19 18:38:38');
INSERT INTO `report_light` VALUES (282, 'corridor_light_001', 0, 0, 131, 18, '2026-05-19 18:38:52');
INSERT INTO `report_light` VALUES (283, 'corridor_light_001', 0, 0, 126, 18, '2026-05-19 18:39:06');
INSERT INTO `report_light` VALUES (284, 'corridor_light_001', 0, 0, 133, 18, '2026-05-19 18:39:20');
INSERT INTO `report_light` VALUES (285, 'corridor_light_001', 0, 0, 135, 18, '2026-05-19 18:39:34');
INSERT INTO `report_light` VALUES (286, 'corridor_light_001', 0, 0, 123, 18, '2026-05-19 18:39:48');
INSERT INTO `report_light` VALUES (287, 'corridor_light_001', 0, 0, 135, 18, '2026-05-19 18:40:02');
INSERT INTO `report_light` VALUES (288, 'corridor_light_001', 0, 0, 124, 18, '2026-05-19 18:40:16');
INSERT INTO `report_light` VALUES (289, 'corridor_light_001', 0, 0, 234, 18, '2026-05-19 18:40:30');
INSERT INTO `report_light` VALUES (290, 'corridor_light_001', 0, 0, 189, 18, '2026-05-19 18:40:44');
INSERT INTO `report_light` VALUES (291, 'corridor_light_001', 0, 0, 185, 18, '2026-05-19 18:40:58');
INSERT INTO `report_light` VALUES (292, 'corridor_light_001', 0, 0, 128, 18, '2026-05-19 18:41:12');
INSERT INTO `report_light` VALUES (293, 'corridor_light_001', 0, 0, 181, 18, '2026-05-19 18:41:26');
INSERT INTO `report_light` VALUES (294, 'corridor_light_001', 0, 0, 176, 18, '2026-05-19 18:41:40');
INSERT INTO `report_light` VALUES (295, 'corridor_light_001', 1, 40, 166, 18, '2026-05-19 18:41:54');
INSERT INTO `report_light` VALUES (296, 'corridor_light_001', 1, 40, 114, 18, '2026-05-19 18:42:08');
INSERT INTO `report_light` VALUES (297, 'corridor_light_001', 1, 40, 180, 18, '2026-05-19 18:42:22');
INSERT INTO `report_light` VALUES (298, 'corridor_light_001', 1, 40, 138, 18, '2026-05-19 18:42:36');
INSERT INTO `report_light` VALUES (299, 'corridor_light_001', 1, 40, 140, 18, '2026-05-19 18:42:50');
INSERT INTO `report_light` VALUES (300, 'corridor_light_001', 1, 40, 129, 18, '2026-05-19 18:43:04');
INSERT INTO `report_light` VALUES (301, 'corridor_light_001', 1, 40, 138, 18, '2026-05-19 18:43:18');
INSERT INTO `report_light` VALUES (302, 'corridor_light_001', 1, 40, 173, 18, '2026-05-19 18:43:32');
INSERT INTO `report_light` VALUES (303, 'corridor_light_001', 1, 50, 214, 21, '2026-05-19 21:30:13');
INSERT INTO `report_light` VALUES (304, 'corridor_light_001', 1, 50, 214, 22, '2026-05-19 22:30:13');
INSERT INTO `report_light` VALUES (305, 'corridor_light_001', 1, 50, 214, 22, '2026-05-19 22:30:13');
INSERT INTO `report_light` VALUES (306, 'corridor_light_001', 1, 50, 214, 22, '2026-05-19 22:30:13');
INSERT INTO `report_light` VALUES (307, 'corridor_light_001', 1, 50, 214, 23, '2026-05-19 23:30:13');
INSERT INTO `report_light` VALUES (308, 'corridor_light_001', 1, 50, 214, 23, '2026-05-19 23:30:13');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '处理结果表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of result_info
-- ----------------------------
INSERT INTO `result_info` VALUES (1, 2, 'user00002', '已处理', '2026-05-11 18:07:13');

-- ----------------------------
-- Table structure for save_info
-- ----------------------------
DROP TABLE IF EXISTS `save_info`;
CREATE TABLE `save_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `device_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `current_time` datetime NULL DEFAULT NULL COMMENT '当前时间，精确到天',
  `current_hour` int NULL DEFAULT NULL COMMENT '当前小时',
  `power` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节电量',
  `carbon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节碳量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '节能统计表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of save_info
-- ----------------------------
INSERT INTO `save_info` VALUES (1, 'corridor_light_001', '2026-03-10 00:00:00', 16, '3000.0', '450.0');
INSERT INTO `save_info` VALUES (2, 'corridor_light_001', '2026-05-10 00:00:00', 16, '5.0', '0.075');
INSERT INTO `save_info` VALUES (3, 'corridor_light_001', '2026-05-10 00:00:00', 17, '5.0', '0.075');
INSERT INTO `save_info` VALUES (4, 'corridor_light_001', '2026-05-10 00:00:00', 18, '5.0', '0.075');
INSERT INTO `save_info` VALUES (5, 'corridor_light_001', '2026-05-10 00:00:00', 11, '5.0', '0.075');
INSERT INTO `save_info` VALUES (6, 'corridor_light_001', '2026-05-18 00:00:00', 22, '230.0', '3.450000000000003');
INSERT INTO `save_info` VALUES (7, 'corridor_light_001', '2026-05-19 00:00:00', 17, '315.0', '4.725000000000006');
INSERT INTO `save_info` VALUES (8, 'corridor_light_001', '2026-05-19 00:00:00', 18, '1103.0', '16.545');
INSERT INTO `save_info` VALUES (9, 'corridor_light_001', '2026-05-19 00:00:00', 21, '5.0', '0.075');
INSERT INTO `save_info` VALUES (10, 'corridor_light_001', '2026-05-19 00:00:00', 22, '60.0', '0.9');
INSERT INTO `save_info` VALUES (11, 'corridor_light_001', '2026-05-19 00:00:00', 23, '100.0', '1.5');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户设备关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_device_bound
-- ----------------------------
INSERT INTO `user_device_bound` VALUES (1, 'user0003', 'corridor_light_001', '2026-05-09 20:01:50');

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
INSERT INTO `user_info` VALUES ('user00002', '12312341234', 'devops', '123123', '2026-05-09 20:00:44', 1);
INSERT INTO `user_info` VALUES ('user0001', '18503461776', 'root', '123123', '2026-05-09 20:00:01', 2);
INSERT INTO `user_info` VALUES ('user0003', '12312345678', 'user', '123123', '2026-05-09 20:01:13', 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '告警工单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warn_order
-- ----------------------------
INSERT INTO `warn_order` VALUES (1, 'corridor_light_001', '设备从未上报状态', '2026-05-07 20:32:03', NULL, 0, NULL);
INSERT INTO `warn_order` VALUES (2, 'corridor_light_001', '设备异常断开连接', '2026-05-07 20:47:06', 'user00002', 1, '2026-05-11 18:07:13');
INSERT INTO `warn_order` VALUES (3, 'corridor_light_001', '设备异常断开连接', '2026-05-18 22:18:50', NULL, 0, NULL);
INSERT INTO `warn_order` VALUES (4, 'corridor_light_001', '设备异常断开连接', '2026-05-18 22:21:50', NULL, 0, NULL);
INSERT INTO `warn_order` VALUES (5, 'corridor_light_001', '设备异常断开连接', '2026-05-18 22:32:50', NULL, 0, NULL);
INSERT INTO `warn_order` VALUES (6, 'corridor_light_001', '设备异常断开连接', '2026-05-19 15:01:42', NULL, 0, NULL);
INSERT INTO `warn_order` VALUES (7, 'corridor_light_001', '设备异常断开连接', '2026-05-19 21:23:41', NULL, 0, NULL);
INSERT INTO `warn_order` VALUES (8, 'corridor_light_001', '设备异常断开连接', '2026-06-02 13:05:44', NULL, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
