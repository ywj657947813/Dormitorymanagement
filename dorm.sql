/*
Navicat MySQL Data Transfer

Source Server         : yuweijing
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : dorm

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-05-24 15:49:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` char(8) CHARACTER SET utf8 NOT NULL,
  `pass` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('02140801', '123');
INSERT INTO `admin` VALUES ('02140802', '123');
INSERT INTO `admin` VALUES ('02140803', '123');
INSERT INTO `admin` VALUES ('02140804', '123');
INSERT INTO `admin` VALUES ('02140805', '123');
INSERT INTO `admin` VALUES ('02140806', '123');
INSERT INTO `admin` VALUES ('02140807', '123');
INSERT INTO `admin` VALUES ('02140808', '123');
INSERT INTO `admin` VALUES ('02140809', '123');
INSERT INTO `admin` VALUES ('02140810', '123');
INSERT INTO `admin` VALUES ('02140811', '123');
INSERT INTO `admin` VALUES ('02140812', '123');
INSERT INTO `admin` VALUES ('02140832', '123');
INSERT INTO `admin` VALUES ('02140901', '123');
INSERT INTO `admin` VALUES ('02140902', '123');
INSERT INTO `admin` VALUES ('02140903', '123');
INSERT INTO `admin` VALUES ('02140904', '123');
INSERT INTO `admin` VALUES ('02140905', '123');
INSERT INTO `admin` VALUES ('02140906', '123');
INSERT INTO `admin` VALUES ('02140907', '123');
INSERT INTO `admin` VALUES ('02140908', '123');
INSERT INTO `admin` VALUES ('02140909', '123');
INSERT INTO `admin` VALUES ('02140910', '123');
INSERT INTO `admin` VALUES ('02140911', '123');
INSERT INTO `admin` VALUES ('02140912', '123');
INSERT INTO `admin` VALUES ('02140913', '123');
INSERT INTO `admin` VALUES ('02140914', '123');
INSERT INTO `admin` VALUES ('02140915', '123');
INSERT INTO `admin` VALUES ('02140916', '123');
INSERT INTO `admin` VALUES ('02140917', '123');
INSERT INTO `admin` VALUES ('02140918', '123');
INSERT INTO `admin` VALUES ('02140919', '123');
INSERT INTO `admin` VALUES ('02140920', '123');
INSERT INTO `admin` VALUES ('02140921', '123');
INSERT INTO `admin` VALUES ('02140922', '123');
INSERT INTO `admin` VALUES ('02140923', '123');
INSERT INTO `admin` VALUES ('02140924', '123');
INSERT INTO `admin` VALUES ('02140925', '123');
INSERT INTO `admin` VALUES ('02140926', '123');
INSERT INTO `admin` VALUES ('02140927', '123');
INSERT INTO `admin` VALUES ('02140928', '123');
INSERT INTO `admin` VALUES ('02140929', '123');
INSERT INTO `admin` VALUES ('02140930', '123');
INSERT INTO `admin` VALUES ('02140931', '123');
INSERT INTO `admin` VALUES ('02140932', '123');
INSERT INTO `admin` VALUES ('02140933', '123');
INSERT INTO `admin` VALUES ('02140934', '123');
INSERT INTO `admin` VALUES ('02140935', '123');
INSERT INTO `admin` VALUES ('02140936', '123');
INSERT INTO `admin` VALUES ('02140937', '123');
INSERT INTO `admin` VALUES ('02140938', '123');
INSERT INTO `admin` VALUES ('02140939', '123');
INSERT INTO `admin` VALUES ('02140940', '123');
INSERT INTO `admin` VALUES ('02140941', '123');
INSERT INTO `admin` VALUES ('02140942', '123');
INSERT INTO `admin` VALUES ('02140943', '123');
INSERT INTO `admin` VALUES ('04140101', '123');
INSERT INTO `admin` VALUES ('04140102', '123');
INSERT INTO `admin` VALUES ('04140103', '123');
INSERT INTO `admin` VALUES ('04140104', '123');
INSERT INTO `admin` VALUES ('04140105', '123');
INSERT INTO `admin` VALUES ('04140106', '123');
INSERT INTO `admin` VALUES ('04140107', '123');
INSERT INTO `admin` VALUES ('04140108', '123');
INSERT INTO `admin` VALUES ('04140109', '123');
INSERT INTO `admin` VALUES ('04140110', '123');
INSERT INTO `admin` VALUES ('04140801', '123');
INSERT INTO `admin` VALUES ('04140802', '123');
INSERT INTO `admin` VALUES ('04140803', '123');
INSERT INTO `admin` VALUES ('04140804', '123');
INSERT INTO `admin` VALUES ('04140805', '123');
INSERT INTO `admin` VALUES ('04140806', '123');
INSERT INTO `admin` VALUES ('04140811', '123');
INSERT INTO `admin` VALUES ('04140819', '123');
INSERT INTO `admin` VALUES ('04140826', '123');
INSERT INTO `admin` VALUES ('04140831', '123');

-- ----------------------------
-- Table structure for `depa`
-- ----------------------------
DROP TABLE IF EXISTS `depa`;
CREATE TABLE `depa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depaId` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `depaNa` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `depaid` (`depaId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of depa
-- ----------------------------
INSERT INTO `depa` VALUES ('1', '04', '计算机科学与技术系');
INSERT INTO `depa` VALUES ('2', '02', '工商管理系');

-- ----------------------------
-- Table structure for `dorms`
-- ----------------------------
DROP TABLE IF EXISTS `dorms`;
CREATE TABLE `dorms` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `dormna` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of dorms
-- ----------------------------
INSERT INTO `dorms` VALUES ('1', '榕一');
INSERT INTO `dorms` VALUES ('2', '榕二');
INSERT INTO `dorms` VALUES ('3', '榕三');
INSERT INTO `dorms` VALUES ('4', '榕四');
INSERT INTO `dorms` VALUES ('5', '榕五');
INSERT INTO `dorms` VALUES ('6', '榕九');

-- ----------------------------
-- Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `gradeid` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `gradena` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `majorid` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`gradeid`),
  KEY `FK_g_majorid` (`majorid`),
  CONSTRAINT `FK_g_majorid` FOREIGN KEY (`majorid`) REFERENCES `major` (`majorId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('020101', '会计一班', '0201');
INSERT INTO `grade` VALUES ('020102', '会计二班', '0201');
INSERT INTO `grade` VALUES ('020103', '会计三班', '0201');
INSERT INTO `grade` VALUES ('020104', '会计四班', '0201');
INSERT INTO `grade` VALUES ('020105', '会计五班', '0201');
INSERT INTO `grade` VALUES ('040101', '软工一班', '0401');
INSERT INTO `grade` VALUES ('040102', '软工二班', '0401');
INSERT INTO `grade` VALUES ('040201', '网工一班', '0402');
INSERT INTO `grade` VALUES ('040202', '网工二班', '0402');
INSERT INTO `grade` VALUES ('040203', '网工三班', '0402');

-- ----------------------------
-- Table structure for `leavemsg`
-- ----------------------------
DROP TABLE IF EXISTS `leavemsg`;
CREATE TABLE `leavemsg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuid` char(8) NOT NULL,
  `name` varchar(5) NOT NULL,
  `firsttime` char(10) NOT NULL,
  `lasttime` char(10) NOT NULL,
  `leavereason` varchar(30) NOT NULL,
  `address` char(8) DEFAULT NULL,
  `studentphone` char(11) NOT NULL,
  `parentsphone` char(11) NOT NULL,
  `state` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of leavemsg
-- ----------------------------
INSERT INTO `leavemsg` VALUES ('1', '04140826', '余伟靖', '2017-03-11', '2017-04-08', '有事', '吉珠外', '13631260350', '12345678910', '已结束');
INSERT INTO `leavemsg` VALUES ('6', '04140819', '唐建章', '2017-04-14', '2017-04-18', '11', '11', '13631260350', '13631260350', '已拒绝');
INSERT INTO `leavemsg` VALUES ('9', '02140801', '张三', '2017-04-06', '2017-04-11', '有事', '吉珠外', '12345678911', '98765432101', '已审批');

-- ----------------------------
-- Table structure for `l_service`
-- ----------------------------
DROP TABLE IF EXISTS `l_service`;
CREATE TABLE `l_service` (
  `id` varchar(8) CHARACTER SET utf8 NOT NULL,
  `pass` varchar(16) CHARACTER SET utf8 DEFAULT NULL,
  `typeid` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of l_service
-- ----------------------------
INSERT INTO `l_service` VALUES ('123', '123', '水电设施');
INSERT INTO `l_service` VALUES ('456', '123', '公共设施');
INSERT INTO `l_service` VALUES ('789', '123', '家具设施');

-- ----------------------------
-- Table structure for `l_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `l_teacher`;
CREATE TABLE `l_teacher` (
  `id` varchar(8) CHARACTER SET utf8 NOT NULL,
  `pass` varchar(16) CHARACTER SET utf8 NOT NULL,
  `depaid` varchar(5) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of l_teacher
-- ----------------------------
INSERT INTO `l_teacher` VALUES ('123', '123', '04');
INSERT INTO `l_teacher` VALUES ('456', '123', '02');

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `majorId` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `majorNa` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `depaid` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_depaId` (`depaid`),
  KEY `majorid` (`majorId`),
  CONSTRAINT `FK_depaId` FOREIGN KEY (`depaid`) REFERENCES `depa` (`depaId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1', '0401', '软件工程', '04');
INSERT INTO `major` VALUES ('2', '0402', '网络工程', '04');
INSERT INTO `major` VALUES ('3', '0201', '会计学', '02');

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `otid` int(10) NOT NULL AUTO_INCREMENT,
  `count` int(5) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` int(10) DEFAULT NULL,
  `oid` int(10) DEFAULT NULL,
  `dormid` int(5) DEFAULT NULL,
  PRIMARY KEY (`otid`),
  KEY `FK_dormid` (`dormid`),
  KEY `FK_pid` (`pid`),
  KEY `FK_oid` (`oid`),
  CONSTRAINT `FK_dormid` FOREIGN KEY (`dormid`) REFERENCES `dorms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_oid` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_pid` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('3', '11', '220', '1', '265931', '1');
INSERT INTO `orderitem` VALUES ('8', '2', '200', '6', '348651', '1');
INSERT INTO `orderitem` VALUES ('9', '2', '40', '7', '348651', '1');
INSERT INTO `orderitem` VALUES ('10', '2', '40', '1', '420963', '1');
INSERT INTO `orderitem` VALUES ('25', '1', '20', '1', '428590', '1');
INSERT INTO `orderitem` VALUES ('27', '1', '30', '5', '428590', '2');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `oid` int(10) NOT NULL AUTO_INCREMENT,
  `otime` date DEFAULT NULL,
  `state` int(2) DEFAULT NULL,
  `suid` varchar(8) DEFAULT NULL,
  `descs` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `FK_suid` (`suid`),
  CONSTRAINT `FK_suid` FOREIGN KEY (`suid`) REFERENCES `l_service` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=428591 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('265931', '2017-05-12', '3', '123', '长时间未领取！');
INSERT INTO `orders` VALUES ('348651', '2017-05-12', '3', '123', '库存不足！');
INSERT INTO `orders` VALUES ('420963', '2017-05-12', '2', '456', null);
INSERT INTO `orders` VALUES ('428590', '2017-05-24', '1', '123', '');

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) DEFAULT NULL,
  `pprice` double(10,0) DEFAULT NULL,
  `ptotal` int(5) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '灯泡', '22', '1010');
INSERT INTO `product` VALUES ('2', '水龙头', '15', '890');
INSERT INTO `product` VALUES ('3', '凳子', '30', '600');
INSERT INTO `product` VALUES ('4', '门锁', '45', '290');
INSERT INTO `product` VALUES ('5', '窗帘', '30', '99');
INSERT INTO `product` VALUES ('6', '风扇', '100', '48');
INSERT INTO `product` VALUES ('7', '灯管', '20', '298');
INSERT INTO `product` VALUES ('8', '电胶布', '5', '300');
INSERT INTO `product` VALUES ('10', '21', '1', '1');
INSERT INTO `product` VALUES ('11', '2121', '1', '1');
INSERT INTO `product` VALUES ('12', '2121', '1', '1');
INSERT INTO `product` VALUES ('13', '23', '1', '1');
INSERT INTO `product` VALUES ('14', '321', '1', '1');

-- ----------------------------
-- Table structure for `rooms`
-- ----------------------------
DROP TABLE IF EXISTS `rooms`;
CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomno` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `dormid` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rooms
-- ----------------------------
INSERT INTO `rooms` VALUES ('1', 'R1-101', '5');
INSERT INTO `rooms` VALUES ('2', 'R1-102', '5');
INSERT INTO `rooms` VALUES ('3', 'R1-103', '5');
INSERT INTO `rooms` VALUES ('4', 'R2-201', '5');
INSERT INTO `rooms` VALUES ('5', 'R2-202', '5');
INSERT INTO `rooms` VALUES ('6', 'R2-203', '5');
INSERT INTO `rooms` VALUES ('7', 'R3-301', '5');
INSERT INTO `rooms` VALUES ('8', 'R3-302', '5');
INSERT INTO `rooms` VALUES ('9', 'R3-303', '5');
INSERT INTO `rooms` VALUES ('10', 'R4-401', '5');
INSERT INTO `rooms` VALUES ('11', 'R4-402', '5');
INSERT INTO `rooms` VALUES ('12', 'R4-403', '5');
INSERT INTO `rooms` VALUES ('13', 'R5-501', '5');
INSERT INTO `rooms` VALUES ('14', 'R5-502', '5');
INSERT INTO `rooms` VALUES ('15', 'R5-503', '5');
INSERT INTO `rooms` VALUES ('16', 'R6-601', '5');
INSERT INTO `rooms` VALUES ('17', 'R6-602', '5');
INSERT INTO `rooms` VALUES ('18', 'R6-603', '5');
INSERT INTO `rooms` VALUES ('20', 'R1-101', '1');
INSERT INTO `rooms` VALUES ('21', 'R1-102', '1');
INSERT INTO `rooms` VALUES ('22', 'R1-103', '1');
INSERT INTO `rooms` VALUES ('23', 'R2-201', '1');
INSERT INTO `rooms` VALUES ('24', 'R2-202', '1');
INSERT INTO `rooms` VALUES ('25', 'R2-203', '1');
INSERT INTO `rooms` VALUES ('26', 'R3-301', '1');
INSERT INTO `rooms` VALUES ('27', 'R3-302', '1');
INSERT INTO `rooms` VALUES ('28', 'R3-303', '1');
INSERT INTO `rooms` VALUES ('29', 'R4-401', '1');
INSERT INTO `rooms` VALUES ('30', 'R4-402', '1');
INSERT INTO `rooms` VALUES ('31', 'R4-403', '1');
INSERT INTO `rooms` VALUES ('32', 'R5-501', '1');
INSERT INTO `rooms` VALUES ('33', 'R5-502', '1');
INSERT INTO `rooms` VALUES ('34', 'R5-503', '1');
INSERT INTO `rooms` VALUES ('35', 'R6-601', '1');
INSERT INTO `rooms` VALUES ('36', 'R6-602', '1');
INSERT INTO `rooms` VALUES ('37', 'R6-603', '1');
INSERT INTO `rooms` VALUES ('38', 'R1-101', '2');
INSERT INTO `rooms` VALUES ('39', 'R1-102', '2');
INSERT INTO `rooms` VALUES ('40', 'R1-103', '2');
INSERT INTO `rooms` VALUES ('41', 'R2-201', '2');
INSERT INTO `rooms` VALUES ('42', 'R2-202', '2');
INSERT INTO `rooms` VALUES ('43', 'R2-203', '2');
INSERT INTO `rooms` VALUES ('44', 'R3-301', '2');
INSERT INTO `rooms` VALUES ('45', 'R3-302', '2');
INSERT INTO `rooms` VALUES ('46', 'R3-303', '2');
INSERT INTO `rooms` VALUES ('47', 'R4-401', '2');
INSERT INTO `rooms` VALUES ('48', 'R4-402', '2');
INSERT INTO `rooms` VALUES ('49', 'R4-403', '2');
INSERT INTO `rooms` VALUES ('50', 'R5-501', '2');
INSERT INTO `rooms` VALUES ('51', 'R5-502', '2');
INSERT INTO `rooms` VALUES ('52', 'R5-503', '2');
INSERT INTO `rooms` VALUES ('53', 'R6-601', '2');
INSERT INTO `rooms` VALUES ('54', 'R6-602', '2');
INSERT INTO `rooms` VALUES ('55', 'R6-603', '2');
INSERT INTO `rooms` VALUES ('56', 'R1-101', '3');
INSERT INTO `rooms` VALUES ('57', 'R1-102', '3');
INSERT INTO `rooms` VALUES ('58', 'R1-103', '3');
INSERT INTO `rooms` VALUES ('59', 'R2-201', '3');
INSERT INTO `rooms` VALUES ('60', 'R2-202', '3');
INSERT INTO `rooms` VALUES ('61', 'R2-203', '3');
INSERT INTO `rooms` VALUES ('62', 'R3-301', '3');
INSERT INTO `rooms` VALUES ('63', 'R3-302', '3');
INSERT INTO `rooms` VALUES ('64', 'R3-303', '3');
INSERT INTO `rooms` VALUES ('65', 'R4-401', '3');
INSERT INTO `rooms` VALUES ('66', 'R4-402', '3');
INSERT INTO `rooms` VALUES ('67', 'R4-403', '3');
INSERT INTO `rooms` VALUES ('68', 'R5-501', '3');
INSERT INTO `rooms` VALUES ('69', 'R5-502', '3');
INSERT INTO `rooms` VALUES ('70', 'R5-503', '3');
INSERT INTO `rooms` VALUES ('71', 'R6-601', '3');
INSERT INTO `rooms` VALUES ('72', 'R6-602', '3');
INSERT INTO `rooms` VALUES ('73', 'R6-603', '3');
INSERT INTO `rooms` VALUES ('74', 'R1-101', '4');
INSERT INTO `rooms` VALUES ('75', 'R1-102', '4');
INSERT INTO `rooms` VALUES ('76', 'R1-103', '4');
INSERT INTO `rooms` VALUES ('77', 'R2-201', '4');
INSERT INTO `rooms` VALUES ('78', 'R2-202', '4');
INSERT INTO `rooms` VALUES ('79', 'R2-203', '4');
INSERT INTO `rooms` VALUES ('80', 'R3-301', '4');
INSERT INTO `rooms` VALUES ('81', 'R3-302', '4');
INSERT INTO `rooms` VALUES ('82', 'R3-303', '4');
INSERT INTO `rooms` VALUES ('83', 'R4-401', '4');
INSERT INTO `rooms` VALUES ('84', 'R4-402', '4');
INSERT INTO `rooms` VALUES ('85', 'R4-403', '4');
INSERT INTO `rooms` VALUES ('86', 'R5-501', '4');
INSERT INTO `rooms` VALUES ('87', 'R5-502', '4');
INSERT INTO `rooms` VALUES ('88', 'R5-503', '4');
INSERT INTO `rooms` VALUES ('89', 'R6-601', '4');
INSERT INTO `rooms` VALUES ('90', 'R6-602', '4');
INSERT INTO `rooms` VALUES ('91', 'R6-603', '4');
INSERT INTO `rooms` VALUES ('92', 'R1-101', '6');
INSERT INTO `rooms` VALUES ('93', 'R1-102', '6');
INSERT INTO `rooms` VALUES ('94', 'R1-103', '6');
INSERT INTO `rooms` VALUES ('95', 'R2-201', '6');
INSERT INTO `rooms` VALUES ('96', 'R2-202', '6');
INSERT INTO `rooms` VALUES ('97', 'R2-203', '6');
INSERT INTO `rooms` VALUES ('98', 'R3-301', '6');
INSERT INTO `rooms` VALUES ('99', 'R3-302', '6');
INSERT INTO `rooms` VALUES ('100', 'R3-303', '6');
INSERT INTO `rooms` VALUES ('101', 'R4-401', '6');
INSERT INTO `rooms` VALUES ('102', 'R4-402', '6');
INSERT INTO `rooms` VALUES ('103', 'R4-403', '6');
INSERT INTO `rooms` VALUES ('104', 'R5-501', '6');
INSERT INTO `rooms` VALUES ('105', 'R5-502', '6');
INSERT INTO `rooms` VALUES ('106', 'R5-503', '6');
INSERT INTO `rooms` VALUES ('107', 'R6-601', '6');
INSERT INTO `rooms` VALUES ('108', 'R6-602', '6');
INSERT INTO `rooms` VALUES ('109', 'R6-603', '6');

-- ----------------------------
-- Table structure for `service`
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stuid` char(8) NOT NULL,
  `dormno` varchar(6) NOT NULL,
  `roomno` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `servicetype` varchar(10) NOT NULL,
  `servicereason` varchar(30) NOT NULL,
  `name` varchar(5) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `time` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `state` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of service
-- ----------------------------
INSERT INTO `service` VALUES ('5', '04140826', '5', 'R2-202', '公共设施', '111', '余伟靖', '13692562508', '2017-03-28', '已完成');
INSERT INTO `service` VALUES ('6', '04140819', '5', 'R2-201', '公共设施', '洗手间的灯泡坏了', '唐建章', '13631260350', '2017-04-01', '维修中');
INSERT INTO `service` VALUES ('8', '04140819', '5', 'R2-201', '家具设施', '111', '唐建章', '13631260350', '2017-04-03', '无库存');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` char(8) NOT NULL,
  `name` varchar(21) DEFAULT NULL,
  `depaid` varchar(5) DEFAULT NULL,
  `majorid` varchar(5) DEFAULT NULL,
  `gradeid` varchar(10) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `dormno` varchar(6) DEFAULT NULL,
  `roomno` varchar(10) DEFAULT NULL,
  `bedno` varchar(5) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  KEY `FK_majorId` (`majorid`),
  KEY `FK_Student_Room` (`roomno`),
  KEY `FK_depaId1` (`depaid`),
  KEY `FK_gradeid` (`gradeid`),
  CONSTRAINT `FK_depaId1` FOREIGN KEY (`depaid`) REFERENCES `depa` (`depaId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_gradeid` FOREIGN KEY (`gradeid`) REFERENCES `grade` (`gradeid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_majorId` FOREIGN KEY (`majorid`) REFERENCES `major` (`majorId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_stuId` FOREIGN KEY (`id`) REFERENCES `admin` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('02140801', '张三', '02', '0201', '020101', '男', '5', 'R3-301', '1', '123');
INSERT INTO `student` VALUES ('02140802', '2', '02', '0201', '020101', '男', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140803', '3', '02', '0201', '020101', '女', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140804', '4', '02', '0201', '020101', '女', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140805', '5', '02', '0201', '020101', '男', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140806', '6', '02', '0201', '020102', '女', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140807', '7', '02', '0201', '020102', '男', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140808', '8', '02', '0201', '020102', '男', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140809', '9', '02', '0201', '020102', '男', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140810', '10', '02', '0201', '020102', '男', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140811', '11', '02', '0201', '020102', '男', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140812', '12', '02', '0201', '020102', '女', '5', 'R3-301', '1', null);
INSERT INTO `student` VALUES ('02140832', '11', '02', '0201', '020105', '女', '2', 'R2-201', '1', '13631260350');
INSERT INTO `student` VALUES ('02140901', '测试111', '02', '0201', '020102', '男', '5', '', '', '123');
INSERT INTO `student` VALUES ('02140902', '17', '02', '0201', '020102', '男', '5', '', '', '456');
INSERT INTO `student` VALUES ('02140903', '18', '02', '0201', '020103', '男', '5', '', '', '789');
INSERT INTO `student` VALUES ('02140904', '19', '02', '0201', '020103', '女', '5', '', '', '123');
INSERT INTO `student` VALUES ('02140905', '20', '02', '0201', '020103', '女', '5', '', '', '456');
INSERT INTO `student` VALUES ('02140906', '21', '02', '0201', '020103', '女', '5', 'R3-301', '1', '789');
INSERT INTO `student` VALUES ('02140907', '22', '02', '0201', '020103', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140908', '23', '02', '0201', '020103', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140909', '24', '02', '0201', '020103', '', '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('02140910', '25', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140911', '26', '02', '0201', '020104', '', '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('02140912', '27', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140913', '28', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140914', '29', '02', '0201', '020104', '', '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('02140915', '30', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140916', '31', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140917', '32', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140918', '33', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140919', '34', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140920', '35', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140921', '36', '02', '0201', '020104', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140922', '37', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140923', '38', '02', '0201', '020105', '', '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('02140924', '39', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140925', '40', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140926', '41', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140927', '42', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140928', '43', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140929', '44', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140930', '45', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140931', '46', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140932', '47', '02', '0201', '020105', '', '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('02140933', '48', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140934', '49', '02', '0201', '020105', '', '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('02140935', '50', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140936', '51', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140937', '52', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140938', '53', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140939', '54', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140940', '55', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140941', '56', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140942', '57', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('02140943', '58', '02', '0201', '020105', '', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140101', '60', '04', '0402', '040201', null, '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140102', '61', '04', '0402', '040201', null, '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140103', '62', '04', '0402', '040201', null, '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140104', '63', '04', '0402', '040201', null, '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('04140105', '64', '04', '0402', '040201', null, '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140106', '65', '04', '0402', '040202', null, '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('04140107', '66', '04', '0402', '040202', null, '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140108', '67', '04', '0402', '040202', null, '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140109', '68', '04', '0402', '040202', null, '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('04140110', '69', '04', '0402', '040202', null, '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140801', '1', '04', '0402', '040202', '女', '5', 'R3-301', '1', '11');
INSERT INTO `student` VALUES ('04140802', '13', '04', '0401', '040101', '女', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140803', '14', '04', '0401', '040101', '男', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140804', '15', '04', '0401', '040101', '男', '5', 'R3-301', '1', '1');
INSERT INTO `student` VALUES ('04140805', '霍健聪', '04', '0401', '040101', '男', '5', 'R2-201', '1', '1');
INSERT INTO `student` VALUES ('04140806', '黎铠宁', '04', '0401', '040102', '男', '5', 'R2-201', '2', '1');
INSERT INTO `student` VALUES ('04140811', '林国滨', '04', '0401', '040102', '男', '5', 'R2-201', '3', '1');
INSERT INTO `student` VALUES ('04140819', '唐建章', '04', '0401', '040102', '男', '5', 'R2-201', '4', '13631260350');
INSERT INTO `student` VALUES ('04140826', '余伟靖', '04', '0401', '040102', '男', '5', 'R2-202', '1', '13692562508');
INSERT INTO `student` VALUES ('04140831', '111', '04', '0401', '040101', '男', '1', 'R1-101', '1', '12345678911');

-- ----------------------------
-- Table structure for `wrongmsg`
-- ----------------------------
DROP TABLE IF EXISTS `wrongmsg`;
CREATE TABLE `wrongmsg` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `stuid` char(8) CHARACTER SET utf8 NOT NULL,
  `name` varchar(5) CHARACTER SET utf8 NOT NULL,
  `wrongday` char(10) CHARACTER SET utf8 NOT NULL,
  `wrongreason` varchar(30) CHARACTER SET utf8 NOT NULL,
  `result` varchar(30) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of wrongmsg
-- ----------------------------
INSERT INTO `wrongmsg` VALUES ('13', '04140826', '余伟靖', '2017-03-24', '外网', '外网');
INSERT INTO `wrongmsg` VALUES ('19', '04140826', '余伟靖', '2017-04-07', '1', '1');
INSERT INTO `wrongmsg` VALUES ('20', '02140801', '张三', '2017-04-07', '唔知', '唔知');
