-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 47.101.183.63    Database: cinema
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

SET @@session.sql_mode ='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(45) NOT NULL,
  `a_description` varchar(255) NOT NULL,
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `coupon_id` int(11) DEFAULT NULL,
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (2,'春季外卖节','春季外卖节','2019-04-23 17:55:59',5,'2019-04-20 17:55:59'),(3,'春季外卖节','春季外卖节','2019-04-23 17:55:59',6,'2019-04-20 17:55:59'),(4,'测试活动','测试活动','2019-06-26 16:00:00',8,'2019-04-20 16:00:00');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_movie`
--

DROP TABLE IF EXISTS `activity_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_movie` (
  `activity_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_movie`
--

LOCK TABLES `activity_movie` WRITE;
/*!40000 ALTER TABLE `activity_movie` DISABLE KEYS */;
INSERT INTO `activity_movie` VALUES (2,10),(2,11),(2,16),(4,10);
/*!40000 ALTER TABLE `activity_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `target_amount` float DEFAULT NULL,
  `discount_amount` float DEFAULT NULL,
  `start_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon`
--

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;
INSERT INTO `coupon` VALUES (1,'测试优惠券','春季电影节',20,5,'2019-04-20 17:47:54','2019-04-23 17:47:59'),(5,'测试优惠券','品质联盟',30,4,'2019-04-20 21:14:46','2019-04-24 21:14:51'),(6,'春节电影节优惠券','电影节优惠券',50,10,'2019-04-20 21:15:11','2019-04-21 21:14:56'),(8,'测试优惠券','123',100,99,'2019-04-20 16:00:00','2019-06-26 16:00:00');
/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_user`
--

DROP TABLE IF EXISTS `coupon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon_user` (
  `coupon_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_user`
--

LOCK TABLES `coupon_user` WRITE;
/*!40000 ALTER TABLE `coupon_user` DISABLE KEYS */;
INSERT INTO `coupon_user` VALUES (8,15),(5,15),(8,15),(6,15),(5,15),(8,15),(6,15);
/*!40000 ALTER TABLE `coupon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall`
--

DROP TABLE IF EXISTS `hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `column` int(11) DEFAULT NULL,
  `row` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall`
--

LOCK TABLES `hall` WRITE;
/*!40000 ALTER TABLE `hall` DISABLE KEYS */;
INSERT INTO `hall` VALUES (1,'1号厅',10,5),(2,'2号厅',12,8);
/*!40000 ALTER TABLE `hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poster_url` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `screen_writer` varchar(255) DEFAULT NULL,
  `starring` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `length` int(11) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  `description` text,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (10,'http://n.sinaimg.cn/translate/640/w600h840/20190312/ampL-hufnxfm4278816.jpg','大森贵弘 /伊藤秀樹','','神谷浩史 /井上和彦 /高良健吾 /小林沙苗 /泽城美雪','动画','日本','日语',120,'2019-04-14 14:54:31','夏目友人帐','在人与妖怪之间过着忙碌日子的夏目，偶然与以前的同学结城重逢，由此回忆起了被妖怪缠身的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。和玲子相识的她，现在和独子椋雄一同过着平稳的生活。夏目通过与他们的交流，心境也变得平和。但这对母子居住的城镇，却似乎潜伏着神秘的妖怪。在调查此事归来后，寄生于猫咪老师身体的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。而吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个',1),(11,'https://gss3.bdstatic.com/7Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=113efc83dc09b3deffb2ec3aadd607e4/71cf3bc79f3df8dc9670d47ac011728b4610288f.jpg','安娜·波顿','吉内瓦·德沃莱特-罗宾森','布利·拉尔森','动作/冒险/科幻','美国','英语',120,'2019-04-16 14:55:31','惊奇队长','漫画中的初代惊奇女士曾经是一名美国空军均情报局探员，暗恋惊奇先生。。。',1),(12,'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike92%2C5%2C5%2C92%2C30/sign=da0ddb2da3345982d187edc06d9d5ac8/7c1ed21b0ef41bd5e0bea7ad5fda81cb38db3dfd.jpg','西蒙·金伯格','西蒙·金伯格','詹姆斯·麦卡沃伊、詹妮弗·劳伦斯、迈克尔·法斯宾德、索菲·特纳、尼古拉斯·霍尔特、杰西卡·查斯坦','科幻/动作','美国','英语',114,'2019-06-06 00:00:00','X战警：黑凤凰','该片的背景设置在20世纪八九十年代，故事将聚焦凤凰女琴·葛蕾的内在人格被释放后黑化为暗黑凤凰，并与X战警开战的故事。','0'),(13,'https://gss3.bdstatic.com/-Po3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=2e410882e91190ef15f69a8daf72f673/574e9258d109b3de37f63c07c2bf6c81800a4c10.jpg','盖·里奇','约翰·奥古斯特','威尔·史密斯、梅纳·马苏德、娜奥米·斯科特','爱情/奇幻/冒险','美国','英语',128,'2019-05-24 00:00:00','阿拉丁','该片根据1992年的同名动画片改编，讲述了善良的穷小子阿拉丁和勇敢的茉莉公主浪漫邂逅，在可以满足主人三个愿望的神灯精灵帮助下，共同踏上寻找真爱和自我的魔幻冒险故事。',0),(14,'https://gss1.bdstatic.com/9vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=f876aa402f3fb80e18dc698557b8444b/2cf5e0fe9925bc31a5ffa7e950df8db1cb137025.jpg','安东尼·罗素，乔·罗素','克里斯托弗·马库斯、斯蒂芬·麦克菲利、斯坦·李、杰克·科比','小罗伯特·唐尼、克里斯·埃文斯、克里斯·海姆斯沃斯、马克·鲁法洛、斯嘉丽·约翰逊、杰瑞米·雷纳、保罗·路德、布丽·拉尔森、唐·钱德尔、凯伦·吉兰、乔什·布洛林','动作/科幻','美国','英语',181,'2019-04-24 00:00:00','复仇者联盟4','故事发生在《复仇者联盟3：无限战争》之后，灭霸使用无限手套的力量，造成全宇宙一半的生命随机消失，有的人永远失去了挚爱和家人，复仇者联盟部分成员也因此消失了。在泰坦星上和灭霸结束了战斗的钢铁侠托尼和星云，在返回地球的途中遭遇了缺水和没有食物的境地，幸得惊奇队长出手相救。回到地球的惊奇队长与其他英雄们，决定前去打倒灭霸并寻回宝石，却发现灭霸已经将宝石毁掉了。愤怒的雷神砍下了灭霸的脑袋，复仇者们从此不再勇猛如初。五年过去了，穿越到量子领域逃过一劫的斯科特·朗（蚁人）来到了复仇者总部，并告知众人，他们仍然有机会可以逆转灭霸的所作所为。',1),(15,'https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=3aca58b5c695d143ce7bec711299e967/4b90f603738da977417f329dbe51f8198618e330.jpg','章笛沙','刘婉荟','陈飞宇，何蓝逗','青春/校园','中国','中文',110,'2019-06-06 00:00:00','最好的我们','那是记忆里最好的时光，“学渣”耿耿和“学霸”余淮成了同桌，还结识了简单（王初伊 饰）、贝塔（周楚濋 饰）、徐延亮（陈帅 饰）。校园里充盈着专属少男少女们的懵懂、青涩、怦然心动和勇敢，耿耿余淮也拥有了他们的约定。高考后，当耿耿满怀期待憧憬约定兑现之时，余淮却忽然消失不见了。七年后两人重逢，余淮当年未说出口的那句话、他不辞而别的秘密，耿耿能否得到解答？这段耿耿于怀的过往，让两人再度面临情感的抉择',0),(16,'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2549523952.webp','林孝谦','吕安弦、林孝謙','陈意涵','爱情','大陆','中文',123,'2019-04-18 13:23:15','比悲伤更悲伤的故事','唱片制作人张哲凯(刘以豪)和王牌作词人宋媛媛(陈意涵)相依为命，两人自幼身世坎坷只有彼此为伴，他们是亲人、是朋友，也彷佛是命中注定的另一半。父亲罹患遗传重症而被母亲抛弃的哲凯，深怕自己随时会发病不久人世，始终没有跨出友谊的界线对媛媛展露爱意。眼见哲凯的病情加重，他暗自决定用剩余的生命完成他们之间的终曲，再为媛媛找个可以托付一生的好男人。这时，事业有 成温柔体贴的医生(张书豪)适时的出现让他成为照顾媛媛的最佳人选，二人按部就班发展着关系。一切看似都在哲凯的计划下进行。然而，故事远比这里所写更要悲伤......',1),(17,'https://gss1.bdstatic.com/-vo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=c3ef07b6f3faaf5190ee89eded3dff8b/a9d3fd1f4134970a3dbabfbf9bcad1c8a6865d50.jpg','迈克尔·道赫蒂','迈克尔·道赫蒂、麦克思·鲍伦斯坦、扎克·希尔兹','凯尔·钱德勒，维拉·法梅加，米莉·博比·布朗，渡边谦，章子怡，布莱德利·惠特福德，莎莉·霍金斯','动作/科幻/冒险','美国','英语',132,'2019-05-31 00:00:00','哥斯拉2：怪兽之王','电影将延续上一部的故事，动物基因组学（帝王计划）研究机构将会有更繁重的研究任务，因为一大堆大小不一的怪物要登场，而哥斯拉也要与摩斯拉、拉顿以及它的强敌基多拉相遇。这些古老的超级怪兽将一一登场，此前他们一直被认为只存在于神话当中，这一次他们将为争夺“怪兽之王”而战，人类的生存也受到严重威胁。',0),(18,'https://gss0.bdstatic.com/94o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike116%2C5%2C5%2C116%2C38/sign=db4638309c45d688b70fbaf6c5ab167b/0e2442a7d933c895c9f86978df1373f08202002a.jpg','乔什·库雷','约翰·拉塞特、安德鲁·斯坦顿 、彼特·道格特、李·昂克里奇、Stephany Folsom','汤姆·汉克斯（配音），蒂姆·艾伦（配音）','动画/爱情/冒险/喜剧/家庭','美国','英语',95,'2019-06-21 00:00:00','玩具总动员4','《玩具总动员4》的故事将集中在胡迪与牧羊女中间的爱情上面，会是一部爱情喜剧。',0),(19,'https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=ea735c7f3fadcbef15397654cdc645b8/f11f3a292df5e0fea3f4ecba526034a85fdf7298.jpg','八锹新之介','辻村深月','水田山葵，大原惠美，嘉数由美，木村昴，关智一，皆川纯子','动画电影（奇幻，冒险）','日本、中国大陆','日语',111,'2019-06-01 00:00:00','哆啦A梦：大雄的月球探险记','影片以“异说俱乐部徽章”为蓝本，讲述了一个神秘的转学生月野露卡与大雄他们一起前往月兔王国经历重重冒险的故事。',0),(20,'https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=162a646b04d162d991e36a4e70b6c289/9922720e0cf3d7ca6fe915d9fc1fbe096b63a905.jpg','乔·沃茨',NULL,'汤姆·赫兰德，赞达亚·科尔曼','动作/冒险/喜剧/科幻','美国','英语',130,'2019-06-28 00:00:00','蜘蛛侠：英雄远征','故事承接《复仇者联盟4：终局之战》，此次蜘蛛侠将前往欧洲展开新的征程，并将对抗由杰克:吉伦哈尔加盟饰演的神秘客。赞达亚、雅各布巴特朗、托尼雷沃罗利等原班人马也将悉数回归。本片将于2019年6月28日在中国大陆上映，经历了第一部的成长经历后，小蜘蛛在失去钢铁侠之后又会面临怎样的危机?敬请期待。',0);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_like`
