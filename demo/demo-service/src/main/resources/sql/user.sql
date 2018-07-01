/*
Navicat MySQL Data Transfer

Source Server         : 10.58.189.10
Source Server Version : 50614
Source Host           : 10.58.189.10:3306
Source Database       : private

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-11-27 17:04:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `created_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '12345', '若有情', '2017-11-22 14:45:28', '2017-11-22 14:45:31');
