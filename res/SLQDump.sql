CREATE DATABASE  IF NOT EXISTS `DØKxb07` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `DØKxb07`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 54.172.101.85    Database: DØKxb07
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.14.04.1

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
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar` (
  `CalendarID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Active` tinyint(4) DEFAULT '1',
  `CreatedBy` varchar(255) NOT NULL,
  `PrivatePublic` tinyint(4) NOT NULL,
  `IsCBS` varchar(45) NOT NULL DEFAULT '0',
  PRIMARY KEY (`CalendarID`),
  UNIQUE KEY `Name_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES (54,'CBScalendar nibr13ae',0,'CBS',0,'1'),(55,'BINTO1056U.XB_E14',1,'CBS',0,'1'),(58,'BINTO1056U.LA_E14',1,'CBS',0,'1'),(62,'BINTO1051U.LA_E14',1,'CBS',0,'1'),(63,'BINTO1067U.LA_E14',1,'CBS',0,'1'),(64,'BINTO1035U.LA_E14',1,'CBS',0,'1'),(65,'BINTO1035U.XB_E14',1,'CBS',0,'1'),(66,'CBScalendar jolj',0,'CBS',0,'1'),(67,'JOhns SUPERAWESOME CALENDAR',0,'jolj',0,'0'),(68,'en ny kalender',1,'jolj',0,'0');
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar_users`
--

DROP TABLE IF EXISTS `calendar_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CalendarID` int(11) NOT NULL,
  `Email` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CalendarID_idx` (`CalendarID`),
  KEY `Email_idx` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar_users`
--

LOCK TABLES `calendar_users` WRITE;
/*!40000 ALTER TABLE `calendar_users` DISABLE KEYS */;
INSERT INTO `calendar_users` VALUES (48,55,'nibr13ae'),(49,55,'nibr13ae'),(50,58,'nibr13ae'),(51,58,'nibr13ae'),(52,58,'nibr13ae'),(53,62,'nibr13ae'),(54,63,'nibr13ae'),(55,64,'nibr13ae'),(56,65,'nibr13ae'),(57,55,'jolj'),(58,58,'jolj'),(59,62,'jolj'),(60,63,'jolj'),(61,67,'jolj'),(62,67,'nibr13ae'),(63,68,'jolj');
/*!40000 ALTER TABLE `calendar_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `courseID` varchar(50) NOT NULL,
  `calendarID` int(11) NOT NULL,
  PRIMARY KEY (`courseID`,`calendarID`),
  UNIQUE KEY `courseID_UNIQUE` (`courseID`),
  UNIQUE KEY `calendarID_UNIQUE` (`calendarID`),
  CONSTRAINT `calendarID` FOREIGN KEY (`calendarID`) REFERENCES `calendar` (`CalendarID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('BINTO1056U.XB_E14',55),('BINTO1056U.LA_E14',58),('BINTO1051U.LA_E14',62),('BINTO1067U.LA_E14',63),('BINTO1035U.LA_E14',64),('BINTO1035U.XB_E14',65);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailyupdate`
--

DROP TABLE IF EXISTS `dailyupdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailyupdate` (
  `Date` datetime NOT NULL,
  `apparentTemperature` double DEFAULT NULL,
  `summary` text,
  `qotd` tinytext,
  PRIMARY KEY (`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyupdate`
--

LOCK TABLES `dailyupdate` WRITE;
/*!40000 ALTER TABLE `dailyupdate` DISABLE KEYS */;
INSERT INTO `dailyupdate` VALUES ('2014-12-04 00:00:00',3.93,'broken clouds',NULL),('2014-12-04 11:00:00',3.01,'sky is clear','Bart Starr: \"Anyone can support a team that is winning - it takes no courage. But to stand behind a team to defend a team when it is down and really needs you, that takes a lot of courage.\"'),('2014-12-04 12:00:00',5.53,'overcast clouds','William James: \"It is only by risking our persons from one hour to another that we live at all. And often enough our faith beforehand in an uncertified result is the only thing that makes the result come true.\"'),('2014-12-05 00:00:00',3.52,'light rain',NULL),('2014-12-05 11:00:00',8.29,'moderate rain',NULL),('2014-12-05 12:00:00',7.15,'moderate rain','Dwight D. Eisenhower: \"The older I get the more wisdom I find in the ancient rule of taking first things first. A process which often reduces the most complex human problem to a manageable proportion.\"'),('2014-12-06 00:00:00',5.52,'sky is clear',NULL),('2014-12-06 11:00:00',4.99,'overcast clouds',NULL),('2014-12-06 12:00:00',7.31,'scattered clouds','Samuel Butler: \"The history of the world is the record of the weakness, frailty and death of public opinion.\"'),('2014-12-07 00:00:00',6.18,'moderate rain',NULL),('2014-12-07 11:00:00',4.59,'moderate rain',NULL),('2014-12-07 12:00:00',6.77,'moderate rain',NULL),('2014-12-08 00:00:00',7.6,'sky is clear',NULL),('2014-12-08 12:00:00',7.61,'light rain',NULL),('2014-12-09 00:00:00',7.11,'sky is clear',NULL),('2014-12-09 12:00:00',6.43,'sky is clear',NULL),('2014-12-10 00:00:00',9.27,'heavy intensity rain',NULL),('2014-12-10 12:00:00',8.48,'moderate rain',NULL),('2014-12-11 00:00:00',7.68,'light rain',NULL),('2014-12-11 12:00:00',7.01,'light rain',NULL),('2014-12-12 00:00:00',7.88,'moderate rain',NULL),('2014-12-12 12:00:00',7.44,'moderate rain',NULL),('2014-12-13 00:00:00',6.75,'moderate rain',NULL),('2014-12-13 12:00:00',7.72,'sky is clear',NULL),('2014-12-14 00:00:00',5.66,'light rain',NULL),('2014-12-14 12:00:00',7.34,'moderate rain',NULL),('2014-12-15 00:00:00',5.52,'light rain',NULL),('2014-12-15 12:00:00',7.89,'light rain',NULL),('2014-12-16 00:00:00',6.04,'light rain',NULL),('2014-12-16 12:00:00',7.62,'sky is clear',NULL),('2014-12-17 00:00:00',5.78,'light rain',NULL),('2014-12-17 12:00:00',7.67,'light rain',NULL),('2014-12-18 00:00:00',7.43,'sky is clear',NULL);
/*!40000 ALTER TABLE `dailyupdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `EventID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  `Location` varchar(45) DEFAULT NULL,
  `CreatedBy` varchar(255) NOT NULL,
  `Start` varchar(255) NOT NULL,
  `End` varchar(255) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Text` text,
  `CalendarID` int(11) NOT NULL,
  `Active` tinyint(4) NOT NULL DEFAULT '1',
  `ActivityID` varchar(45) DEFAULT NULL,
  `CBSeventID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EventID`),
  UNIQUE KEY `CBSeventID_UNIQUE` (`CBSeventID`),
  KEY `CalendarID_idx` (`CalendarID`),
  CONSTRAINT `events_CalendarID` FOREIGN KEY (`CalendarID`) REFERENCES `calendar` (`CalendarID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1033 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES (939,'Exercise','Ks71','CBS','2014-9-23 14:25:00','2014-9-23 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_94185202954f14e733c0d9c3d05ef1ab_5bf6afa2cbc755c1108cb32442d47d39'),(940,'Exercise','Ks71','CBS','2014-9-30 14:25:00','2014-9-30 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_7398b4dc51b393aac3bb1a659022696b_5a8e0a0f225ab88d402a97238c14e5a5'),(941,'Exercise','Ks71','CBS','2014-10-7 14:25:00','2014-10-7 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_7e8e0f029d8eca24521bd1713c529240_112d9c7bec52aa934758683663fc25df'),(942,'Exercise','Ks71','CBS','2014-10-21 14:25:00','2014-10-21 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_9e0c12af53c65b4dbddf88ba5bf5864a_040bdd40d8f04623948566a6a30f5eed'),(943,'Exercise','Ks71','CBS','2014-10-28 14:25:00','2014-10-28 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_d220ff202cb43c4f7482fbc4efae154c_cdb9c9f8378014a68dd911a4f13a38ad'),(944,'Exercise','Ks71','CBS','2014-11-4 14:25:00','2014-11-4 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_bfa9ccbf08167e38d27db52d5f33b591_dc3689d07be43c6c742ffd23f116665b'),(945,'Exercise','Ks71','CBS','2014-11-11 14:25:00','2014-11-11 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_71e336a818f5181f52e5e84b7b19267e_5d2a492a69a2f9bf4fb771cbc8d07f28'),(946,'Exercise','Ks71','CBS','2014-11-18 14:25:00','2014-11-18 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_3f7b826317d5fc329410d947c58d515f_0cd6a71f2318b71ce27f6fdc963731cb'),(947,'Exercise','Ks71','CBS','2014-11-25 14:25:00','2014-11-25 16:05:00','BINTO1056U.XB_E14','Ledelse af IS - forandring, innovation og viden (XB)',55,1,'BINTO1056U_XB_E14','BINTO1056U_XB_E14_f2ad3729ddc88c06a630e9cd93d1c5a0_0da201a5138f4c31e15885d892579fef'),(948,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-9-4 9:50:00','2014-9-4 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_18fa304e85da594e22ddecf6e83921b6_58103a314b6de11f155b544bff392ed4'),(949,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-9-11 9:50:00','2014-9-11 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_f60c4c31e318676bb67148df2968b3df_ba6493f904d8e4053b65044f8589aea6'),(950,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-9-18 9:50:00','2014-9-18 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_302e5dacae0fd6e0728cbf3bc169d6dc_72e5fdd846ab59092a2d4275c0b184c7'),(951,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-10-2 9:50:00','2014-10-2 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_36fe519a966e93afebf1cafc3bc9a33a_9c7463055ec3011c8ada3beb4cb200ef'),(952,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-10-9 9:50:00','2014-10-9 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_e145bbddb222e8dc241d8ba625d818c2_27055189a706b05ed66be0ce5f1e191a'),(953,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-10-23 9:50:00','2014-10-23 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_96172435dfa810fd98cff8f36484b5c6_57382d1f02f19708b73544be26507ed2'),(954,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-11-6 9:50:00','2014-11-6 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_3d7748fd86d6f6fd3c1f95c652d16640_e5b3bf119a95dc297c3f0cbba45b9ffe'),(955,'Lecture','SPs14','CBS','2014-11-6 11:40:00','2014-11-6 13:20:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_72fca254ca25a1045f2813a76e402ca0_eebe0993e8bd18d598369e15aaa3d1e2'),(956,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-11-13 9:50:00','2014-11-13 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_e2da215eafb6d4c04c64bca39d3465a7_5d4669a1a0bae900fa4cbcaa7561685a'),(957,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-11-20 9:50:00','2014-11-20 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_5d09b743653bbe32d4a6b7795f42d7f1_5ba57cdba18e645d30e3c5d8cb464d1e'),(958,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-11-27 9:50:00','2014-11-27 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_58748ecf05ff5c0229a222892d11f532_9fbf196622c06772e8daa88b9a96a4c9'),(959,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-12-4 9:50:00','2014-12-4 11:30:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_12b4078682059d0f5c79ab86de7ebd3d_c425273f69fa1ca0d6a177547d4886d6'),(960,'Lecture','SPs05','CBS','2014-12-4 11:40:00','2014-12-4 13:20:00','BINTO1056U.LA_E14','Ledelse af IS - forandring, innovation og viden (LA)',58,1,'BINTO1056U_LA_E14','BINTO1056U_LA_E14_e5b175311195e6b7992036fdad265513_40926a3a7052063acecff47f0f7daf0b'),(961,'Lecture','SPs14','CBS','2014-9-5 8:00:00','2014-9-5 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_9ec39e04ed4cde6c77e9dad2791ec065_a2b2d1448f89a42f4abcadb195f5baa5'),(962,'Lecture','SPs14','CBS','2014-9-12 8:00:00','2014-9-12 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_f2608aef8ea5cefc3b4e4661e8a1b65a_6f97eb60f5ac86a8bd6bd40e15bc21df'),(963,'Lecture','SPs14','CBS','2014-9-19 8:00:00','2014-9-19 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_358e537c3b8bc9ba3a777151fe0dbe84_46ecada33c30f1809f39cf3de0c32188'),(964,'Lecture','SPs08 Nykredit Aud.','CBS','2014-9-26 8:00:00','2014-9-26 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_c419aa6aa104d33cb5a9f95bd0e80fdc_26a862c80fdd0194eee0789c9824f71c'),(965,'Lecture','SPs08 Nykredit Aud.','CBS','2014-10-3 8:00:00','2014-10-3 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_5f83bd976f9ebb645215f83a6ab91cb6_33a96cdc76ad733c12a04a077aa10a0f'),(966,'Lecture','SPs14','CBS','2014-10-10 8:00:00','2014-10-10 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_d4eafb526e6b19b2097caafaa359d9ea_fd400d1cd3e756ff66af762e49b9d864'),(967,'Exercise','SPs14','CBS','2014-10-10 9:50:00','2014-10-10 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_3c84d9a04f6d9956e119593cfcb02e8d_c88c4d39d7d73e1b8d4e135909f585a4'),(968,'Lecture','SPs14','CBS','2014-10-24 8:00:00','2014-10-24 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_9efae7c9cdae2823fc50939fd0b707b9_303810da09857404dc38dfbf0885b0d9'),(969,'Exercise','SPs14','CBS','2014-10-24 9:50:00','2014-10-24 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_39e8e647e442d2b4552ee11a4ef5a280_a7869193738d7abc220e934024076aa6'),(970,'Lecture','SPs14','CBS','2014-10-31 8:00:00','2014-10-31 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_ffae383a70ee7ce6bb7aae8dcda0173f_5489b53bc3f67b37a4dd3d3ef0d3f6a5'),(971,'Exercise','SPs14','CBS','2014-10-31 9:50:00','2014-10-31 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_138900750f545cee4d178bb1e3127827_e09f9ffa696980f8c16b9c7ef5978664'),(972,'Lecture','SPs14','CBS','2014-11-7 8:00:00','2014-11-7 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_401643c4779ada5372c22467193c5c7b_ee414e568345d0d258eff5b029b3d5c6'),(973,'Exercise','SPs14','CBS','2014-11-7 9:50:00','2014-11-7 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_a048a5ffaff5c16bf6c77efa921a5f95_29a2efde41aaa26fb9bcae0e3a280507'),(974,'Lecture','SPs07 British American Tobacco Aud.','CBS','2014-11-14 8:00:00','2014-11-14 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_84ee1407ccab5835c194316b9a23225e_04133ac7df97b59ae738e2561b6ba9ff'),(975,'Exercise','Ks43','CBS','2014-11-14 10:45:00','2014-11-14 12:25:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_03ec9237d4d81e30d5af473483fa9ba0_b0ab1291f8ccef726a8b2ed6a012ce36'),(976,'Lecture','SPs14','CBS','2014-11-21 8:00:00','2014-11-21 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_eb96741d7e362c553a29909d1d7fac40_5a2b43b47c8d9ba08922392e3bb39ffb'),(977,'Exercise','SPs14','CBS','2014-11-21 9:50:00','2014-11-21 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_d021d311a82b10041be674c681927d11_c4c7ceb895f0138373cdb4a95db9f11f'),(978,'Lecture','SPs14','CBS','2014-11-28 8:00:00','2014-11-28 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_08b31735844cce4bbe610860c203dc56_04f47d35daf6fedc12dc1d52b01d9a77'),(979,'Exercise','SPs14','CBS','2014-11-28 9:50:00','2014-11-28 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_d1a2317580bbacb329267525f5d44f9a_74c66b0f60b3e273099331f7fba14406'),(980,'Lecture','SPs14','CBS','2014-12-5 8:00:00','2014-12-5 9:40:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_9aaa22b8997958d858a6bf61873008b6_ff6068cb0718eff1e64451fbe525394e'),(981,'Exercise','SPs14','CBS','2014-12-5 9:50:00','2014-12-5 11:30:00','BINTO1051U.LA_E14','Virksomhedens økonomiske styring (3) (LA)',62,1,'BINTO1051U_LA_E14','BINTO1051U_LA_E14_798f38ae6f0492ff3d1aab83b28b6a3e_3ac2d11a4993572e1c57bab3872cbfe4'),(982,'Exercise','SP213','CBS','2014-9-15 8:00:00','2014-9-15 9:40:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_714ff8c1a1d8f5e918829fef3ff92a0f_23e125dbca8f1d6655b7a40a77481a82'),(983,'Lecture','Falkoner_Bio_Sal_1','CBS','2014-9-22 8:00:00','2014-9-22 10:35:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_122f76f124a8f4c9ebf4774703906c3a_d1a34baa1d5e06633bafd83a6459fa70'),(984,'Exercise','SPs16','CBS','2014-9-22 12:35:00','2014-9-22 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_d1c74830665ba80d8649ad61ec3b5fb3_6f2cd1db810109f119383e57add2fe2e'),(985,'Exercise','SP113','CBS','2014-9-25 14:25:00','2014-9-25 16:05:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_d916d28a928072ed4bfd9e4554d8aa01_11cfa365e80acc3a3f24d1bd7a9b4096'),(986,'Exercise','SPs16','CBS','2014-9-29 12:35:00','2014-9-29 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_852d26c3e4958d3bc14eaaa436959936_5a14f7f4349a7bd0c82983eba95da9ed'),(987,'Lecture','D1Ø041','CBS','2014-9-29 15:20:00','2014-9-29 17:55:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_589128fabb341040c0a397410011f85b_aee378ea370e5ebadd25671f9dc35b22'),(988,'Exercise','SPs07 British American Tobacco Aud.','CBS','2014-10-2 15:20:00','2014-10-2 17:55:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_41474e66c2791cadea606da728d82d97_47eaf4e1e19723a507fe6bd2c3e0e67a'),(989,'Exercise','FH_C1','CBS','2014-10-3 12:35:00','2014-10-3 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_6b3f3fefdcd2d7f662a89c5a69434478_62542be3229fccaf81cffaafe7cf22e4'),(990,'Exercise','FH_C1','CBS','2014-10-6 15:20:00','2014-10-6 17:55:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_b9993ed06fc07ba76ca8c77635e6cd40_bcc3edeff285789e764b28f9f2bcedbd'),(991,'Exercise','SP213','CBS','2014-10-9 12:35:00','2014-10-9 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_c66ad519fa4d748d159f2f6558c987b5_5c8cb330e49abf45ed7c82d270f57fb1'),(992,'Exercise','SPs07 British American Tobacco Aud.','CBS','2014-10-9 15:20:00','2014-10-9 17:55:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_6f8df177b64937831a6f5fe6668db8d5_0b8fed4b1d4269866e6bdd4c48018ff3'),(993,'Exercise','FH_C1','CBS','2014-10-10 12:35:00','2014-10-10 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_ae8bdff94869ba9f488ee350f1103c07_e760536bb1c6e1d0025a0d01c0b1fbad'),(994,'Exercise','SP213','CBS','2014-10-13 8:55:00','2014-10-13 22:40:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_b57af4bcf4f354bec0688600877cd8e8_283ddf094af57ca4d9379d0ac2c35968'),(995,'Exercise','SP213','CBS','2014-10-16 8:55:00','2014-10-16 22:40:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_d7f2b38d7b6d10e9f88ca66238d1642b_13b2cb88475198df370cf6580579dc3c'),(996,'Exercise','SP213','CBS','2014-10-20 15:20:00','2014-10-20 17:00:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_c9b06993a9a8995f9a2b9440cfe30b82_f6973de9c576c49073dcbf0b6de8c319'),(997,'Exercise','SP213','CBS','2014-10-23 12:35:00','2014-10-23 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_39b96e251b3c6b80094d62ae9da51b62_11bbccde9063ba767e55fb518062486f'),(998,'Exercise','FH_C2','CBS','2014-10-27 12:35:00','2014-10-27 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_dcb7f16bf685966c0f38741cc7e1eb0a_20c03c19d053f3ec1581450eac3e07fe'),(999,'Exercise','HOW601','CBS','2014-10-30 12:35:00','2014-10-30 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_2bee5e4f2ef7064f69f6b0bc93e708ec_16b3bd96878ffb7961ce30d2fbeedff7'),(1000,'Exercise','SP213','CBS','2014-11-10 14:25:00','2014-11-10 16:05:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_570b63504c27e96b88a92c1988199302_7ccc114dfe5995cd92bf5aa6f0fd66a1'),(1001,'Exercise','SP113','CBS','2014-11-13 14:25:00','2014-11-13 16:05:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_492f80dbe6b3fbb842b27b1c53169594_6a25503ce9d920b09d7122a7023e6416'),(1002,'Exercise','FH_C2','CBS','2014-11-17 12:35:00','2014-11-17 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_c973de5d63c8411b8aabeceed4aad80c_6f99479443932b7224ee37f75adbad90'),(1003,'Exercise','SP213','CBS','2014-11-20 12:35:00','2014-11-20 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_83a098472ce11ebe9f6e33e7c6494351_a535189005ff835559f03ed6eaca9a6d'),(1004,'Exercise','SP213','CBS','2014-12-8 8:00:00','2014-12-8 9:40:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_455b9f4bfe7f0a41cb90334241089fbb_dbb8b341b3c3d9f605c7a7e4517205b0'),(1005,'Exercise','FH_C2','CBS','2014-12-8 12:35:00','2014-12-8 14:15:00','BINTO1067U.LA_E14','Distribuerede systemer (LA)',63,1,'BINTO1067U_LA_E14','BINTO1067U_LA_E14_58a293082a405d849adfc1db1f7ba449_eec9f0fda11100296a308501842e19ad'),(1006,'Lecture','SP202 Carlsberg Group Aud.','CBS','2014-10-20 9:50:00','2014-10-20 11:30:00','BINTO1035U.LA_E14','Makroøkonomi (LA)',64,1,'BINTO1035U_LA_E14','BINTO1035U_LA_E14_49893d6227f838d040258860a345f795_46dc53ea245d99cd9f3285b3886adae1'),(1007,'Lecture','SPs05','CBS','2014-10-27 9:50:00','2014-10-27 11:30:00','BINTO1035U.LA_E14','Makroøkonomi (LA)',64,1,'BINTO1035U_LA_E14','BINTO1035U_LA_E14_eb5d1b805112ad442aca0e512b6f3a6e_aafbe6309a9a80d2fbbd78fc6705fc22'),(1008,'Lecture','Ks71','CBS','2014-9-3 8:00:00','2014-9-3 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_881fb386f81fb1234083d29b1a380d07_65d80bae176d37fb8df63c7bb89047f0'),(1009,'Lecture','Ks71','CBS','2014-9-10 8:00:00','2014-9-10 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_36346be3295577f4cf056059cdf14c0e_9f151e11a4fa932e484c1286d199f810'),(1010,'Lecture','Ks71','CBS','2014-9-17 8:00:00','2014-9-17 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_f930dffd55ca75168e708103716f57d8_de8cb5401a0e0e7b3017181144ab2a19'),(1011,'Lecture','Ks71','CBS','2014-9-24 8:00:00','2014-9-24 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_2e2166e91bfccb5815024c2158116332_91e104fef8b7101c9d954b0bdf0b0cc5'),(1012,'Lecture','Ks71','CBS','2014-10-1 8:00:00','2014-10-1 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_9e4de72f76bf4bf2addb497f4a94b774_4d4676025fd88f3b7e4b8cd08e7f2c2b'),(1013,'Exercise','SP114','CBS','2014-10-2 8:00:00','2014-10-2 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_cdce1cb046af7b80851ceb83806c5166_2d334c0cff72d923f7e232a5df098f6d'),(1014,'Lecture','Ks71','CBS','2014-10-8 8:00:00','2014-10-8 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_7f7a5e7a81099334bb1b7f02ac41f2d4_a4bd44a9c73c7065e071c5d76e4eeaec'),(1015,'Exercise','SP114','CBS','2014-10-9 8:00:00','2014-10-9 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_fff4d36ee938b38263e52e6030f3a43a_7246a4db5a3a22aa3f6b3074a4e7abd8'),(1016,'Lecture','Ks71','CBS','2014-10-22 8:00:00','2014-10-22 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_9e30b43ac7ec55d04afa8e587ccf5441_fc2a430db393058ecec9f9786b8283fe'),(1017,'Exercise','SP114','CBS','2014-10-23 8:00:00','2014-10-23 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_ea0ac2c5d3a0b7ab86cfaddfcf2892d9_db3b0aa8f7b248179e281c9bfcda7dc4'),(1018,'Lecture','Ks71','CBS','2014-10-29 8:00:00','2014-10-29 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_7f246442adf64497950ea07b40c56fc0_58a881b0a81cea2fd3ffdb09d06d9a88'),(1019,'Exercise','SP114','CBS','2014-10-30 8:00:00','2014-10-30 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_451ea90f80a80717cad8c5948bb08610_72c76ff1a52da1c76a9e49203c250052'),(1020,'Lecture','Ks71','CBS','2014-11-5 8:00:00','2014-11-5 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_66dc211617178ee63ca3899e65f261b7_20fd87d67f40b45908476509000f401f'),(1021,'Exercise','SP114','CBS','2014-11-6 8:00:00','2014-11-6 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_fe4d175deac0633a0bdfb51c2f0724c9_dbfaf4494cf3f2f11900ffd9ac6d7ca8'),(1022,'Lecture','Ks71','CBS','2014-11-12 8:00:00','2014-11-12 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_25302dc0fc4dbe99c19ebb60dff99c95_1209a0d02d7a278280cbea5e1135ef24'),(1023,'Exercise','SP114','CBS','2014-11-13 8:00:00','2014-11-13 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_5134d3c99f0f76a3e02b8570e6a32dc7_cba39c65cf434ba1917b3860ab974b4b'),(1024,'Lecture','Ks71','CBS','2014-11-19 8:00:00','2014-11-19 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_748f3b70e316faf5becc2799daa4d00b_ac88b80aa1e480e40f7a985a5066b550'),(1025,'Exercise','SP114','CBS','2014-11-20 8:00:00','2014-11-20 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_f08f9450923d73d2c266dd4291fdd70a_13625903508a35137abd0928f0f2f36e'),(1026,'Lecture','Ks71','CBS','2014-11-26 8:00:00','2014-11-26 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_7522cb9e2c47efa1e3ff6ac24002be36_99537c3adf3e04bc329ed38f0e58ce2e'),(1027,'Exercise','SP114','CBS','2014-11-27 8:00:00','2014-11-27 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_5eea61ae6c2d8824d340e3b57c52dc11_a48b708b5cdbd426b599420f90697a1f'),(1028,'Lecture','Ks71','CBS','2014-12-3 8:00:00','2014-12-3 9:40:00','BINTO1035U.XB_E14','Makroøkonomi (XB)',65,1,'BINTO1035U_XB_E14','BINTO1035U_XB_E14_e523a307e38d3b09094746c6d679e683_087a51abc6bd219e13e0351601e0e1aa'),(1029,'fest','vet ikke','jolj','2014-12-12 12:12:12','2014-12-13 12:12:12','a new event','Super cool event',67,0,NULL,NULL),(1030,'event','gfdsgsdfg|12','jolj','2014-12-12 12:12:12','2015-12-12 12:12:12','event','et event',68,0,NULL,NULL),(1031,'BBBBBBBBBBBBB','dude','jolj','2014-12-5 10::00','2014-12-5 11::00','WHILFHEB','AAAAAAA',67,0,NULL,NULL);
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GroupName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `GroupName_UNIQUE` (`GroupName`),
  KEY `Email_idx` (`Email`),
  CONSTRAINT `GroupEmail` FOREIGN KEY (`Email`) REFERENCES `users` (`Email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `NoteID` int(11) NOT NULL AUTO_INCREMENT,
  `EventID` int(11) NOT NULL,
  `CreatedBy` varchar(255) NOT NULL,
  `Text` text,
  `Created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Active` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`NoteID`),
  KEY `EventID_idx` (`EventID`),
  CONSTRAINT `EventID` FOREIGN KEY (`EventID`) REFERENCES `events` (`EventID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notes`
--

LOCK TABLES `notes` WRITE;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `RoleID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(45) NOT NULL,
  `Type` varchar(200) NOT NULL,
  PRIMARY KEY (`RoleID`),
  KEY `email_idx` (`Email`),
  CONSTRAINT `email` FOREIGN KEY (`Email`) REFERENCES `users` (`Email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'jolj','admin'),(2,'nihabr','user'),(4,'hejsa123','user'),(5,'nibr13ae','user'),(6,'alla13ad','user'),(7,'blabla','user'),(8,'nyuser','admin'),(9,'test user','user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(45) CHARACTER SET latin1 NOT NULL,
  `Active` tinyint(4) DEFAULT '0',
  `Created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Password` varchar(200) CHARACTER SET latin1 NOT NULL,
  `Exist` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`UserID`,`Email`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'jolj',0,'2014-11-24 15:14:03','123',1),(3,'nihabr',0,'2014-11-24 15:20:07','1',1),(5,'hejsa123',1,'2014-11-25 12:50:52','hejsa123',1),(6,'nibr13ae',1,'2014-11-25 13:42:48','1234',1),(7,'alla13ad',0,'2014-12-03 17:09:29','1234',0),(8,'blabla',0,'2014-12-06 09:45:18','123',0),(9,'nyuser',0,'2014-12-07 16:08:20','123',0),(10,'test user',0,'2014-12-07 16:14:50','123',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-09  1:40:00
