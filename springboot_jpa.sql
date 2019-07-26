/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : springboot_jpa

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 26/07/2019 17:57:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `authority_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`authority_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES (1, '超级管理员');
INSERT INTO `authority` VALUES (2, '主要管理员');
INSERT INTO `authority` VALUES (3, '次要管理员');
INSERT INTO `authority` VALUES (4, '一般管理员');
INSERT INTO `authority` VALUES (5, '普通用户');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `UK_iubw515ff0ugtm28p8g3myt0h`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '影视主角');
INSERT INTO `role` VALUES (2, '影视大侠');
INSERT INTO `role` VALUES (3, '影视配角');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_acount` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UK_lqjrcobrh9jc8wpcar64q1bfh`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'fengqy', '风清扬', '123456');
INSERT INTO `user` VALUES (2, 'renwox', '任我行', '123456');
INSERT INTO `user` VALUES (3, 'wentian', '向问天', '123456');
INSERT INTO `user` VALUES (4, 'linghuc', '令狐冲', '123456');
INSERT INTO `user` VALUES (6, 'yangjunfei', 'yangjunfei', '$2a$10$O.i0WiH/XuJ24hO5u5TCKOxzcduGLco7Y67WnfKN1fAQohZpSML8.');

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority`  (
  `authority_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`authority_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
INSERT INTO `user_authority` VALUES (1, 1);
INSERT INTO `user_authority` VALUES (1, 2);
INSERT INTO `user_authority` VALUES (1, 6);
INSERT INTO `user_authority` VALUES (2, 1);
INSERT INTO `user_authority` VALUES (2, 2);
INSERT INTO `user_authority` VALUES (3, 3);
INSERT INTO `user_authority` VALUES (4, 4);
INSERT INTO `user_authority` VALUES (5, 1);
INSERT INTO `user_authority` VALUES (5, 2);
INSERT INTO `user_authority` VALUES (5, 4);

-- ----------------------------
-- Table structure for user_authority_list
-- ----------------------------
DROP TABLE IF EXISTS `user_authority_list`;
CREATE TABLE `user_authority_list`  (
  `user_list_user_id` bigint(20) NOT NULL,
  `authority_list_authority_id` bigint(20) NOT NULL,
  INDEX `FKgh0mb4xd87mollkt9bm5yg55f`(`authority_list_authority_id`) USING BTREE,
  INDEX `FK8pia40p9v491jnp5i6u6ify71`(`user_list_user_id`) USING BTREE,
  CONSTRAINT `FK8pia40p9v491jnp5i6u6ify71` FOREIGN KEY (`user_list_user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKgh0mb4xd87mollkt9bm5yg55f` FOREIGN KEY (`authority_list_authority_id`) REFERENCES `authority` (`authority_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (1, 3);
INSERT INTO `user_role` VALUES (1, 5);
INSERT INTO `user_role` VALUES (2, 1);
INSERT INTO `user_role` VALUES (2, 3);
INSERT INTO `user_role` VALUES (3, 2);
INSERT INTO `user_role` VALUES (3, 4);

-- ----------------------------
-- Table structure for user_role_list
-- ----------------------------
DROP TABLE IF EXISTS `user_role_list`;
CREATE TABLE `user_role_list`  (
  `user_list_user_id` bigint(20) NOT NULL,
  `role_list_role_id` bigint(20) NOT NULL,
  INDEX `FK9sefiu6ih4lsgvhb5987konb3`(`role_list_role_id`) USING BTREE,
  INDEX `FKbwiedk41ysqgu62nunyuajuuq`(`user_list_user_id`) USING BTREE,
  CONSTRAINT `FK9sefiu6ih4lsgvhb5987konb3` FOREIGN KEY (`role_list_role_id`) REFERENCES `role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKbwiedk41ysqgu62nunyuajuuq` FOREIGN KEY (`user_list_user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
