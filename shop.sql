CREATE DATABASE  IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shop`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `commodity`
--

DROP TABLE IF EXISTS `commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity` (
  `id` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(80) COLLATE utf8mb4_bin NOT NULL,
  `category` varchar(40) COLLATE utf8mb4_bin NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `detail` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `spec` varchar(40) COLLATE utf8mb4_bin NOT NULL,
  `image` varchar(40) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity`
--

LOCK TABLES `commodity` WRITE;
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
INSERT INTO `commodity` VALUES ('20180625001','鉛筆盒','配件',70,26,'精美的鉛筆盒，買到賺到哦!!!','黑','1529993011308.jpg'),('20180626001','禹丞的雙下巴','配件',50,1,'全球限量，僅此一件。偽裝肥宅必備武器，錯過了就買不到囉!! 快叫把拔買給你','一組兩件','1529993983176.jpg'),('20180626002','天線寶寶','配件',40,4,'一隻40，整組帶走算你200','紅綠黃紫','1529994751564.jpg'),('20180704001','開口透氣襪','鞋款',100,8,'最新科技，在一般襪子前面開洞，讓您擺脫悶熱潮濕的臭腳丫~','黑色 一雙','1530682527217.jpg'),('20180704002','USB電風扇','配件',200,3,'打電腦很熱嗎? 隨插即用USB電風扇是你最佳的好夥伴','綠色','1530682846491.jpg'),('20180704003','iphone不知道幾','配件',20000,1,'二手哀鳳，功能完好。','象牙白','1530683336883.jpg'),('20180704004','五月花衛生紙','配件',5,11,'只有四分之一只有四分之一只有四分之一\r\n因為很重要所以說三次','純白色','1530683417434.jpg'),('20180704005','雲彩色外套','配件',123,5,'適合搭配雙下巴使用，雙管齊下保證變成職訓局潮男!!','很多雲','1530683583662.jpg'),('20180704006','adidas側背包','背包',500,99,'你有看到他的光澤嗎?? 心動就趕快下手吧\r\n註: 實物不包含那一隻腳掌','黑色','1530683664346.jpg'),('20180704007','COACH女用包','背包',10000,1,'時尚奢華，高貴不貴。','黑色','1530683718105.jpg'),('20180704008','免洗帽','帽子',10,20,'四個一組免洗帽，使用前請自行修剪成滿意的樣式。','黑色','1530683769912.jpg'),('20180704009','側背包','背包',250,32,'歷經滄桑的背包，哥買的不是包包，是千錘百鍊的故事。','白','1530683847063.jpg'),('20180704010','多種鞋款','鞋款',500,7,'均一價，一雙五百，任君挑選，訂單請註明要哪一隻。','多種顏色','1530683933432.jpg'),('20180704011','環保背包','背包',10,40,'陪伴著JAVA班多時的好夥伴，不管在晴天雨天總是承載著JAVA班學員的燃料，遠道而來。','白色','1530684011602.jpg'),('20180704012','康青龍格雷綜合水果','配件',50,20,'拿在手上，讓眾人只能眼巴巴的看著裡面的水果切片，感受康青龍妹子的無情。','透明','1530684159138.jpg'),('20180704013','多風格帽子','帽子',1,333,'沒錯他是帽子。','綠色帶斑點','1530684228855.jpg'),('20180704014','這次比較多','配件',20,1,'這次有半包','半包','1530684279007.jpg'),('20180704015','白色小藥丸','配件',20,2,'覺得昏昏欲睡嗎? 來一顆\r\n覺得事情太多嗎? 來一顆\r\n覺得餐廳老闆很機車嗎? 來一顆\r\n覺得康青龍不好喝嗎? 來一顆\r\n一顆下去解決你所有的煩惱','白色顆粒','1530684373239.jpg'),('20180704016','班代御用水壺','配件',10000,1,'全世界只有一款哦，班代用過的水壺\r\n充滿著粉紅色少女的氣息\r\n如果你曾經因為喝康青龍的少女心而感到愉悅\r\n那你一定不能錯過這款，少女心水壺','pink','1530684481909.jpg'),('20180704017','Hㄒㄈ手機','配件',5000,1,'最新科技，買的時候是平面螢幕\r\n過幾年就變成曲面螢幕\r\n很適合正在猶豫要買哪種螢幕的您\r\n兩種享受一次滿足','黑色(目前是平面)','1530684573046.jpg'),('20180704018','結婚戒指','配件',100000000,1,'快來買走他!!! 買了他也等於買走了姊姊的心\r\n\r\n註:商品內容不含手指及兩條腿','白金','1530684648143.jpg');
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marquee`
--

DROP TABLE IF EXISTS `marquee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marquee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(200) DEFAULT NULL,
  `selected` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marquee`
--

LOCK TABLES `marquee` WRITE;
/*!40000 ALTER TABLE `marquee` DISABLE KEYS */;
INSERT INTO `marquee` VALUES (11,'慶祝195找到好工作，全館加長尺碼全部195!!!',1),(12,' ',0),(13,'0.0',0);
/*!40000 ALTER TABLE `marquee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `addr` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `tel` varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `privilege` int(11) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'root','123456','12007','internet','12007','rootroot@12007',31),(2,'test','654321','Henry','台北101101樓','0911333999','rrr@yahoo.mail.com',0),(3,'POHAN','LUPOHAN','POHAN','POHAN','1111111','POHAN@11111',8),(4,'JAVA107','JAVA107','JAVA107','JAVA107','JAVA107','JAVA107@JAVA10111',8),(5,'aaaa','111111','1','1','1','1@1',0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderext`
--

DROP TABLE IF EXISTS `orderext`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderext` (
  `id` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  `commodity_id` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `buyquantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderext`
--

LOCK TABLES `orderext` WRITE;
/*!40000 ALTER TABLE `orderext` DISABLE KEYS */;
INSERT INTO `orderext` VALUES ('A201807020002','20180626001',50,1),('A201807020002','20180625001',70,2),('A201807020002','20180626002',40,1),('A201807040001','20180625001',70,1),('A201807040002','20180625001',70,1),('A201807040003','20180625001',70,2),('A201807040003','20180626001',50,1);
/*!40000 ALTER TABLE `orderext` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordermain`
--

DROP TABLE IF EXISTS `ordermain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordermain` (
  `id` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  `mem_id` int(11) DEFAULT NULL,
  `date` varchar(14) COLLATE utf8mb4_bin DEFAULT NULL,
  `receiver` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `addr` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `tel` varchar(13) COLLATE utf8mb4_bin DEFAULT NULL,
  `process` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `note` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordermain`
--

LOCK TABLES `ordermain` WRITE;
/*!40000 ALTER TABLE `ordermain` DISABLE KEYS */;
INSERT INTO `ordermain` VALUES ('A201807020002',1,'20180702','淋雨陳','自強樓406','0977878787','出貨準備中','45454546'),('A201807040001',4,'20180704','JAVA107','JAVA107','JAVA107','出貨準備中','JAVA107'),('A201807040002',4,'20180704','JAVA107','JAVA107','JAVA107','出貨準備中','JAVA107JAVA107'),('A201807040003',5,'20180704','1','1','1','已出貨','1');
/*!40000 ALTER TABLE `ordermain` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-04 14:11:24
