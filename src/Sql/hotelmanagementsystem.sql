/*
 Navicat Premium Data Transfer

 Source Server         : fushiyuchen
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : hotelmanagementsystem

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 10/06/2024 18:58:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '123456');

-- ----------------------------
-- Table structure for booking
-- ----------------------------
DROP TABLE IF EXISTS `booking`;
CREATE TABLE `booking`  (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `guest_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  `room_number` int NULL DEFAULT NULL,
  `check_in_date` datetime NULL DEFAULT NULL,
  `check_out_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`booking_id`) USING BTREE,
  INDEX `guest_name`(`guest_name`) USING BTREE,
  INDEX `room_number`(`room_number`) USING BTREE,
  CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`guest_name`) REFERENCES `guests` (`guest_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`room_number`) REFERENCES `rooms` (`room_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of booking
-- ----------------------------
INSERT INTO `booking` VALUES (1, '陈jack', 101, '2024-06-07 00:00:00', '2024-06-17 00:00:00');

-- ----------------------------
-- Table structure for guests
-- ----------------------------
DROP TABLE IF EXISTS `guests`;
CREATE TABLE `guests`  (
  `guest_id` int NOT NULL AUTO_INCREMENT,
  `guest_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  `guest_gender` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  `guest_contact` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  `id_number` varbinary(100) NULL DEFAULT NULL,
  `check_in_date` datetime NULL DEFAULT NULL,
  `expected_stay` int NULL DEFAULT NULL,
  PRIMARY KEY (`guest_id`) USING BTREE,
  INDEX `guest_name`(`guest_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guests
-- ----------------------------
INSERT INTO `guests` VALUES (1, '陈Jack', '男', '19950494372', 0x3139393530343934333732, '2024-06-07 00:00:00', 10);

-- ----------------------------
-- Table structure for rooms
-- ----------------------------
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms`  (
  `room_id` int NOT NULL AUTO_INCREMENT,
  `room_number` int NULL DEFAULT NULL,
  `room_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  `room_price` decimal(10, 2) NULL DEFAULT NULL,
  `room_discount` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  `STATUS` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  `room_manager` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  `room_contact` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_croatian_ci NULL DEFAULT NULL,
  PRIMARY KEY (`room_id`) USING BTREE,
  INDEX `room_number`(`room_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rooms
-- ----------------------------
INSERT INTO `rooms` VALUES (1, 101, '标准间', 120.00, '0.86', '空闲', '陈jack', '19950494372');
INSERT INTO `rooms` VALUES (2, 102, '标准间', 120.00, '0.86', '已预定', '司tom', '110');
INSERT INTO `rooms` VALUES (111, 102, '标准间', 120.00, '0.86', '已预定', '司tom', '110');

SET FOREIGN_KEY_CHECKS = 1;
