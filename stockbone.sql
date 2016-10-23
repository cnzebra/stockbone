/*
Navicat MySQL Data Transfer

Source Server         : db4free
Source Server Version : 50623
Source Host           : db4free.net:3306
Source Database       : stockbone

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2015-02-08 00:39:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `candle_data`
-- ----------------------------
DROP TABLE IF EXISTS `candle_data`;
CREATE TABLE `candle_data` (
  `id` varchar(32) NOT NULL,
  `code` varchar(8) NOT NULL,
  `time` bigint(16) NOT NULL,
  `trade_period` int(16) NOT NULL,
  `open` float(8,3) NOT NULL,
  `high` float(8,3) NOT NULL,
  `low` float(8,3) NOT NULL,
  `close` float(8,3) NOT NULL,
  `adj` float(8,3) DEFAULT NULL,
  `volume` bigint(16) NOT NULL,
  `amount` float(16,3) NOT NULL,
  `average_price` float(8,3) DEFAULT NULL,
  `ma5` float(8,3) DEFAULT NULL,
  `ma10` float(8,3) DEFAULT NULL,
  `ma30` float(8,3) DEFAULT NULL,
  `ma60` float(8,3) DEFAULT NULL,
  `ma120` float(8,3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `candle_stock` (`code`),
  CONSTRAINT `candle_stock` FOREIGN KEY (`code`) REFERENCES `stock` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of candle_data
-- ----------------------------

-- ----------------------------
-- Table structure for `share_change`
-- ----------------------------
DROP TABLE IF EXISTS `share_change`;
CREATE TABLE `share_change` (
  `id` varchar(32) NOT NULL,
  `code` varchar(8) NOT NULL,
  `change_date` bigint(16) NOT NULL,
  `a_shares` bigint(16) NOT NULL,
  `b_shares` bigint(16) DEFAULT NULL,
  `total_shares` bigint(16) DEFAULT NULL,
  `change_reason` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `share_stock` (`code`),
  CONSTRAINT `share_stock` FOREIGN KEY (`code`) REFERENCES `stock` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of share_change
-- ----------------------------

-- ----------------------------
-- Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `code` varchar(8) NOT NULL DEFAULT '' COMMENT '股票编码',
  `name` varchar(64) DEFAULT NULL COMMENT '股票名称',
  `exchange` enum('HK','SZ','SH') NOT NULL COMMENT '交易所',
  `go_public_time` bigint(16) DEFAULT NULL COMMENT '上市时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stock
-- ----------------------------
