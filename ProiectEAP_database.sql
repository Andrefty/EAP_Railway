-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: proiect
-- ------------------------------------------------------
-- Server version	8.0.29-0ubuntu0.22.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `angajati`
--

DROP TABLE IF EXISTS `angajati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `angajati` (
  `id_angajat` int unsigned NOT NULL,
  `id_departament` int unsigned NOT NULL,
  `id_functie` int unsigned NOT NULL,
  `prenume` varchar(50) NOT NULL,
  `nume` varchar(50) NOT NULL,
  `email` varchar(40) NOT NULL,
  `hire_date` date NOT NULL,
  `salariu` double NOT NULL,
  `CNP` varchar(15) NOT NULL,
  PRIMARY KEY (`id_angajat`),
  UNIQUE KEY `ANGAJATI_EMAIL_UQ` (`email`),
  UNIQUE KEY `ANGAJATI_CNP_UQ` (`CNP`),
  KEY `ANGAJATI_DEP_FK` (`id_departament`),
  KEY `ANGAJATI_FUNCTIE_FK` (`id_functie`),
  CONSTRAINT `ANGAJATI_DEP_FK` FOREIGN KEY (`id_departament`) REFERENCES `departamente` (`id_departament`) ON DELETE CASCADE,
  CONSTRAINT `ANGAJATI_FUNCTIE_FK` FOREIGN KEY (`id_functie`) REFERENCES `functii` (`id_functie`) ON DELETE CASCADE,
  CONSTRAINT `ANGAJATI_EMAIL_CK` CHECK ((`email` like _utf8mb4'%@%.%')),
  CONSTRAINT `ANGAJATI_SALARIU_CK` CHECK ((`salariu` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `angajati`
--

LOCK TABLES `angajati` WRITE;
/*!40000 ALTER TABLE `angajati` DISABLE KEYS */;
INSERT INTO `angajati` VALUES (1,2,3,'Marcheta','Florea','charlene19@example.com','2000-05-29',20000,'6150519016179'),(2,2,2,'Alexe','Mazilescu','davidedwards@example.com','2001-05-07',10000,'6150519016830'),(3,3,4,'Petrică','Preda','bbell@example.org','2006-02-07',15000,'5150519017427'),(4,4,5,'Caius','Preda','igraves@example.net','2001-12-24',16600,'6150519016849'),(5,5,2,'Monica','Ene','kimdwayne@example.org','2010-08-13',10000,'5150519016249');
/*!40000 ALTER TABLE `angajati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bilete`
--

DROP TABLE IF EXISTS `bilete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bilete` (
  `id_pasager` int unsigned NOT NULL,
  `id_cursa` int unsigned NOT NULL,
  `pret` double NOT NULL,
  PRIMARY KEY (`id_cursa`,`id_pasager`),
  KEY `BILETE_PASAGER_FK` (`id_pasager`),
  CONSTRAINT `BILETE_CURSA_FK` FOREIGN KEY (`id_cursa`) REFERENCES `curse` (`id_cursa`) ON DELETE CASCADE,
  CONSTRAINT `BILETE_PASAGER_FK` FOREIGN KEY (`id_pasager`) REFERENCES `pasageri` (`id_pasager`) ON DELETE CASCADE,
  CONSTRAINT `BILETE_PRET_CK` CHECK ((`pret` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bilete`
--