--

DROP TABLE IF EXISTS `movie_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_like` (
  `movie_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `like_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`movie_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_like`
--

LOCK TABLES `movie_like` WRITE;
/*!40000 ALTER TABLE `movie_like` DISABLE KEYS */;
INSERT INTO `movie_like` VALUES (10,12,'2019-03-25 02:40:19'),(11,1,'2019-03-22 09:38:12'),(11,2,'2019-03-23 09:38:12'),(11,3,'2019-03-22 08:38:12'),(12,1,'2019-03-23 09:48:46'),(12,3,'2019-03-25 06:36:22'),(14,1,'2019-03-23 09:38:12'),(16,12,'2019-03-23 15:27:48');
/*!40000 ALTER TABLE `movie_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hall_id` int(11) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `start_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NOT NULL,
  `fare` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (20,1,12,'2019-04-13 17:00:00','2019-04-13 18:00:00',20.5),(21,1,10,'2019-04-11 12:00:00','2019-04-11 13:00:00',90),(27,1,11,'2019-04-17 18:01:00','2019-04-17 20:01:00',20.5),(28,1,11,'2019-04-19 16:00:00','2019-04-19 18:00:00',20.5),(30,1,11,'2019-04-18 18:01:00','2019-04-18 20:01:00',20.5),(31,1,11,'2019-04-12 16:00:00','2019-04-12 18:00:00',20.5),(32,1,11,'2019-04-12 20:00:00','2019-04-12 22:00:00',20.5),(37,1,11,'2019-04-15 00:00:00','2019-04-15 02:00:00',20.5),(38,1,11,'2019-04-14 17:00:00','2019-04-14 19:00:00',20.5),(40,1,10,'2019-04-10 16:00:00','2019-04-10 18:00:00',20.5),(41,1,11,'2019-04-10 19:00:00','2019-04-10 21:00:00',20.5),(42,1,11,'2019-04-10 22:00:00','2019-04-11 00:00:00',20.5),(43,1,10,'2019-04-11 01:00:00','2019-04-11 03:00:00',20.5),(44,2,10,'2019-04-11 01:00:00','2019-04-11 03:00:00',20.5),(45,2,10,'2019-04-10 22:00:00','2019-04-11 00:00:00',20.5),(46,2,11,'2019-04-10 19:00:00','2019-04-10 21:00:00',20.5),(47,2,11,'2019-04-10 16:00:00','2019-04-10 18:00:00',20.5),(48,2,10,'2019-04-11 13:00:00','2019-04-11 15:59:00',20.5),(50,1,10,'2019-04-15 16:00:00','2019-04-15 19:00:00',2),(51,1,10,'2019-04-17 05:00:00','2019-04-17 07:00:00',9),(52,1,10,'2019-04-18 05:00:00','2019-04-18 07:00:00',9),(53,1,16,'2019-04-19 07:00:00','2019-04-19 10:00:00',9),(54,1,16,'2019-04-16 19:00:00','2019-04-16 22:00:00',9),(55,1,15,'2019-04-17 23:00:00','2019-04-18 01:00:00',9),(56,2,10,'2019-04-19 13:00:00','2019-04-19 15:59:00',20.5),(57,2,10,'2019-04-20 13:00:00','2019-04-20 15:59:00',20.5),(58,2,10,'2019-04-21 13:00:00','2019-04-21 15:59:00',20.5),(61,1,13,'2019-04-20 11:00:00','2019-04-20 13:00:00',25),(62,1,11,'2019-04-20 08:00:00','2019-04-20 10:00:00',25),(63,2,15,'2019-04-20 16:01:30','2019-04-21 05:30:00',30),(64,1,16,'2019-04-22 02:00:00','2019-04-22 05:30:00',30),(65,1,10,'2019-04-23 02:00:00','2019-04-23 05:30:00',30),(66,2,13,'2019-04-21 07:31:29','2019-04-16 15:59:00',20.5),(67,2,10,'2019-04-25 13:00:00','2019-04-25 15:59:00',20.5),(68,2,10,'2019-04-26 13:00:00','2019-04-26 15:59:00',20.5);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `user_id` int(11) DEFAULT NULL,
  `schedule_id` int(11) DEFAULT NULL,
  `column_index` int(11) DEFAULT NULL,
  `row_index` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*INSERT INTO `ticket` VALUES (12,50,5,3,2,1,'2019-04-23 13:50:52'),(12,50,5,3,2,2,'2019-04-23 13:50:52'),(12,50,5,3,2,3,'2019-04-23 13:50:52'),(12,50,5,3,2,4,'2019-04-23 13:50:52'),(12,50,5,3,0,5,'2019-04-23 13:50:52'),(15,50,4,3,0,6,'2019-04-23 13:50:52'),(15,58,0,0,1,15,'2019-04-23 13:50:52'),(15,58,2,0,1,16,'2019-04-23 13:50:52'),(15,58,1,1,1,17,'2019-04-23 13:50:52'),(15,58,11,7,1,18,'2019-04-23 13:50:52'),(13,50,4,2,1,19,'2019-04-23 13:50:52'),(15,66,3,2,1,20,'2019-04-23 13:50:52'),(12,50,1,1,1,21,'2019-04-23 13:50:52'),(13,50,4,3,1,22,'2019-04-23 13:50:52'),(15,50,2,2,1,23,'2019-04-23 13:50:52'),(15,58,0,7,0,24,'2019-04-23 13:50:52'),(15,58,5,4,0,25,'2019-04-23 13:50:52'),(15,58,6,4,0,26,'2019-04-23 13:50:52'),(15,58,6,2,0,27,'2019-04-23 13:50:52'),(15,58,7,2,0,28,'2019-04-23 13:50:52'),(15,58,0,4,0,29,'2019-04-23 13:50:52'),(15,58,0,3,0,30,'2019-04-23 13:50:52'),(15,58,0,2,0,31,'2019-04-23 13:50:52'),(15,58,10,0,0,32,'2019-04-23 13:50:52'),(15,58,11,0,0,33,'2019-04-23 13:50:52'),(15,58,8,0,0,34,'2019-04-23 13:50:52'),(15,58,9,0,0,35,'2019-04-23 13:50:52'),(15,58,5,0,0,36,'2019-04-23 13:50:52'),(15,58,6,0,0,37,'2019-04-23 13:50:52'),(15,58,6,7,0,38,'2019-04-23 13:50:52'),(15,58,7,7,0,39,'2019-04-23 13:50:52'),(15,58,8,7,0,40,'2019-04-23 13:50:52'),(15,58,11,4,0,41,'2019-04-23 13:50:52'),(15,58,11,5,0,42,'2019-04-23 13:50:52'),(15,58,9,6,0,43,'2019-04-23 13:50:52'),(15,58,10,6,0,44,'2019-04-23 13:50:52'),(15,58,11,6,0,45,'2019-04-23 13:50:52'),(15,58,3,5,1,46,'2019-04-23 13:50:52'),(15,58,4,5,1,47,'2019-04-23 13:50:52'),(15,58,5,5,1,48,'2019-04-23 13:50:52'),(15,58,11,2,0,49,'2019-04-23 13:50:52'),(15,58,11,3,0,50,'2019-04-23 13:50:52'),(15,58,9,4,0,51,'2019-04-23 13:50:52'),(15,58,9,3,1,52,'2019-04-23 13:50:52'),(15,58,10,3,1,53,'2019-04-23 13:50:52'),(15,65,7,4,0,54,'2019-04-23 13:50:52'),(15,65,8,4,0,55,'2019-04-23 13:50:52'),(15,65,9,4,0,56,'2019-04-23 13:50:52'),(15,65,7,3,0,57,'2019-04-23 13:50:52'),(15,65,8,3,0,58,'2019-04-23 13:50:52'),(15,65,9,3,0,59,'2019-04-23 13:50:52'),(15,65,0,0,1,60,'2019-04-23 13:50:52'),(15,65,0,1,1,61,'2019-04-23 13:50:52'),(15,65,0,2,1,62,'2019-04-23 13:50:52');*/
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'testname','123456','客户'),(3,'test','123456','客户'),(5,'test1','123456','客户'),(7,'test121','123456','客户'),(8,'root','123456','总经理'),(10,'roottt','123123','客户'),(12,'zhourui','123456','客户'),(13,'abc123','abc123','客户'),(15,'dd','123','客户');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `view`
--

