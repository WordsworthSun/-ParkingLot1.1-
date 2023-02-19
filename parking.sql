/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.4.17-MariaDB : Database - parking
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`parking` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `parking`;

/*Table structure for table `card` */

DROP TABLE IF EXISTS `card`;

CREATE TABLE `card` (
  `card_id` varchar(50) NOT NULL,
  `seat_id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_addr` varchar(50) DEFAULT NULL,
  `car_num` varchar(50) NOT NULL,
  PRIMARY KEY (`card_id`),
  KEY `user_id` (`user_id`),
  KEY `car` (`car_num`),
  KEY `seat_id` (`seat_id`),
  KEY `user_gender` (`user_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `card` */

insert  into `card`(`card_id`,`seat_id`,`user_id`,`user_phone`,`user_addr`,`car_num`) values ('20220420184252','EAST002','nike003','13285697416','商丘','豫s0018'),('20220420221430','EAST001','liyu001','15703694521','信阳','豫s9e10');

/*Table structure for table `fixed` */

DROP TABLE IF EXISTS `fixed`;

CREATE TABLE `fixed` (
  `fixed_id` varchar(50) NOT NULL,
  `card_id` varchar(50) NOT NULL,
  `entry_date` date NOT NULL,
  `entry_time` time NOT NULL,
  `out_date` date DEFAULT '1111-11-11',
  `out_time` time DEFAULT NULL,
  `car_num` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`fixed_id`),
  KEY `card_id` (`card_id`),
  KEY `car_num` (`car_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `fixed` */

insert  into `fixed`(`fixed_id`,`card_id`,`entry_date`,`entry_time`,`out_date`,`out_time`,`car_num`) values ('20220421101102','20220420184252','2022-04-21','10:11:02','2022-04-21','10:11:18','豫s0018'),('20220421101119','20220420184252','2022-04-21','10:11:19','2022-04-21','10:59:14','豫s0018'),('20220421101129','20220420221430','2022-04-21','10:11:29','2022-04-21','10:11:34','豫s9e10'),('20220421101140','20220420221430','2022-04-21','10:11:40','2022-04-21','10:59:16','豫s9e10'),('20220421173005','20220420184252','2022-04-21','17:30:05','2022-05-05','14:52:07','豫s0018'),('20220505145210','20220420184252','2022-05-05','14:52:10','1111-11-11','11:11:11','豫s0018');

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `user_id` varchar(50) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `context` varchar(255) DEFAULT NULL,
  `time` varchar(40) DEFAULT NULL,
  `message_id` varchar(40) NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

insert  into `message`(`user_id`,`title`,`context`,`time`,`message_id`) values ('SAdmin','公告','检查','20220321075008','孙延宇:075008'),('SAdmin','公告','检查','20220505145354','孙延宇:145354'),('SAdmin','公告','防疫','20220321160054','孙延宇:160054'),('SAdmin','公告','加油','20220322160202','孙延宇:160202'),('nike003','公告','第二次检查','20220427154105','李克:154105'),('nike003','公告','第四次检查','20220427154124','李克:154124'),('nike003','留言','车祸','20220427154908','李克:154908'),('nike003','留言','有车祸','20220427154918','李克:154918'),('nike003','留言','测试','20220427155012','李克:155012'),('nike003','留言','车位不够','20220427155112','李克:155112'),('nike003','留言','占用','20220427155950','李克:155950');

/*Table structure for table `seat` */

DROP TABLE IF EXISTS `seat`;

CREATE TABLE `seat` (
  `seat_id` varchar(50) NOT NULL,
  `seat_num` varchar(50) NOT NULL,
  `seat_section` varchar(50) NOT NULL,
  `seat_state` int(11) NOT NULL,
  `seat_tag` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`seat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `seat` */

insert  into `seat`(`seat_id`,`seat_num`,`seat_section`,`seat_state`,`seat_tag`) values ('EAST001','001','东区',0,'固定'),('EAST002','002','东区',1,'固定'),('North001','001','北区',1,'临时'),('North002','002','北区',0,'临时'),('South001','001','南区',0,'临时'),('South002','002','南区',0,'临时'),('West001','001','西区',0,'临时'),('West002','002','西区',0,'临时');

/*Table structure for table `temp` */

DROP TABLE IF EXISTS `temp`;

CREATE TABLE `temp` (
  `temp_id` varchar(50) NOT NULL,
  `card_id` varchar(50) NOT NULL,
  `car_num` varchar(50) NOT NULL,
  `entry_date` date NOT NULL,
  `entry_time` time NOT NULL,
  `out_date` date DEFAULT NULL,
  `out_time` time DEFAULT NULL,
  `temp_money` float DEFAULT NULL,
  `seat_id` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`temp_id`),
  KEY `card_id` (`card_id`),
  KEY `car_num` (`car_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `temp` */

insert  into `temp`(`temp_id`,`card_id`,`car_num`,`entry_date`,`entry_time`,`out_date`,`out_time`,`temp_money`,`seat_id`) values ('20220421074837','888811112222','豫s99','2022-04-21','07:48:37','2022-04-21','07:51:02',3,'South001'),('20220421082141','888811112222','豫s99','2022-04-21','08:21:41','2022-04-21','10:15:32',6,'North001'),('20220421082153','888811112222','豫s99','2022-04-21','08:21:53','2022-04-21','10:15:46',6,'North002'),('20220421101621','888811112222','豫s99','2022-04-21','10:16:21','2022-04-21','10:16:31',3,'North001'),('20220421101640','888811112222','豫s99','2022-04-21','10:16:40','2022-04-22','20:45:17',105,'North001'),('20220421142940','sczxvxcv','豫s99','2022-04-21','14:29:40','2022-04-22','20:45:21',93,'North002'),('20220422204403','1008611','豫s100','2022-04-22','20:44:03','2022-04-22','20:44:20',3,'South001'),('20220422210433','20224222104','豫s422','2022-04-22','21:04:33','1111-11-11','11:11:11',0,'North001'),('20220422210509','20224222105','豫s2105','2022-04-22','21:05:09','2022-04-22','21:05:21',3,'North002'),('20220505145301','1023030','豫s99','2022-05-05','14:53:01','2022-05-05','14:53:15',3,'North002');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` varchar(50) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `user_pwd` varchar(20) NOT NULL,
  `user_email` varchar(20) DEFAULT NULL,
  `real_name` varchar(50) NOT NULL,
  `user_phone` varchar(50) DEFAULT NULL,
  `user_gender` varchar(10) DEFAULT NULL,
  `user_birth` varchar(20) DEFAULT NULL,
  `car_num` varchar(20) DEFAULT NULL,
  `role_id` varchar(20) NOT NULL,
  `user_addr` varchar(50) DEFAULT NULL,
  `role_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `car_num` (`car_num`),
  KEY `user_gender` (`user_gender`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`user_pwd`,`user_email`,`real_name`,`user_phone`,`user_gender`,`user_birth`,`car_num`,`role_id`,`user_addr`,`role_name`) values ('20220421161328','jieke','123456','jieke@qq.com','张杰克','15036489856','男','1998-07-21','豫s99jk','a001','商丘','管理员'),('id007521','徐翔','123456','2268106643@qq.com','徐羊羽','15939752518','男','2003-02-22','豫s0052','c001','商丘','用户'),('liyu001','小玉','123456','15703694521@qq.com','李玉','15703694521','女','1990-12-20','豫s9e10','c001','信阳','用户'),('nike003','尼克','123456','13285697416@qq.com','李克','13285697417','男','1992-07-21','豫s0018','c001','商丘','用户'),('SAdmin','SAdmin','123456','2268106643','孙延宇','15803709572','男','1999-01-09','豫s8888','a001','淮滨','管理员'),('sasa123456','莎莎','123456','15039752569@qq.com','李莎莎','15039752569','女','1997-02-20','豫s99787','c001','信阳','用户'),('tom001','汤姆','123456','tom@.qq.com','张汤沐','15236841526','男','1986-05-06','豫s0206','c002','信阳','用户'),('yuehan001','约翰','123456','18036951257@qq.com','张悦','18036951257','男','1986-06-19','豫n0018','c001','商丘','用户');