LOCK TABLES `bilete` WRITE;
/*!40000 ALTER TABLE `bilete` DISABLE KEYS */;
INSERT INTO `bilete` VALUES (5,1,67.5),(1,2,23.43),(2,3,657),(3,4,23.45),(2,5,78.43);
/*!40000 ALTER TABLE `bilete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colete`
--

DROP TABLE IF EXISTS `colete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colete` (
  `id_colet` int unsigned NOT NULL,
  `id_expeditor` int unsigned NOT NULL,
  `id_cursa` int unsigned DEFAULT NULL,
  `masa` double NOT NULL,
  PRIMARY KEY (`id_colet`),
  KEY `COLETE_EXPEDITOR_FK` (`id_expeditor`),
  KEY `COLETE_CURSA_FK` (`id_cursa`),
  CONSTRAINT `COLETE_CURSA_FK` FOREIGN KEY (`id_cursa`) REFERENCES `curse` (`id_cursa`) ON DELETE SET NULL,
  CONSTRAINT `COLETE_EXPEDITOR_FK` FOREIGN KEY (`id_expeditor`) REFERENCES `expeditori` (`id_expeditor`) ON DELETE CASCADE,
  CONSTRAINT `COLETE_MASA_CK` CHECK ((`masa` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colete`
--

LOCK TABLES `colete` WRITE;
/*!40000 ALTER TABLE `colete` DISABLE KEYS */;
INSERT INTO `colete` VALUES (1,2,4,0.25),(2,1,2,3.3),(3,5,4,12.3),(4,2,3,6),(5,3,5,34);
/*!40000 ALTER TABLE `colete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curse`
--

DROP TABLE IF EXISTS `curse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curse` (
  `id_cursa` int unsigned NOT NULL,
  `id_statie_plecare` int unsigned NOT NULL,
  `id_statie_sosire` int unsigned NOT NULL,
  `id_tren` int unsigned NOT NULL,
  `distanta` double NOT NULL,
  `data_plecare` datetime NOT NULL,
  PRIMARY KEY (`id_cursa`),
  UNIQUE KEY `CURSE_STATII_DATA_UQ` (`id_statie_plecare`,`id_statie_sosire`,`data_plecare`),
  KEY `CURSE_STATIES_FK` (`id_statie_sosire`),
  KEY `CURSE_TREN_FK` (`id_tren`),
  CONSTRAINT `CURSE_STATIEP_FK` FOREIGN KEY (`id_statie_plecare`) REFERENCES `statii` (`id_statie`) ON DELETE CASCADE,
  CONSTRAINT `CURSE_STATIES_FK` FOREIGN KEY (`id_statie_sosire`) REFERENCES `statii` (`id_statie`) ON DELETE CASCADE,
  CONSTRAINT `CURSE_TREN_FK` FOREIGN KEY (`id_tren`) REFERENCES `trenuri` (`id_tren`) ON DELETE CASCADE,
  CONSTRAINT `CURSE_DISTANTA_CK` CHECK ((`distanta` > 0)),
  CONSTRAINT `CURSE_STATII_CK` CHECK ((`id_statie_plecare` <> `id_statie_sosire`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curse`
--

LOCK TABLES `curse` WRITE;
/*!40000 ALTER TABLE `curse` DISABLE KEYS */;
INSERT INTO `curse` VALUES (1,1,2,1,244.23,'2022-02-07 12:35:00'),(2,1,2,1,244.23,'2022-04-07 12:35:00'),(3,2,1,4,244.33,'2022-01-06 14:47:00'),(4,4,5,2,65.5,'2022-01-20 14:47:00'),(5,3,5,5,29.36,'2022-01-06 10:47:00');
/*!40000 ALTER TABLE `curse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamente`
--

DROP TABLE IF EXISTS `departamente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamente` (
  `id_departament` int unsigned NOT NULL,
  `id_manager` int unsigned DEFAULT NULL,
  `nume_departament` varchar(40) NOT NULL,
  PRIMARY KEY (`id_departament`),
  UNIQUE KEY `DEPARTAMENTE_NUME_UQ` (`nume_departament`),
  UNIQUE KEY `DEPARTAMENTE_MANAGER_UQ` (`id_manager`),
  CONSTRAINT `DEPARTAMENTE_MANAGER_FK` FOREIGN KEY (`id_manager`) REFERENCES `angajati` (`id_angajat`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamente`
--

LOCK TABLES `departamente` WRITE;
/*!40000 ALTER TABLE `departamente` DISABLE KEYS */;
INSERT INTO `departamente` VALUES (1,NULL,'Financiar'),(2,1,'Management'),(3,3,'Tehnic'),(4,NULL,'Vanzari'),(5,2,'Marketing');
/*!40000 ALTER TABLE `departamente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expeditori`
--

DROP TABLE IF EXISTS `expeditori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expeditori` (
  `id_expeditor` int unsigned NOT NULL,
  `prenume_expeditor` varchar(50) NOT NULL,
  `nume_expeditor` varchar(50) NOT NULL,
  `telefon_expeditor` varchar(12) DEFAULT NULL,
  `CNP` varchar(15) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id_expeditor`),
  UNIQUE KEY `EXPEDITORI_CNP_UQ` (`CNP`),
  UNIQUE KEY `EXPEDITORI_TELEFON_UQ` (`telefon_expeditor`),
  UNIQUE KEY `EXPEDITORI_EMAIL_UQ` (`email`),
  CONSTRAINT `EXPEDITORI_EMAIL_CK` CHECK ((`email` like _utf8mb4'%@%.%'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expeditori`
--

LOCK TABLES `expeditori` WRITE;
/*!40000 ALTER TABLE `expeditori` DISABLE KEYS */;
INSERT INTO `expeditori` VALUES (1,'Carol','Albu','02348911323','5150519438958','jrogers@example.org'),(2,'Lucențiu','Stănescu','0232892323','5150519435346','kiara76@example.net'),(3,'Blanduzia','Niță','0234692323','6150519436497','jtodd@example.net'),(4,'Marcu','Ababei','02348923563','6150519438239','ewebb@example.com'),(5,'Ion','Popescu','02347892323','5150519435901','jeffrey61@example.net'),(6,'pn1','nm1','86594','135341','lol@lol.lol');
/*!40000 ALTER TABLE `expeditori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `functii`
--

DROP TABLE IF EXISTS `functii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `functii` (
  `id_functie` int unsigned NOT NULL,
  `nume_functie` varchar(40) NOT NULL,
  `descriere_functie` varchar(100) NOT NULL,
  `locatie_functie` varchar(10) NOT NULL,
  PRIMARY KEY (`id_functie`),
  UNIQUE KEY `FUNCTII_NUME_UQ` (`nume_functie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `functii`
--

LOCK TABLES `functii` WRITE;
/*!40000 ALTER TABLE `functii` DISABLE KEYS */;
INSERT INTO `functii` VALUES (1,'Contabil','Desc1','Remote'),(2,'Secretar','Desc2','Hybrid'),(3,'Director vanzari','Desc3','Remote'),(4,'Conductor','Desc4','Onsite'),(5,'Controlor','Desc4','Onsite');
/*!40000 ALTER TABLE `functii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lucreaza_in`
--

DROP TABLE IF EXISTS `lucreaza_in`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lucreaza_in` (
  `id_tren` int unsigned NOT NULL,
  `id_angajat` int unsigned NOT NULL,
  `data_asignare` datetime NOT NULL,
  PRIMARY KEY (`id_tren`,`id_angajat`,`data_asignare`),
  UNIQUE KEY `LUCREAZA_IN_UQ` (`id_angajat`,`data_asignare`),
  CONSTRAINT `LUCREAZA_IN_ANG_FK` FOREIGN KEY (`id_angajat`) REFERENCES `angajati` (`id_angajat`) ON DELETE CASCADE,
  CONSTRAINT `LUCREAZA_IN_TREN_FK` FOREIGN KEY (`id_tren`) REFERENCES `trenuri` (`id_tren`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lucreaza_in`
--

LOCK TABLES `lucreaza_in` WRITE;
/*!40000 ALTER TABLE `lucreaza_in` DISABLE KEYS */;
INSERT INTO `lucreaza_in` VALUES (1,3,'2022-02-07 15:20:30'),(2,3,'2022-02-07 20:00:00'),(3,3,'2022-03-15 08:08:53'),(1,4,'2022-02-07 15:20:30'),(2,4,'2022-02-07 20:00:00');
/*!40000 ALTER TABLE `lucreaza_in` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pasageri`
--

DROP TABLE IF EXISTS `pasageri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pasageri` (
  `id_pasager` int unsigned NOT NULL,
  `prenume_pasager` varchar(50) NOT NULL,
  `nume_pasager` varchar(50) NOT NULL,
  `CNP` varchar(15) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `tip_pasager` varchar(10) NOT NULL,
  PRIMARY KEY (`id_pasager`),
  UNIQUE KEY `PASAGERI_CNP_UQ` (`CNP`),
  UNIQUE KEY `PASAGERI_EMAIL_UQ` (`email`),
  CONSTRAINT `PASAGERI_EMAIL_CK` CHECK ((`email` like _utf8mb4'%@%.%'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pasageri`
--

LOCK TABLES `pasageri` WRITE;
/*!40000 ALTER TABLE `pasageri` DISABLE KEYS */;
INSERT INTO `pasageri` VALUES (1,'Pavel','Cristea','5120118018080','ihopkins@example.net','ADULT'),(2,'Iulian','Pușcașu','1700776602024','petersencatherine@example.com','COPIL'),(3,'Marinela','Albu','2630227400189','nhanson@example.org','INFANT'),(4,'Codruț','Pop','6120118019631','alexandergolden@example.com','STUDENT'),(5,'Florin','Preda','5120118018881','jacksontonya@example.com','PENSIONAR');
/*!40000 ALTER TABLE `pasageri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statii`
--

DROP TABLE IF EXISTS `statii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statii` (
  `id_statie` int unsigned NOT NULL,
  `nume_statie` varchar(20) NOT NULL,
  `adresa` varchar(100) NOT NULL,
  PRIMARY KEY (`id_statie`),
  UNIQUE KEY `STATII_ADRESA_UQ` (`adresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statii`
--

LOCK TABLES `statii` WRITE;
/*!40000 ALTER TABLE `statii` DISABLE KEYS */;
INSERT INTO `statii` VALUES (1,'Sinaia','Strada Bucuresti'),(2,'Predeal','Strada Izvor'),(3,'Azuga','Strada Mihai Voda'),(4,'Marului','Strada Bihor'),(5,'Codlea','Strada Ineu');
/*!40000 ALTER TABLE `statii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trenuri`
--

DROP TABLE IF EXISTS `trenuri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trenuri` (
  `id_tren` int unsigned NOT NULL,
  `fabricant` varchar(30) DEFAULT NULL,
  `an_fabricatie` year DEFAULT NULL,
  PRIMARY KEY (`id_tren`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trenuri`
--

LOCK TABLES `trenuri` WRITE;
/*!40000 ALTER TABLE `trenuri` DISABLE KEYS */;
INSERT INTO `trenuri` VALUES (1,'Bombardier',2015),(2,'Siemens',2000),(3,'Alstom',2012),(4,'Hitachi Rail',2017),(5,'Electroputere',2005);
/*!40000 ALTER TABLE `trenuri` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-27  7:41:25
