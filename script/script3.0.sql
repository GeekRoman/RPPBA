-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: logistics
-- ------------------------------------------------------
-- Server version	5.6.37-log

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
-- Table structure for table `log_availability`
--

DROP TABLE IF EXISTS `log_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_availability` (
  `AvailabilityId` varchar(15) NOT NULL,
  `ItemId` varchar(15) NOT NULL,
  `CellId` varchar(15) NOT NULL,
  `QuantityOnHand` int(11) DEFAULT NULL,
  `OrderQuantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`AvailabilityId`),
  KEY `CellId_idx` (`CellId`),
  KEY `Item_idx` (`ItemId`),
  CONSTRAINT `CellId` FOREIGN KEY (`CellId`) REFERENCES `log_cell` (`CellId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ItemId` FOREIGN KEY (`ItemId`) REFERENCES `log_nomenclature` (`ItemId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_availability`
--

LOCK TABLES `log_availability` WRITE;
/*!40000 ALTER TABLE `log_availability` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_availability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_cell`
--

DROP TABLE IF EXISTS `log_cell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_cell` (
  `CellId` varchar(15) NOT NULL,
  `StorageId` varchar(11) NOT NULL,
  `Lenght` int(11) NOT NULL,
  `Height` int(11) NOT NULL,
  `Widht` int(11) NOT NULL,
  `Type` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`CellId`),
  KEY `StorageId_idx` (`StorageId`),
  CONSTRAINT `StorageId` FOREIGN KEY (`StorageId`) REFERENCES `log_storage` (`StorageId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_cell`
--

LOCK TABLES `log_cell` WRITE;
/*!40000 ALTER TABLE `log_cell` DISABLE KEYS */;
INSERT INTO `log_cell` VALUES ('1','1',10,10,15,'BOX','empty'),('2','2',20,20,20,'20','empty');
/*!40000 ALTER TABLE `log_cell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_nomenclature`
--

DROP TABLE IF EXISTS `log_nomenclature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_nomenclature` (
  `ItemId` varchar(15) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Lenght` int(11) DEFAULT NULL,
  `Height` int(11) DEFAULT NULL,
  `Width` int(11) DEFAULT NULL,
  `Config` varchar(45) DEFAULT NULL,
  `Provider` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_nomenclature`
--

LOCK TABLES `log_nomenclature` WRITE;
/*!40000 ALTER TABLE `log_nomenclature` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_nomenclature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_storage`
--

DROP TABLE IF EXISTS `log_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_storage` (
  `StorageId` varchar(11) NOT NULL,
  `Address` varchar(45) NOT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`StorageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_storage`
--

LOCK TABLES `log_storage` WRITE;
/*!40000 ALTER TABLE `log_storage` DISABLE KEYS */;
INSERT INTO `log_storage` VALUES ('1','г. Минск бля бля','10'),('2','2','Пустой');
/*!40000 ALTER TABLE `log_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_task`
--


DROP TABLE IF EXISTS `log_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_task` (
  `TaskId` varchar(15) NOT NULL,
  `TransitId` varchar(15) DEFAULT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Direction` varchar(45) DEFAULT NULL,
  `Status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TaskId`),
  KEY `TransitId_idx` (`TransitId`),
  CONSTRAINT `TransitId` FOREIGN KEY (`TransitId`) REFERENCES `log_transit` (`TransitId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_task`
--

LOCK TABLES `log_task` WRITE;
/*!40000 ALTER TABLE `log_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_tasklist`
--

DROP TABLE IF EXISTS `log_tasklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_tasklist` (
  `TaskListId` varchar(15) NOT NULL,
  `TaskId` varchar(15) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`TaskListId`),
  KEY `TaskId_idx` (`TaskId`),
  CONSTRAINT `TaskId` FOREIGN KEY (`TaskId`) REFERENCES `log_task` (`TaskId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_tasklist`
--

LOCK TABLES `log_tasklist` WRITE;
/*!40000 ALTER TABLE `log_tasklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_tasklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_transit`
--

DROP TABLE IF EXISTS `log_transit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_transit` (
  `TransitId` varchar(15) NOT NULL,
  `AvailabilityId` varchar(15) NOT NULL,
  `Move_Quantity` int(11) DEFAULT NULL,
  `Out_Storage` varchar(15) DEFAULT NULL,
  `In_Storage` varchar(15) DEFAULT NULL,
  `Status` varchar(45) NOT NULL,
  PRIMARY KEY (`TransitId`),
  KEY `AvailabilityId_idx` (`AvailabilityId`),
  KEY `In_Storage_idx` (`In_Storage`),
  KEY `Out_Storage_idx` (`Out_Storage`),
  CONSTRAINT `AvailabilityId` FOREIGN KEY (`AvailabilityId`) REFERENCES `log_availability` (`AvailabilityId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `In_Storage` FOREIGN KEY (`In_Storage`) REFERENCES `log_cell` (`CellId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Out_Storage` FOREIGN KEY (`Out_Storage`) REFERENCES `log_cell` (`CellId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_transit`
--

LOCK TABLES `log_transit` WRITE;
/*!40000 ALTER TABLE `log_transit` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_transit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-02 19:45:53