DROP TABLE IF EXISTS `view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `view` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `view`
--

LOCK TABLES `view` WRITE;
/*!40000 ALTER TABLE `view` DISABLE KEYS */;
INSERT INTO `view` VALUES (1,7);
/*!40000 ALTER TABLE `view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vip_card`
--

DROP TABLE IF EXISTS `vip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vip_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `join_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `target_money` DOUBLE NOT NULL,
  `gift_money` DOUBLE NOT NULL,
  `price` DOUBLE NOT NULL,
  `total_amount`DOUBLE DEFAULT NULL,
  `last_order_date`timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vip_card_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vip_card`
--

LOCK TABLES `vip_card` WRITE;
/*!40000 ALTER TABLE `vip_card` DISABLE KEYS */;
/*INSERT INTO `vip_card` VALUES (1,15,375,'2019-04-21 13:54:38',200,30,25),(2,12,660,'2019-04-17 18:47:42',200,30,25);
/*!40000 ALTER TABLE `vip_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vipStrategy`
--
DROP TABLE IF EXISTS `vipStrategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vipStrategy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `price` DOUBLE NOT NULL,
  `target_money` DOUBLE NOT NULL ,
  `gift_money` DOUBLE NOT NULL ,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vipStrategy`
--

LOCK TABLES `vipStrategy` WRITE;
/*!40000 ALTER TABLE `vipStrategy` DISABLE KEYS */;
INSERT INTO `vipStrategy` VALUES (1,'hehe',30,200,30),(2,'xixi',10,500,100);
/*!40000 ALTER TABLE `vipStrategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--
DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `time`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state` tinyint(4) DEFAULT NULL,
  `used_coupon_id` int(11) DEFAULT NULL,
  `origin_money` DOUBLE NOT NULL,
  `real_money` DOUBLE NOT NULL,
  `schedule_time` timestamp NOT NULL,
  `seat` VARCHAR(255) DEFAULT NULL,
  `movie_name` VARCHAR(255) DEFAULT NULL,
  `poster_url` varchar(255) DEFAULT NULL,
  `vipcard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vipStrategy`
--

LOCK TABLES `order` WRITE ;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*INSERT INTO `order` VALUES (1,1,'2019-04-21 13:14:00',1,8,120,21,'2019-04-23 10:00:00','夏目友人帐','http://n.sinaimg.cn/translate/640/w600h840/20190312/ampL-hufnxfm4278816.jpg');*/
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_ticket`
--

DROP TABLE IF EXISTS `order_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_ticket`(
  `order_id` int(11) NOT NULL,
  `ticket_id` int(11) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_ticket`
