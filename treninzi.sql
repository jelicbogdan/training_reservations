/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`treninzi` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `treninzi`;



DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Administrator`(`AdministratorID`,`Ime`,`Prezime`,`Username`,`Password`) VALUES 
(1,'Bogdan','Jelic','bogdan','bogdan123'),
(2,'Uros','Smiljanic','uros','uros123');




DROP TABLE IF EXISTS `Klijent`;

CREATE TABLE `Klijent` (
  `KlijentID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ImeKlijenta` VARCHAR(30) NOT NULL,
  `PrezimeKlijenta` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`KlijentID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Klijent` VALUES 
(1,'Hristina', 'Cirovic', 'hris@gmail.com', '0631231234'),
(2,'Dragan', 'Lambulic', 'fahret@gmail.com', '0654645434'),
(3,'Ognjen', 'Grujic', 'ognjen@gmail.com', '0641235153');

DROP TABLE IF EXISTS `Trener`;

CREATE TABLE `Trener` (
  `TrenerID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ImeTrenera` VARCHAR(30) NOT NULL,
  `PrezimeTrenera` VARCHAR(30) NOT NULL,
  `GodineIskustva` INT(7) NOT NULL,
  `OpisIskustva` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`TrenerID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Trener` VALUES 
(1,'James', 'Huston', 20, 'Specijalizovan za tezinske treninge.'),
(2,'Merry', 'Smith', 10, 'Specijalizovana za HIIT.'),
(3,'Mike', 'Tyson', 30, 'Specijalizovan za borilacke sportove.');



DROP TABLE IF EXISTS `Sala`;

CREATE TABLE `Sala` (
  `SalaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivSale` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`SalaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Sala` VALUES 
(1,'A109'),
(2,'B020'),
(3,'111'),
(4,'005'),
(5,'006');


DROP TABLE IF EXISTS `VrstaTreninga`;

CREATE TABLE `VrstaTreninga` (
  `VrstaTreningaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `NazivVrsteTreninga` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`VrstaTreningaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `VrstaTreninga` VALUES 
(1,'Bodybuilding'),
(2,'HIIT'),
(3,'Boxing');




DROP TABLE IF EXISTS `Trening`;

CREATE TABLE `Trening` (
  `TreningID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVremePocetka` DATETIME NOT NULL,
  `OpisTreninga` VARCHAR(200) NOT NULL,
  `TrenerID` BIGINT(10) UNSIGNED NOT NULL,
  `SalaID` BIGINT(10) UNSIGNED NOT NULL,
  `VrstaTreningaID` BIGINT(10) UNSIGNED NOT NULL,
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TreningID`),
  CONSTRAINT `fk_trener_id` FOREIGN KEY (`TrenerID`) REFERENCES `Trener` (`TrenerID`),
  CONSTRAINT `fk_sala_id` FOREIGN KEY (`SalaID`) REFERENCES `Sala` (`SalaID`),
  CONSTRAINT `fk_vrstaTreninga_id` FOREIGN KEY (`VrstaTreningaID`) REFERENCES `VrstaTreninga` (`VrstaTreningaID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



INSERT  INTO `Trening` VALUES 
(1,'2022-06-06 18:00:00','HIIT trening.',2,1,2,1);


DROP TABLE IF EXISTS `Vezbac`;

CREATE TABLE `Vezbac` (
  `TreningID` BIGINT(10) UNSIGNED NOT NULL,
  `RbVezbaca` INT(7) NOT NULL,
  `Napomena` VARCHAR(200) COLLATE utf8_unicode_ci NOT NULL,
  `KlijentID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`TreningID`,`RbVezbaca`),
  CONSTRAINT `fk_trening_id` FOREIGN KEY (`TreningID`) REFERENCES `Trening` (`TreningID`) ON DELETE CASCADE,
  CONSTRAINT `fk_klijent_id` FOREIGN KEY (`KlijentID`) REFERENCES `Klijent` (`KlijentID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Vezbac` VALUES 
(1,1,'Pocetnik, malo lakse s njom.',1),
(1,2,'Napredan, kidajte ga.',3);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
