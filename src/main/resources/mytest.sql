/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : mytest

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-05-23 10:59:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_permissions
-- ----------------------------
DROP TABLE IF EXISTS `system_permissions`;
CREATE TABLE `system_permissions` (
  `id` int(11) NOT NULL,
  `permissions_name` varchar(255) DEFAULT NULL,
  `createDatetime` datetime DEFAULT NULL,
  `murl` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `createDatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_rolepermissions
-- ----------------------------
DROP TABLE IF EXISTS `system_rolepermissions`;
CREATE TABLE `system_rolepermissions` (
  `role_id` int(11) NOT NULL,
  `permissions_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permissions_id`),
  KEY `RPperid` (`permissions_id`),
  CONSTRAINT `RPperid` FOREIGN KEY (`permissions_id`) REFERENCES `system_permissions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `RProleid` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `createDatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Sname` (`name`),
  KEY `id` (`id`),
  FULLTEXT KEY `content` (`content`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for system_userrole
-- ----------------------------
DROP TABLE IF EXISTS `system_userrole`;
CREATE TABLE `system_userrole` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `URroleid` (`role_id`),
  CONSTRAINT `URroleid` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `URuserid` FOREIGN KEY (`user_id`) REFERENCES `system_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