--

LOCK TABLES `order_ticket` WRITE ;
/*!40000 ALTER TABLE `order_ticket` DISABLE KEYS */;
/*INSERT INTO `order_ticket` VALUES (1,1);*/
/*!40000 ALTER TABLE `order_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_giftCoupon`
--

DROP TABLE IF EXISTS `order_giftCoupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_giftCoupon`(
 `order_id` int(11) NOT NULL,
 `giftCoupon_id` int(11) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_giftCoupon`
--

LOCK TABLES `order_giftCoupon` WRITE ;
/*!40000 ALTER TABLE `order_giftCoupon` DISABLE KEYS */;
/*INSERT INTO `order_giftCoupon` VALUES (1,8);*/
/*!40000 ALTER TABLE `order_giftCoupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `recharge`
--

DROP TABLE IF EXISTS `recharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recharge`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `trade_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `recharge_money` double NOT NULL,
  `gift_money` double NOT NULL,
  `balance` double NOT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recharge`
--

LOCK TABLES `recharge` WRITE ;
/*!40000 ALTER TABLE `recharge` DISABLE KEYS */;
/*INSERT INTO `order_giftCoupon` VALUES (1,8);*/
/*!40000 ALTER TABLE `recharge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `refundstrategy`
--

DROP TABLE IF EXISTS `refundstrategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refundstrategy`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `refundstrategy_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `limitedTime` int(11) DEFAULT NULL,
  `bookingCharge` double DEFAULT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refundstrategy`
--

LOCK TABLES `refundstrategy` WRITE ;
/*!40000 ALTER TABLE `refundstrategy` DISABLE KEYS */;
/*!40000 ALTER TABLE `refundstrategy` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `refundstrategy_movieid`
--

DROP TABLE IF EXISTS `refundstrategy_movieid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refundstrategy_movieid`(
  `refundstrategy_id` int(11) DEFAULT NULL,
  `movie_id` int(11) DEFAULT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refundstrategy_movieid`
--

LOCK TABLES `refundstrategy_movieid` WRITE ;
/*!40000 ALTER TABLE `refundstrategy_movieid` DISABLE KEYS */;
/*INSERT INTO `order_giftCoupon` VALUES (1,8);*/
/*!40000 ALTER TABLE `refundstrategy_movieid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `gender` varchar(50) NOT NULL ,
  `nation` varchar(255) NOT NULL,
  `idnumber` varchar(255) NOT NULL ,
  `birth` TIMESTAMP NOT NULL,
  `age` int(11) NOT NULL ,
  `hiredate` TIMESTAMP NOT NULL,
  `position` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL ,
  `address` varchar(255) NOT NULL ,
  `username` varchar(255) NOT NULL,
  `password` varchar(55) NOT NULL,
  PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE ;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'cinema'
--

--
-- Dumping routines for database 'cinema'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-24 21:20:52