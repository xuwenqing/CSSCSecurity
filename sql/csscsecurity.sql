/*
Navicat MySQL Data Transfer

Source Server         : conn
Source Server Version : 50542
Source Host           : localhost:3306
Source Database       : csscsecurity

Target Server Type    : MYSQL
Target Server Version : 50542
File Encoding         : 65001

Date: 2016-05-26 21:55:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `law`
-- ----------------------------
DROP TABLE IF EXISTS `law`;
CREATE TABLE `law` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '法律法规名称',
  `publish_institution` varchar(100) COLLATE utf8_bin DEFAULT '' COMMENT '法律法规发布单位',
  `document_number` varchar(100) COLLATE utf8_bin DEFAULT '' COMMENT '法律法规文号',
  `publish_time` date DEFAULT NULL COMMENT '法律法规发布时间',
  `active_time` date DEFAULT NULL COMMENT '法律法规生效时间',
  `category` varchar(30) COLLATE utf8_bin DEFAULT '' COMMENT '法律法规所属类别（法律，法规，地方性法规）',
  `content` text COLLATE utf8_bin COMMENT '法律法规内容',
  `view_count` int(11) DEFAULT NULL COMMENT '浏览数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of law
-- ----------------------------

-- ----------------------------
-- Table structure for `register`
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register` (
  `username` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(30) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of register
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(10) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
