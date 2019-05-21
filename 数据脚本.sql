-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: j2ee_final
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='模拟银行账户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,171,'2019-02-19 00:00:00','2019-03-27 08:34:04'),(2,233,'2019-02-19 00:00:00','2019-03-25 23:27:46'),(3,20.5,'2019-02-19 00:00:00','2019-03-27 09:14:54'),(4,400,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(5,500,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(6,600,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(7,738,'2019-02-19 00:00:00','2019-03-25 23:26:22'),(8,20000,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(9,20000,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(10,20000,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(11,30000,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(12,30000,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(13,40000,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(14,40000,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(15,50000,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(39,0,'2019-03-20 17:38:47','2019-03-20 17:38:47'),(96,3,'2019-03-27 08:34:19','2019-03-27 08:38:09'),(102,100,'2019-03-27 09:08:23','2019-03-27 09:08:23'),(105,0,'2019-03-27 09:10:08','2019-03-27 09:10:08');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountdetail`
--

DROP TABLE IF EXISTS `accountdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `accountdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL COMMENT '0为商家\n1为会员',
  `money` double NOT NULL DEFAULT '0',
  `mem_id` bigint(20) NOT NULL,
  `res_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `accountdetail_member_id_fk` (`mem_id`),
  KEY `accountdetail_restaurant_id_fk` (`res_id`),
  CONSTRAINT `accountdetail_member_id_fk` FOREIGN KEY (`mem_id`) REFERENCES `member` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `accountdetail_restaurant_id_fk` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='账户明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountdetail`
--

