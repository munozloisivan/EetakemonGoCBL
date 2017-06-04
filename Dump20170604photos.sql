-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: juego
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `batalla`
--

DROP TABLE IF EXISTS `batalla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `batalla` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcaptura` int(11) NOT NULL,
  `resultado` int(11) DEFAULT NULL,
  `experiencia` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcaptura_idx` (`idcaptura`),
  CONSTRAINT `idcaptura` FOREIGN KEY (`idcaptura`) REFERENCES `captura` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batalla`
--

LOCK TABLES `batalla` WRITE;
/*!40000 ALTER TABLE `batalla` DISABLE KEYS */;
INSERT INTO `batalla` VALUES (2,5,1,100,'2017-05-05 17:45:32'),(3,6,1,100,'2017-05-05 17:45:32'),(4,7,1,100,'2017-05-05 17:45:32');
/*!40000 ALTER TABLE `batalla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `captura`
--

DROP TABLE IF EXISTS `captura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `captura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idusuariosss` int(11) DEFAULT NULL,
  `idetakemon` int(11) DEFAULT NULL,
  `idlocalizacion` int(11) DEFAULT NULL,
  `nivel` int(11) DEFAULT NULL,
  `experiencia` int(11) DEFAULT NULL,
  `vida` int(11) DEFAULT NULL,
  `ataque` int(11) DEFAULT NULL,
  `defensa` int(11) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `nombreetakemon` varchar(45) DEFAULT NULL,
  `habilidadetakemon` varchar(45) DEFAULT NULL,
  `tipoetakemon` int(11) DEFAULT NULL,
  `imagen` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idusuario_idx` (`idusuariosss`),
  KEY `idetakemon_idx` (`idetakemon`),
  KEY `idlocalizacion_idx` (`idlocalizacion`),
  CONSTRAINT `idetakemon` FOREIGN KEY (`idetakemon`) REFERENCES `etakemon` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idlocalizacion` FOREIGN KEY (`idlocalizacion`) REFERENCES `localizacion` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idusuariosss` FOREIGN KEY (`idusuariosss`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `captura`
--

LOCK TABLES `captura` WRITE;
/*!40000 ALTER TABLE `captura` DISABLE KEYS */;
INSERT INTO `captura` VALUES (5,7,4,5,5,100,90,90,90,1,'2017-05-05 17:45:32','Livanny','Esfumarse',3,'https://www.dropbox.com/pri/get/eetakemon_photos/Livanny.png?_subject_uid=220926105&w=AAAH5jCqMpna-NA_KzEx85yb-lPSRJO4q9LVGuP-Q96dxw'),(6,8,1,19,5,100,90,90,90,1,'2017-05-05 17:46:34','Alakasals','Hipnosis',1,'https://www.dropbox.com/pri/get/eetakemon_photos/Alakasals.png?_subject_uid=220926105&w=AAB8iY7FS_N7lsV9lIj7IfThWm27vdrBsFcd6sxC56oIRQ'),(7,9,2,24,5,100,90,90,90,1,'2017-05-05 17:46:53','Danisloth','Indiferencia',2,'https://www.dropbox.com/pri/get/eetakemon_photos/Danisloth.png?_subject_uid=220926105&w=AAAmEwYcFAO3VOpQ2StEPGlDeYziIjB53ENdslIVQNNvUQ'),(70,NULL,4,12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,NULL,2,25,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,NULL,6,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,NULL,3,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,NULL,3,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,NULL,6,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(76,NULL,1,19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,NULL,6,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(78,NULL,2,9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,NULL,4,19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,NULL,4,5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,NULL,2,8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,NULL,5,10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,NULL,4,7,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,NULL,5,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,NULL,1,28,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(86,NULL,3,11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(87,NULL,4,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(88,NULL,3,8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(89,NULL,6,29,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90,7,3,12,4,100,14,24,20,1,'2017-05-18 00:00:00',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `captura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cofre`
--

DROP TABLE IF EXISTS `cofre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cofre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `temporizador` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cofre`
--

LOCK TABLES `cofre` WRITE;
/*!40000 ALTER TABLE `cofre` DISABLE KEYS */;
/*!40000 ALTER TABLE `cofre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cofreusuario`
--

DROP TABLE IF EXISTS `cofreusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cofreusuario` (
  `idusuarioss` int(11) NOT NULL,
  `idcofre` int(11) DEFAULT NULL,
  PRIMARY KEY (`idusuarioss`),
  KEY `idcofre_idx` (`idcofre`),
  CONSTRAINT `idcofre` FOREIGN KEY (`idcofre`) REFERENCES `cofre` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `idusuarioss` FOREIGN KEY (`idusuarioss`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cofreusuario`
--

LOCK TABLES `cofreusuario` WRITE;
/*!40000 ALTER TABLE `cofreusuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `cofreusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etakemon`
--

DROP TABLE IF EXISTS `etakemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `etakemon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `habilidad` varchar(45) DEFAULT NULL,
  `tipo` int(11) NOT NULL,
  `imagen` varchar(200) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etakemon`
--

LOCK TABLES `etakemon` WRITE;
/*!40000 ALTER TABLE `etakemon` DISABLE KEYS */;
INSERT INTO `etakemon` VALUES (1,'Alakasals','Hipnosis',1,'https://www.dropbox.com/pri/get/eetakemon_photos/Alakasals.png?_subject_uid=220926105&w=AAB8iY7FS_N7lsV9lIj7IfThWm27vdrBsFcd6sxC56oIRQ','Humanoide con poderes extrasensoriales, tiene un tono de voz pausado y constante. Dice la profecía que es capaz de crimpar RJ45 con el poder de su mente'),(2,'Danisloth','Indiferencia',2,'https://www.dropbox.com/pri/get/eetakemon_photos/Danisloth.png?_subject_uid=220926105&w=AAAmEwYcFAO3VOpQ2StEPGlDeYziIjB53ENdslIVQNNvUQ','Es una criatura lenta y sosegada, se pasa el día comiendo y durmiendo. Le cuesta aprender ataques nuevos, a veces no entiende las cosas, paciencia entrenadores.'),(3,'Davidos','Restauración',2,'https://www.dropbox.com/pri/get/eetakemon_photos/Davidos.png?_subject_uid=220926105&w=AADFLmUIUpZNcLQ5QAVFst-vgUGU_ZXbnGnKUkLpG5hFlA','Eetakemon prehistórico de procedencia indefinida. Según los estudios arquelógicos, su capacidad de combate era reducida por culpa de sus cortas extremidades.'),(4,'Livanny','Esfumarse',3,'https://www.dropbox.com/pri/get/eetakemon_photos/Livanny.png?_subject_uid=220926105&w=AAAH5jCqMpna-NA_KzEx85yb-lPSRJO4q9LVGuP-Q96dxw','A simple vista parece una criatura inofensiva pero tiene un carácter volátil e impredecible. Su principal habilidad es camuflarse con las hojas para eludir responsabilidades.'),(5,'Tonix','Lanzaqueries',1,'https://www.dropbox.com/pri/get/eetakemon_photos/Tonix.png?_subject_uid=220926105&w=AADYxu92j9HLuF4Z-OS3lwtNicF0gyCjT4ffZyorIwMsuQ','Eetakemon rocoso y afilado, intimida al resto de rivales con su gesto serio e implacable. Se le atribuyen todas las avalanchas de peticiones producidas en las redes.'),(6,'Wooperal','Trapicheo',3,'https://www.dropbox.com/pri/get/eetakemon_photos/Wooperal.png?_subject_uid=220926105&w=AAAph5l4OJwrnYkR5jLLTYIEuoxgtB_XzsTFQvZUihzVtA','Solo aparece en la zona de Viladecans, es una criatura viscosa y escurridiza. Es de color azul pero su sueño siempre ha sido vestir de blanco.'),(7,'marionsita','quimicaChunga',1,NULL,NULL);
/*!40000 ALTER TABLE `etakemon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localizacion`
--

DROP TABLE IF EXISTS `localizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localizacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `longitud` double NOT NULL,
  `latitud` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localizacion`
--

LOCK TABLES `localizacion` WRITE;
/*!40000 ALTER TABLE `localizacion` DISABLE KEYS */;
INSERT INTO `localizacion` VALUES (1,'EETAC',1.9872137904167175,41.275591139229704),(2,'ESAB',1.986626386642456,41.275742324160106),(3,'Biblioteca',1.9853060692548752,41.27546313571486),(4,'ICFO',1.9895660877227783,41.27559718663362),(5,'ICFO lago',1.9898155331611633,41.27508920274953),(6,'UOC',1.98815256357193,41.27525449952666),(7,'UOC lago',1.9883671402931213,41.27477070290112),(8,'RDIT lago',1.9895526766777039,41.27593987194151),(9,'RDIT campo',1.9887346029281616,41.27611322970612),(10,'Profes secundario',1.987728774547577,41.275871335023865),(11,'Recepcion EETAC',1.987064927816391,41.27551252292746),(12,'Azul pasillo',1.986842304468155,41.27542785911152),(13,'Azul calle',1.986992508172989,41.2750388073074),(14,'Azul calle2',1.9867350161075592,41.27564959744429),(15,'Azul clase',1.9868671149015427,41.27526861020775),(16,'Amarilla pasillo',1.9865472614765167,41.27535932165626),(17,'Amarilla calle',1.9867269694805145,41.27498034854577),(18,'Amarilla calle2',1.98646679520607,41.275585091825185),(19,'Amarilla clase',1.9865848124027252,41.2751899935171),(20,'Roja pasillo',1.9862709939479828,41.27530086318167),(21,'Roja calle',1.9864372909069061,41.274909794798226),(22,'Roja calle2',1.986163705587387,41.275518570338704),(23,'Roja clase',1.9862870872020721,41.27513153489086),(24,'MalezaChunga',1.985454261302948,41.27514967722875),(25,'PingPong',1.9860939681529999,41.27513355070642),(26,'Camino salvaje resi',1.9862200319766998,41.27465983233608),(27,'Resi',1.985192745923996,41.274536866580064),(28,'Resi lago',1.985427439212799,41.27414982531144),(29,'Tierra Salvaje1',1.9846925139427185,41.27523030978055),(30,'Tierra Salvaje2',1.984831988811493,41.274948095413485),(31,'Huerto Chungo',1.9883403182029724,41.276359155047444);
/*!40000 ALTER TABLE `localizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logros`
--

DROP TABLE IF EXISTS `logros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logros` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `experiencia` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logros`
--

LOCK TABLES `logros` WRITE;
/*!40000 ALTER TABLE `logros` DISABLE KEYS */;
INSERT INTO `logros` VALUES (22,'etakeColector','captura 10 etakemons',100),(23,'etakeColector platino','captura 20 etakemons',200),(24,'etakeColector gold','captura 50 etakemons',300),(25,'etakeAtakker','Libra una batalla etakemon',50),(26,'etakeAtakker malote','gana 5 batallas',200),(27,'etakeAtakker destroyer','gana 20 batallas',500),(28,'etakeColector tipo1','captura 5 etakemons tipo 1',100),(29,'etakeColector tipo1 platino','captura 10 etakemons tipo 1',200),(30,'etakeColector tipo1 gold','captura 20 etakemons tipo 1',500),(31,'etakeColector tipo2','captura 5 etakemons tipo 2',100),(32,'etakeColector tipo2 platino','captura 10 etakemons tipo 2',200),(33,'etakeColector tipo2 gold','captura 20 etakemons tipo 2',500),(34,'etakeColector tipo3','captura 5 etakemons tipo 3',100),(35,'etakeColector tipo3 platino','captura 10 etakemons tipo 3',200),(36,'etakeColector tipo3 gold','captura 20 etakemons tipo 3',500),(37,'etakeKedex','captura todos los etakemons',1000),(38,'LanzaEtakeballs','Captura tu primer etakemon',100);
/*!40000 ALTER TABLE `logros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logrosusuario`
--

DROP TABLE IF EXISTS `logrosusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logrosusuario` (
  `idlogros` int(11) DEFAULT NULL,
  `idusuarios` int(11) DEFAULT NULL,
  KEY `idlogro_idx` (`idlogros`),
  KEY `idusuario_idx` (`idusuarios`),
  CONSTRAINT `idlogros` FOREIGN KEY (`idlogros`) REFERENCES `logros` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `idusuarios` FOREIGN KEY (`idusuarios`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logrosusuario`
--

LOCK TABLES `logrosusuario` WRITE;
/*!40000 ALTER TABLE `logrosusuario` DISABLE KEYS */;
INSERT INTO `logrosusuario` VALUES (22,7),(22,8),(22,9);
/*!40000 ALTER TABLE `logrosusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetos`
--

DROP TABLE IF EXISTS `objetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objetos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(70) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetos`
--

LOCK TABLES `objetos` WRITE;
/*!40000 ALTER TABLE `objetos` DISABLE KEYS */;
INSERT INTO `objetos` VALUES (3,'etakeball','úsala para atrapar a los etakemons salvajes'),(4,'revivir','trae de vuelta a tu etakemon'),(5,'pocion1','cura a tu etakemon'),(6,'pocion2','cura media vida a tu etakemon'),(7,'pocion3','cura al maximo a tu etakemon');
/*!40000 ALTER TABLE `objetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetosusuario`
--

DROP TABLE IF EXISTS `objetosusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objetosusuario` (
  `idobjeto` int(11) NOT NULL,
  `idusuario` int(11) NOT NULL,
  KEY `idusuario_idx` (`idusuario`),
  KEY `idobjetos_idx` (`idobjeto`),
  CONSTRAINT `idobjetos` FOREIGN KEY (`idobjeto`) REFERENCES `objetos` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `idusuario` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetosusuario`
--

LOCK TABLES `objetosusuario` WRITE;
/*!40000 ALTER TABLE `objetosusuario` DISABLE KEYS */;
INSERT INTO `objetosusuario` VALUES (3,7),(3,7),(3,7),(3,7),(3,7),(3,7),(4,7),(4,7),(4,7),(4,7),(4,7),(7,7),(7,7),(7,7),(3,8),(3,8),(3,8),(3,8),(3,8),(3,8),(4,8),(4,8),(4,8),(4,8),(4,8),(7,8),(7,8),(3,9),(3,9),(3,9),(3,9),(3,9),(3,9),(4,9),(4,9),(4,9),(4,9),(4,9),(7,9),(7,9),(7,9);
/*!40000 ALTER TABLE `objetosusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `nick` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `nivel` int(11) DEFAULT NULL,
  `experiencia` int(11) DEFAULT NULL,
  `modified` int(11) NOT NULL,
  `admin` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idusuario_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (7,'Ivan','sweex','munozloisivan@gmail.com','password',6,70,0,1),(8,'Roberto','DomingueroRob','roberto.arranz.93@gmail.com','password',5,100,0,1),(9,'Daniel','Sob','danimb93@gmail.com','password',5,100,0,1),(10,'Mariona','marionn3','rovira.caliz@gmail.com','password',3,50,0,0),(11,'Marc Mansilla','Marcc','marc_mansilla@gmail.com','password',4,150,1,0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-04 12:31:21
