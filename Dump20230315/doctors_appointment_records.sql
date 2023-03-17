-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: doctors_appointment
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `records` (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `doctor_id` int NOT NULL,
  `month_id` int NOT NULL,
  `day_id` int NOT NULL,
  `time` varchar(45) NOT NULL,
  `client_name` varchar(100) NOT NULL,
  `client_birth` varchar(10) NOT NULL,
  PRIMARY KEY (`record_id`),
  UNIQUE KEY `record_id_UNIQUE` (`record_id`),
  KEY `fk_record_on_doctor` (`doctor_id`),
  CONSTRAINT `fk_record_on_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` VALUES (1,4,4,3,'09.00','Закушняк Дмитро Михайлович','2008-04-27'),(11,3,3,3,'11.30','Якись Чувак','2000-05-09'),(14,4,2,7,'10.30','я тестю пріложуху','2018-10-19'),(17,6,2,11,'09.30','Віталій Мельник Олександрович','2007-11-17'),(18,6,5,9,'10.30','Василь Семенов Максимович','1982-02-07'),(19,3,2,7,'17.00','Пацієнт №1','1923-11-23'),(20,3,2,7,'17.30','Пацієнт №2','2001-03-24'),(21,4,3,1,'17.00','ПАцієнт','1984-06-08'),(22,4,3,1,'17.30','Ще один пацієнт','1982-11-24'),(23,4,0,10,'10.30','sdasdadad','2023-03-03'),(25,5,2,31,'09.30','імя пацієнта','2016-03-17'),(26,5,2,31,'17.30','пртор','2023-03-04'),(27,5,2,31,'10.30','ошзж','2017-03-15'),(31,5,2,31,'09.00','glj','2018-07-06'),(32,5,8,1,'17.30','Попов Дмитро Євгенович','1939-09-01'),(33,5,2,31,'14.00','Василь Павлов Олегович','1985-05-09');
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-15 23:17:54