LOCK TABLES `accountdetail` WRITE;
/*!40000 ALTER TABLE `accountdetail` DISABLE KEYS */;
INSERT INTO `accountdetail` VALUES (51,1,95,1,1,'2019-03-25 02:50:48','2019-03-25 02:50:48'),(64,1,38,1,3,'2019-03-25 14:54:51','2019-03-25 14:54:51'),(65,1,33,1,2,'2019-03-25 23:00:26','2019-03-25 23:00:26'),(69,1,18,1,1,'2019-03-25 23:29:47','2019-03-25 23:29:47'),(74,1,13,1,6,'2019-03-25 23:56:22','2019-03-25 23:56:22'),(78,1,34,1,1,'2019-03-26 16:12:31','2019-03-26 16:12:31'),(112,1,17,1,1,'2019-03-27 09:14:09','2019-03-27 09:14:09'),(117,1,31.5,1,1,'2019-03-27 09:14:54','2019-03-27 09:14:54');
/*!40000 ALTER TABLE `accountdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `mem_id` bigint(20) NOT NULL,
  `priority` int(11) NOT NULL,
  `coordinate` varchar(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `local_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,1,'118.95,32.30','2019-02-19 00:00:00','2019-02-19 00:00:00','汉口路22号 陶园二舍'),(2,1,0,'119.95,32.12','2019-02-19 00:00:00','2019-02-19 00:00:00','汉口路22号 陶园一舍'),(3,1,0,'118.95,32.12','2019-02-19 00:00:00','2019-02-19 00:00:00','仙林大道163号 学生宿舍一栋'),(4,1,0,'118.95,32.20','2019-02-19 00:00:00','2019-02-19 00:00:00','仙林大道163号 学生宿舍二栋'),(5,2,1,'110.46,56.23','2019-02-19 00:00:00','2019-02-19 00:00:00','仙林大道163号 学生宿舍三栋'),(6,3,1,'118.95,32.05','2019-02-19 00:00:00','2019-02-19 00:00:00','仙林大道163号 学生宿舍一栋'),(7,4,1,'114.95,32.12','2019-02-19 00:00:00','2019-02-19 00:00:00','仙林大道163号 学生宿舍一栋'),(82,81,1,'118.95,32.30','2019-03-27 08:14:21','2019-03-27 08:14:21','汉口路22号 陶园二舍'),(104,103,1,'118.95,32.30','2019-03-27 09:08:23','2019-03-27 09:08:23','汉口路22号 陶园二舍');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approval`
--

DROP TABLE IF EXISTS `approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `approval` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `man_id` bigint(20) NOT NULL,
  `old_type` int(11) NOT NULL,
  `new_type` int(11) NOT NULL,
  `finished` int(11) NOT NULL DEFAULT '0',
  `old_coordinate` varchar(20) DEFAULT NULL,
  `new_coordinate` varchar(20) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `approval_manager_id_fk` (`man_id`),
  CONSTRAINT `approval_manager_id_fk` FOREIGN KEY (`man_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='审批';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval`
--

LOCK TABLES `approval` WRITE;
/*!40000 ALTER TABLE `approval` DISABLE KEYS */;
/*!40000 ALTER TABLE `approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '类型名称',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='餐厅类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'美食','2019-02-19 00:00:00','2019-02-19 00:00:00'),(2,'快餐便当','2019-02-19 00:00:00','2019-02-19 00:00:00'),(3,'特色菜系','2019-02-19 00:00:00','2019-02-19 00:00:00'),(4,'异国料理','2019-02-19 00:00:00','2019-02-19 00:00:00'),(5,'小吃夜宵','2019-02-19 00:00:00','2019-02-19 00:00:00'),(6,'甜品饮品','2019-02-19 00:00:00','2019-02-19 00:00:00'),(7,'果蔬生鲜','2019-02-19 00:00:00','2019-02-19 00:00:00'),(8,'商店超市','2019-02-19 00:00:00','2019-02-19 00:00:00');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `food` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `res_id` bigint(20) NOT NULL,
  `starttime` datetime NOT NULL,
  `endtime` datetime NOT NULL,
  `type` int(11) NOT NULL COMMENT '0表示已删除',
  `name` varchar(40) NOT NULL,
  `picture` varchar(255) NOT NULL,
  `amount` bigint(20) NOT NULL DEFAULT '0',
  `price` double NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `food_restaurant_id_fk` (`res_id`),
  CONSTRAINT `food_restaurant_id_fk` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='食品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,1,'2019-02-20 00:00:00','2019-12-01 00:00:00',1,'米饭1','https://sell-resources.oss-cn-beijing.aliyuncs.com/1/food_1.jpg',297,1.5,'2019-02-19 00:00:00','2019-03-27 09:14:32','香喷喷的米饭'),(2,1,'2019-02-20 00:00:00','2019-12-01 00:00:00',1,'韭菜炒蛋','https://sell-resources.oss-cn-beijing.aliyuncs.com/1/food_2.jpg',141,17,'2019-02-19 00:00:00','2019-05-21 13:33:02','韭菜炒鸡蛋是一道传统的特色名菜，不少菜系中均有出现，其中以在鲁菜最出名，原材料为韭菜、鸡蛋，制作简单。一般人群均能食用。 '),(3,1,'2019-02-20 00:00:00','2019-12-01 00:00:00',1,'酸菜鱼','https://sell-resources.oss-cn-beijing.aliyuncs.com/1/food_3.jpg',197,30,'2019-02-19 00:00:00','2019-03-23 22:15:15','酸菜鱼也称为酸汤鱼，是一道源自重庆的经典菜品，以其特有的调味和独特的烹调技法而著称。流行于上世纪90年代，是重庆江湖菜的开路先锋之一。酸菜鱼以草鱼为主料，配以泡菜等食材。'),(4,1,'2019-03-01 01:01:00','2019-12-01 00:00:00',1,'麻婆豆腐','https://sell-resources.oss-cn-beijing.aliyuncs.com/1/food_4.jpg',99,13,'2019-03-22 11:47:36','2019-03-27 09:14:35','麻婆豆腐'),(5,2,'2019-02-20 00:00:00','2019-12-01 00:00:00',1,'川香鸡柳饭','https://sell-resources.oss-cn-beijing.aliyuncs.com/2/food_1.jpg',98,33,'2019-02-19 00:00:00','2019-03-25 14:43:26','正宗川香风味，独特的甜香及辣香.表面富有光泽。'),(6,2,'2019-02-20 00:00:00','2019-12-01 00:00:00',1,'梅菜扣肉饭','https://sell-resources.oss-cn-beijing.aliyuncs.com/2/food_2.jpg',200,31,'2019-02-19 00:00:00','2019-02-19 00:00:00','汉族传统名菜，属客家菜。制作材料有五花肉、梅菜、葱白、姜片等。'),(7,3,'2019-02-20 00:00:00','2019-12-01 00:00:00',1,'藤椒鱼','https://sell-resources.oss-cn-beijing.aliyuncs.com/3/food_1.jpg',48,38,'2019-02-19 00:00:00','2019-03-25 23:41:35','藤椒鱼，是以鱼肉和鸡蛋为主料加上藤椒特有味道和香气烹调的一道的美味菜品。成品藤椒鱼口味麻辣微酸，鱼肉鲜香嫩滑，鱼肉富含的蛋白质为人体提供丰富的营养物质。'),(8,3,'2019-02-20 00:00:00','2019-12-01 00:00:00',1,'水煮肉片','https://sell-resources.oss-cn-beijing.aliyuncs.com/3/food_2.jpg',100,34,'2019-02-19 00:00:00','2019-02-19 00:00:00','水煮肉片肉味香辣，软嫩，易嚼。吃时肉嫩菜鲜 ，汤红油亮，麻辣味浓，最宜下饭，为家常美食之一。特色是“麻、辣、鲜、香”。'),(9,4,'2019-02-20 00:00:00','2019-05-01 00:00:00',1,'五花肉泡菜石锅拌饭','https://sell-resources.oss-cn-beijing.aliyuncs.com/4/food_1.jpg',200,31,'2019-02-19 00:00:00','2019-03-20 00:00:00','泡菜石锅拌饭是由米饭为主要食材做成的一道美食。'),(10,4,'2019-02-20 00:00:00','2019-05-01 00:00:00',1,'香肠石锅拌饭','https://sell-resources.oss-cn-beijing.aliyuncs.com/4/food_2.jpg',150,29,'2019-02-19 00:00:00','2019-03-19 00:00:00','石锅拌饭营养丰富且热量不高。尤其能吃到各种蔬菜，这正符合在全世界刮起的吃蔬菜和减肥的潮流。具有很好的营养价值。'),(11,5,'2019-02-20 00:00:00','2019-05-01 00:00:00',1,'全家福热干面','https://sell-resources.oss-cn-beijing.aliyuncs.com/5/food_1.jpg',17,38,'2019-02-19 00:00:00','2019-03-19 00:00:00',NULL),(12,5,'2019-02-20 00:00:00','2019-05-01 00:00:00',1,'原味热干面','https://sell-resources.oss-cn-beijing.aliyuncs.com/5/food_2.jpg',20,9.9,'2019-02-19 00:00:00','2019-03-18 00:00:00','热干面是中国十大面条之一，是湖北武汉最出名的小吃之一，有多种做法。通常以油、盐、芝麻酱、色拉油、香油、细香葱、大蒜子、辣萝卜丁，量卤水汁、生抽为辅助材料。其色泽黄而油润，味道鲜美，由于热量高，也可以当作主食，营养早餐，补充人体所需的能量。'),(13,6,'2019-02-20 00:00:00','2019-05-01 00:00:00',1,'奶茶三兄弟/大杯','https://sell-resources.oss-cn-beijing.aliyuncs.com/6/food_1.jpg',199,13,'2019-02-19 00:00:00','2019-03-25 23:32:25',NULL),(14,6,'2019-02-20 00:00:00','2019-05-01 00:00:00',1,'红豆奶茶/中杯','https://sell-resources.oss-cn-beijing.aliyuncs.com/6/food_2.jpg',200,11,'2019-02-19 00:00:00','2019-03-21 00:00:00',NULL),(42,1,'2019-01-01 00:00:00','2019-05-01 00:00:00',1,'差价','',9996,1,'2019-03-22 10:47:43','2019-03-27 08:32:14','用来补差价'),(43,1,'2019-03-01 01:01:00','2019-05-01 00:00:00',0,'1','',1,1,'2019-03-22 11:47:36','2019-03-22 11:47:50','1'),(98,97,'2019-03-01 09:00:00','2019-03-31 00:00:00',1,'青菜','',99,3,'2019-03-27 08:37:29','2019-03-27 08:37:52','青菜'),(107,106,'2019-03-01 00:00:00','2019-03-31 00:00:00',1,'青菜','',99,3,'2019-03-27 09:11:36','2019-03-27 09:12:49','青菜');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodorder`
--

DROP TABLE IF EXISTS `foodorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `foodorder` (
  `id` bigint(20) NOT NULL,
  `address_id` bigint(20) NOT NULL COMMENT '地址',
  `receiver` varchar(40) DEFAULT NULL COMMENT '单纯的收货人姓名',
  `phonenumber` varchar(20) DEFAULT NULL,
  `user_message` varchar(40) DEFAULT NULL COMMENT '备注',
  `pay_date` datetime DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `confirm_date` datetime DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `mem_id` bigint(20) NOT NULL,
  `res_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `need_time` double DEFAULT NULL COMMENT '秒钟',
  `total_price` double DEFAULT NULL COMMENT '最终付款金额',
  PRIMARY KEY (`id`),
  KEY `foodorder_address_id_fk` (`address_id`),
  KEY `foodorder_member_id_fk` (`mem_id`),
  KEY `foodorder_restaurant_id_fk` (`res_id`),
  CONSTRAINT `foodorder_address_id_fk` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `foodorder_member_id_fk` FOREIGN KEY (`mem_id`) REFERENCES `member` (`id`),
  CONSTRAINT `foodorder_restaurant_id_fk` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单，包含多个订单项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodorder`
--

LOCK TABLES `foodorder` WRITE;
/*!40000 ALTER TABLE `foodorder` DISABLE KEYS */;
INSERT INTO `foodorder` VALUES (1,1,'zzh','188****0963','无','2019-02-20 00:00:00','2019-02-20 00:00:00','2019-02-20 00:00:00',4,1,1,'2019-02-20 00:00:00','2019-03-25 23:28:22',1000,NULL),(2,1,'zzz','13312341234','无','2019-03-24 10:52:00',NULL,NULL,4,1,1,'2019-02-20 00:00:00','2019-03-25 23:28:15',1000,NULL),(60,1,'zzz','13312341234',NULL,'2019-03-25 23:00:24',NULL,NULL,4,1,2,'2019-03-25 14:43:25','2019-03-25 23:27:46',228103,NULL),(62,1,'zzz','13312341234',NULL,'2019-03-25 14:54:51',NULL,NULL,4,1,3,'2019-03-25 14:51:12','2019-03-25 23:26:22',1000,NULL),(66,1,'zzz','13312341234',NULL,'2019-03-25 23:29:45',NULL,NULL,4,1,1,'2019-03-25 23:29:21','2019-03-25 23:31:47',50751,NULL),(70,1,'zzz','13312341234',NULL,'2019-03-25 23:56:21',NULL,NULL,3,1,6,'2019-03-25 23:32:25','2019-03-25 23:56:22',84327,NULL),(72,1,'zzz','13312341234',NULL,NULL,NULL,NULL,2,1,3,'2019-03-25 23:41:35','2019-03-25 23:41:42',0,NULL),(76,1,'zzz','13312341234',NULL,'2019-03-26 16:12:30',NULL,'2019-03-26 16:13:09',4,1,1,'2019-03-26 15:52:05','2019-03-26 16:13:09',14428,24),(108,1,'zzz','13312341234',NULL,NULL,NULL,NULL,2,1,106,'2019-03-27 09:12:49','2019-03-27 09:13:12',0,3),(110,1,'zzz','13312341234',NULL,'2019-03-27 09:14:08',NULL,NULL,3,1,1,'2019-03-27 09:13:56','2019-03-27 09:14:08',14428,14),(113,1,'zzz','13312341234',NULL,'2019-03-27 09:14:54',NULL,'2019-03-27 09:15:24',4,1,1,'2019-03-27 09:14:32','2019-03-27 09:15:24',14428,21.5),(118,1,NULL,NULL,NULL,NULL,NULL,NULL,1,1,1,'2019-05-21 13:32:50','2019-05-21 13:32:51',0,NULL);
/*!40000 ALTER TABLE `foodorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foodorderitem`
--

DROP TABLE IF EXISTS `foodorderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `foodorderitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL COMMENT '数量',
  `price` double NOT NULL COMMENT '单价',
  `food_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `mem_id` bigint(20) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `foodorderitem_food_id_fk` (`food_id`),
  KEY `foodorderitem_foodorder_id_fk` (`order_id`),
  KEY `foodorderitem_member_id_fk` (`mem_id`),
  CONSTRAINT `foodorderitem_food_id_fk` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `foodorderitem_foodorder_id_fk` FOREIGN KEY (`order_id`) REFERENCES `foodorder` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `foodorderitem_member_id_fk` FOREIGN KEY (`mem_id`) REFERENCES `member` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8 COMMENT='订单单项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foodorderitem`
--

LOCK TABLES `foodorderitem` WRITE;
/*!40000 ALTER TABLE `foodorderitem` DISABLE KEYS */;
INSERT INTO `foodorderitem` VALUES (1,3,12,1,1,1,'2019-02-20 00:00:00','2019-03-12 08:35:10'),(45,2,30,3,2,1,'2019-03-23 14:36:59','2019-03-23 22:15:15'),(49,2,1,1,2,1,'2019-03-23 15:16:19','2019-03-23 15:16:19'),(50,1,33,5,2,1,'2019-03-23 22:18:24','2019-03-23 22:18:24'),(61,1,33,5,60,1,'2019-03-25 14:43:25','2019-03-25 14:43:25'),(63,1,38,7,62,1,'2019-03-25 14:51:12','2019-03-25 14:51:12'),(67,1,1,42,66,1,'2019-03-25 23:29:21','2019-03-25 23:29:21'),(68,1,17,2,66,1,'2019-03-25 23:29:26','2019-03-25 23:29:26'),(71,1,13,13,70,1,'2019-03-25 23:32:25','2019-03-25 23:32:25'),(73,1,38,7,72,1,'2019-03-25 23:41:35','2019-03-25 23:41:35'),(77,2,17,2,76,1,'2019-03-26 15:52:05','2019-03-26 15:52:05'),(109,1,3,107,108,1,'2019-03-27 09:12:49','2019-03-27 09:12:49'),(111,1,17,2,110,1,'2019-03-27 09:13:56','2019-03-27 09:13:56'),(114,1,1.5,1,113,1,'2019-03-27 09:14:32','2019-03-27 09:14:32'),(115,1,13,4,113,1,'2019-03-27 09:14:35','2019-03-27 09:14:35'),(116,1,17,2,113,1,'2019-03-27 09:14:38','2019-03-27 09:14:38');
/*!40000 ALTER TABLE `foodorderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (120),(120),(120),(120),(120),(120),(120),(120),(120),(120);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `level` (
  `name` varchar(20) NOT NULL,
  `upper_limit` int(11) NOT NULL,
  `lower_limit` int(11) NOT NULL,
  `discount` double NOT NULL COMMENT '不同积分的用户可获得的折扣不同\nxx折',
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='等级规则';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES ('王者',10000,1000,7,'https://sell-resources.oss-cn-beijing.aliyuncs.com/level/level_5.png'),('白银',200,200,9,'https://sell-resources.oss-cn-beijing.aliyuncs.com/level/level_2.png'),('钻石',750,750,8,'https://sell-resources.oss-cn-beijing.aliyuncs.com/level/level_4.png'),('青铜',100,0,10,'https://sell-resources.oss-cn-beijing.aliyuncs.com/level/level_1.png'),('黄金',500,500,8.5,'https://sell-resources.oss-cn-beijing.aliyuncs.com/level/level_3.png');
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='总经理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'ma','1234',NULL,'2019-02-19 00:00:00','2019-02-19 00:00:00'),(2,'mb','1234',NULL,'2019-02-19 00:00:00','2019-02-19 00:00:00');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) NOT NULL,
  `name` varchar(40) DEFAULT '',
  `phone_number` varchar(20) DEFAULT NULL,
  `preferred_address` varchar(40) DEFAULT NULL,
  `password` varchar(40) NOT NULL,
  `level_point` int(11) NOT NULL DEFAULT '0',
  `account_id` bigint(20) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态，0为未激活，1为激活，2为注销',
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `validate_code` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_email_uindex` (`email`),
  KEY `member_account_id_fk` (`account_id`),
  CONSTRAINT `member_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8 COMMENT='会员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'1@163.com','songzi12','188****0963','地址1','1234',20,3,'https://sell-resources.oss-cn-beijing.aliyuncs.com/member/1.jpg',1,'2019-02-19 00:00:00','2019-03-26 13:57:25',NULL),(2,'2@163.com','b','188****0964','地址5','1234',200,4,'https://sell-resources.oss-cn-beijing.aliyuncs.com/member/2.jpg',1,'2019-02-19 00:00:00','2019-02-19 00:00:00',NULL),(3,'3@163.com','c','138****0963','地址6','1234',150,5,'https://sell-resources.oss-cn-beijing.aliyuncs.com/member/1.jpg',1,'2019-02-19 00:00:00','2019-02-19 00:00:00',NULL),(4,'4@163.com','d','188****1111','地址7','1234',300,6,'https://sell-resources.oss-cn-beijing.aliyuncs.com/member/2.jpg',1,'2019-02-19 00:00:00','2019-02-19 00:00:00',NULL),(103,'m18851830963@163.com','member1',NULL,'汉口路22号 ok','admin1234',0,102,NULL,1,'2019-03-27 09:08:23','2019-03-27 09:09:54',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `restaurant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(40) NOT NULL,
  `type` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `account_id` bigint(20) NOT NULL,
  `coordinate` varchar(20) DEFAULT NULL,
  `codenumber` varchar(7) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `name` varchar(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `local_address` varchar(255) NOT NULL COMMENT '本地详细地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `restaurant_code_uindex` (`codenumber`),
  KEY `restaurant_account_id_fk` (`account_id`),
  KEY `restaurant_category_id_fk` (`category_id`),
  CONSTRAINT `restaurant_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `restaurant_category_id_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='餐厅';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (1,'1234',1,'https://sell-resources.oss-cn-beijing.aliyuncs.com/1/1.jpg',1,'118.45,32.30','AJEOj12','2019-02-19 00:00:00','2019-03-26 14:07:14','阿杜小厨',1,'雨花台区城新湖大道8号金地自在城二街期12幢102室'),(2,'1234',2,'https://sell-resources.oss-cn-beijing.aliyuncs.com/2/1.jpg',2,'118.95,32.20','dfASD12','2019-02-19 00:00:00','2019-02-19 00:00:00','阿姨便当',2,'南京市鼓楼区中央路141-1号104室'),(3,'1234',1,'https://sell-resources.oss-cn-beijing.aliyuncs.com/3/1.jpg',7,'118.95,32.20','12SDSas','2019-02-19 00:00:00','2019-02-19 00:00:00','川香阁',3,'南京市鼓楼区萨家湾56号-3号门面房'),(4,'1234',1,'https://sell-resources.oss-cn-beijing.aliyuncs.com/4/1.jpg',8,'118.95,32.38','12SDsad','2019-02-19 00:00:00','2019-02-19 00:00:00','韩式石锅拌饭',4,'南京市玄武区中央路282-2号'),(5,'1234',1,'https://sell-resources.oss-cn-beijing.aliyuncs.com/5/1.jpg',9,'118.95,31.30','asqw12t','2019-02-19 00:00:00','2019-02-19 00:00:00','正宗武汉热干面',5,'南京市鼓楼区车站东巷6号104室'),(6,'1234',1,'https://sell-resources.oss-cn-beijing.aliyuncs.com/6/1.jpg',10,'118.97,32.30','koekr14','2019-02-19 00:00:00','2019-02-19 00:00:00','CoCo都可',6,' 南京市鼓楼区马台街68号'),(40,'1234',1,NULL,39,'118.34,32.30','cdNjKTw','2019-03-20 17:40:06','2019-03-20 17:40:06','南京大学',1,'仙林大道163号'),(97,'admin1234',1,NULL,96,'118.776143,32.053765','d6HOgGo','2019-03-27 08:34:55','2019-03-27 08:34:55','南京大学1',1,'南京大学'),(106,'admin1234',1,NULL,105,'118.9509,32.12451','psfeENc','2019-03-27 09:10:43','2019-03-27 09:10:43','restaurant1',1,'汉口路22号');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategy_1`
--

DROP TABLE IF EXISTS `strategy_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `strategy_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `res_id` bigint(20) NOT NULL,
  `full` double DEFAULT NULL COMMENT '超过多少钱',
  `discount` double DEFAULT NULL COMMENT '减去多少钱',
  PRIMARY KEY (`id`),
  KEY `strategy_restaurant_id_fk` (`res_id`),
  CONSTRAINT `strategy_restaurant_id_fk` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 COMMENT='满减策略';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategy_1`
--

LOCK TABLES `strategy_1` WRITE;
/*!40000 ALTER TABLE `strategy_1` DISABLE KEYS */;
INSERT INTO `strategy_1` VALUES (1,'2019-03-25 11:09:00','2019-03-26 15:11:00',1,25,10),(3,'2019-03-25 11:09:00','2019-03-25 11:09:00',2,20,8),(75,'2019-03-26 15:34:10','2019-03-26 15:34:10',1,10,3);
/*!40000 ALTER TABLE `strategy_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategy_2`
--

DROP TABLE IF EXISTS `strategy_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `strategy_2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `food_ids` varchar(255) NOT NULL COMMENT '用,分隔',
  `res_id` bigint(20) NOT NULL,
  `discount_price` double NOT NULL COMMENT '现价格',
  `prime_price` double DEFAULT NULL COMMENT '原价格',
  PRIMARY KEY (`id`),
  KEY `strategy_2_restaurant_id_fk` (`res_id`),
  CONSTRAINT `strategy_2_restaurant_id_fk` FOREIGN KEY (`res_id`) REFERENCES `restaurant` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='套餐策略';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategy_2`
--

LOCK TABLES `strategy_2` WRITE;
/*!40000 ALTER TABLE `strategy_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `strategy_2` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-21 13:36:40
