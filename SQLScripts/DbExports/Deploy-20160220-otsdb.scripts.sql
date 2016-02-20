-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for otsdb
DROP DATABASE IF EXISTS `otsdb`;
CREATE DATABASE IF NOT EXISTS `otsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `otsdb`;


-- Dumping structure for table otsdb.academiccourse
DROP TABLE IF EXISTS `academiccourse`;
CREATE TABLE IF NOT EXISTS `academiccourse` (
  `CourseTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Number` varchar(15) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CourseTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.academiccourse: ~2 rows (approximately)
/*!40000 ALTER TABLE `academiccourse` DISABLE KEYS */;
INSERT INTO `academiccourse` (`CourseTypeId`, `Number`, `Name`, `Description`) VALUES
	(1, '100', 'Introduction to Botany', NULL),
	(2, '101', 'Advance Level Art', NULL),
	(5, '400', 'Health Science', NULL);
/*!40000 ALTER TABLE `academiccourse` ENABLE KEYS */;


-- Dumping structure for table otsdb.answer
DROP TABLE IF EXISTS `answer`;
CREATE TABLE IF NOT EXISTS `answer` (
  `AnswerId` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(255) DEFAULT NULL,
  `Question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`AnswerId`),
  KEY `Question_id` (`Question_id`),
  CONSTRAINT `FK2644B9D2989C4BF` FOREIGN KEY (`Question_id`) REFERENCES `question` (`QuestionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.answer: ~0 rows (approximately)
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;


-- Dumping structure for table otsdb.cognitiveleveltype
DROP TABLE IF EXISTS `cognitiveleveltype`;
CREATE TABLE IF NOT EXISTS `cognitiveleveltype` (
  `CognitiveLevel` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CognitiveLevel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.cognitiveleveltype: ~4 rows (approximately)
/*!40000 ALTER TABLE `cognitiveleveltype` DISABLE KEYS */;
INSERT INTO `cognitiveleveltype` (`CognitiveLevel`, `Name`, `Description`) VALUES
	(1, 'List', NULL),
	(2, 'Describe', NULL),
	(3, 'Summarize', NULL),
	(4, 'Classify', NULL);
/*!40000 ALTER TABLE `cognitiveleveltype` ENABLE KEYS */;


-- Dumping structure for table otsdb.courseassignment
DROP TABLE IF EXISTS `courseassignment`;
CREATE TABLE IF NOT EXISTS `courseassignment` (
  `TeacherId` int(11) NOT NULL,
  `CourseId` int(11) NOT NULL,
  `AssignOn` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `IsCompleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`TeacherId`,`CourseId`),
  KEY `CourseId` (`CourseId`),
  CONSTRAINT `FK279C913C4562DB52` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.courseassignment: ~2 rows (approximately)
/*!40000 ALTER TABLE `courseassignment` DISABLE KEYS */;
INSERT INTO `courseassignment` (`TeacherId`, `CourseId`, `AssignOn`, `EndDate`, `IsCompleted`) VALUES
	(1, 1, '2014-09-30 21:18:08', '0001-01-01 00:00:00', 0),
	(1, 2, '2014-09-30 21:18:08', '0001-01-01 00:00:00', 0),
	(2, 5, '2016-02-20 10:40:15', NULL, 0);
/*!40000 ALTER TABLE `courseassignment` ENABLE KEYS */;


-- Dumping structure for table otsdb.courseknowledgemap
DROP TABLE IF EXISTS `courseknowledgemap`;
CREATE TABLE IF NOT EXISTS `courseknowledgemap` (
  `CourseId` int(11) NOT NULL,
  `KnowledgeMapId` int(11) NOT NULL,
  `AssignBy` int(11) DEFAULT NULL,
  `AssignOn` datetime DEFAULT NULL,
  `ActionText` varchar(100) DEFAULT NULL,
  `CanEnableSelect` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`CourseId`,`KnowledgeMapId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.courseknowledgemap: ~14 rows (approximately)
/*!40000 ALTER TABLE `courseknowledgemap` DISABLE KEYS */;
INSERT INTO `courseknowledgemap` (`CourseId`, `KnowledgeMapId`, `AssignBy`, `AssignOn`, `ActionText`, `CanEnableSelect`) VALUES
	(1, 3, 1, '2015-09-28 09:58:17', 'Select', 1),
	(1, 5, 1, '2014-10-12 09:19:14', 'Select', 1),
	(1, 7, 2, '2016-02-14 10:49:02', 'Selected', 0),
	(1, 8, 2, '2016-02-14 11:25:33', 'Selected', 0),
	(1, 82, 1, '2014-10-11 19:39:55', 'Select', 1),
	(1, 83, 1, '2014-10-12 09:19:16', 'Select', 1),
	(1, 84, 1, '2014-10-12 09:32:06', 'Select', 1),
	(2, 3, 1, '2016-02-15 08:18:37', 'Selected', 0),
	(2, 4, 1, '2016-02-13 12:52:07', 'Selected', 0),
	(2, 37, 1, '2014-10-11 17:51:30', 'Select', 1),
	(2, 38, 1, '2014-10-11 17:51:24', 'Select', 1),
	(2, 57, 1, '2014-10-11 19:41:32', 'Select', 1),
	(2, 58, 1, '2014-10-11 19:41:33', 'Select', 1),
	(2, 75, 1, '2014-10-11 19:41:36', 'Select', 1),
	(5, 9, 2, '2016-02-20 10:44:37', 'Selected', 0),
	(5, 10, 2, '2016-02-20 10:44:38', 'Selected', 0);
/*!40000 ALTER TABLE `courseknowledgemap` ENABLE KEYS */;


-- Dumping structure for table otsdb.knowledgemap
DROP TABLE IF EXISTS `knowledgemap`;
CREATE TABLE IF NOT EXISTS `knowledgemap` (
  `KnowledgeMapId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `CreateOn` datetime NOT NULL,
  `Concepts` text,
  `LastUpdated` datetime DEFAULT NULL,
  `IsPublic` tinyint(1) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`KnowledgeMapId`),
  KEY `CreatedBy` (`CreatedBy`),
  CONSTRAINT `FK4416245C78287B0` FOREIGN KEY (`CreatedBy`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.knowledgemap: ~5 rows (approximately)
/*!40000 ALTER TABLE `knowledgemap` DISABLE KEYS */;
INSERT INTO `knowledgemap` (`KnowledgeMapId`, `Name`, `Description`, `CreateOn`, `Concepts`, `LastUpdated`, `IsPublic`, `CreatedBy`) VALUES
	(3, 'Plant', 'Plant Concept', '2014-07-09 08:10:44', '<children>\n  <child>\n    <id>465089421</id>\n    <rootid>3</rootid>\n    <identity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</identity>\n    <parentid>3</parentid>\n    <label>Root</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>47045517-cedd-44e4-b5a5-597aa569d6b7</id>\n        <rootid>3</rootid>\n        <parentidentity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f1130a8b-9fc9-4d85-a9e0-d43894d590f5</id>\n        <rootid>3</rootid>\n        <parentidentity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>absorb</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>1835048167</id>\n    <rootid>3</rootid>\n    <identity>85e91d1c-2a4a-4e43-9698-7c5977824364</identity>\n    <parentid>3</parentid>\n    <label>Steam</label>\n    <children>\n      <id>760293952</id>\n      <rootid>3</rootid>\n      <identity>dfbf5a30-9b47-42db-b214-96abe14321fc</identity>\n      <parentid>1835048167</parentid>\n      <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n      <label>Sprout</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>dae6517a-3d17-4607-946e-f610da9dfa0a</id>\n        <rootid>3</rootid>\n        <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>d52640e1-3ac8-4b39-acbb-b42cf6a3608a</id>\n        <rootid>3</rootid>\n        <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>transport</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>866745648</id>\n    <rootid>3</rootid>\n    <identity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</identity>\n    <parentid>3</parentid>\n    <label>Leaf</label>\n    <children>\n      <id>771245702</id>\n      <rootid>3</rootid>\n      <identity>ccbcdd3b-aae9-4020-8850-e4e8a9e6fdeb</identity>\n      <parentid>866745648</parentid>\n      <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n      <label>Shape</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <children>\n      <id>1960298587</id>\n      <rootid>3</rootid>\n      <identity>f337f7b5-9a0f-4858-abba-f3f78232a186</identity>\n      <parentid>866745648</parentid>\n      <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n      <label>Margin</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a517cb20-dc3f-422e-bb63-4fa41d7bede3</id>\n        <rootid>3</rootid>\n        <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>a60e835e-5cc8-49e7-8fbb-002cb0a226b0</id>\n        <rootid>3</rootid>\n        <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>evaporate</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n</children>', '2014-07-09 08:28:34', 1, 1),
	(4, 'Garden', 'Garden Concept', '2014-07-09 08:30:29', '<children>\n  <child>\n    <id>770196883</id>\n    <rootid>4</rootid>\n    <identity>40d1b808-c054-477e-acd1-c0d7f101b2fa</identity>\n    <parentid>4</parentid>\n    <label>Taiwan Cherry</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a357d2d3-3bcc-460f-911b-735854a9634b</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Aristate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>ebe8eef4-3295-4fce-8139-29a5e78c38da</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f4ada86f-b3a8-49bb-b467-6a9c0957ae1a</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Serrate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>72b3a8b4-05e6-4b99-83f2-73a1a1258af3</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arragement</attributename>\n        <attributevalue>Alternate</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>400134451</id>\n    <rootid>4</rootid>\n    <identity>949edac4-2518-4112-8763-fb5408feeca3</identity>\n    <parentid>4</parentid>\n    <label>Rhododendron</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a384613d-ba83-4421-b07a-01a8d2f5ba31</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Obtuse</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>ae09ed43-4ad3-4291-b531-a85557e16c5c</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>993339bf-a4f9-45e8-b807-f490eb534f75</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Entire</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f2a6a298-5d61-4bae-a821-0cfd1f7ea03b</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arrangement</attributename>\n        <attributevalue>Alternate</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>103604509</id>\n    <rootid>4</rootid>\n    <identity>927a05fc-1853-4bb6-a397-dd11c1c2c082</identity>\n    <parentid>4</parentid>\n    <label>Golden Dedron</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>7a9599af-c8b0-48db-a818-314ba3d60cfa</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Aristate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>688ee6d8-b882-4878-b79f-3ef698798f94</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>42f4d8ee-cc26-448f-a422-8111f767d289</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Entire</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>98dadb8b-2c2b-4f21-96e4-7dd14c94bd1b</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arrangement</attributename>\n        <attributevalue>Opposite</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>1836452959</id>\n    <rootid>4</rootid>\n    <identity>d330c54d-4397-462b-9a72-081c1e06a44c</identity>\n    <parentid>4</parentid>\n    <label>BroadLeaf Palm</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>601728ee-5c52-497c-93b3-7fe0043d87b1</id>\n        <rootid>4</rootid>\n        <parentidentity>d330c54d-4397-462b-9a72-081c1e06a44c</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue> Parallel</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n</children>', '2016-02-15 07:46:46', 1, 1),
	(5, '水果 (fruit)', '水果概念 (Fruit Concept)', '2016-02-13 20:26:24', '<children>\n  <child>\n    <id>931447457</id>\n    <rootid>5</rootid>\n    <identity>fed7b756-2bbb-4bef-b65c-dd9a3144f244</identity>\n    <parentid>5</parentid>\n    <label>芒果  (Mango)</label>\n    <relationtype>TypeOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>80db28dc-7ee4-4a53-a15f-142e09564f6a</id>\n        <rootid>5</rootid>\n        <parentidentity>fed7b756-2bbb-4bef-b65c-dd9a3144f244</parentidentity>\n        <relationname>是 (is)</relationname>\n        <conceptname>水果 (fruit)</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>471297496</id>\n    <rootid>5</rootid>\n    <identity>5b9a0ae8-d8c1-4d11-a712-0c70bbb8e770</identity>\n    <parentid>5</parentid>\n    <label>橙 (Orange)</label>\n    <relationtype>TypeOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>3fbdfa94-7029-4f41-80ff-67849ea2ab5c</id>\n        <rootid>5</rootid>\n        <parentidentity>5b9a0ae8-d8c1-4d11-a712-0c70bbb8e770</parentidentity>\n        <relationname>是 (is)</relationname>\n        <conceptname>水果 (fruit)</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n</children>', NULL, 1, 1),
	(9, 'Plant', 'Plant Concept', '2016-02-20 10:42:50', '<children>\n  <child>\n    <id>465089421</id>\n    <rootid>3</rootid>\n    <identity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</identity>\n    <parentid>3</parentid>\n    <label>Root</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>47045517-cedd-44e4-b5a5-597aa569d6b7</id>\n        <rootid>3</rootid>\n        <parentidentity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f1130a8b-9fc9-4d85-a9e0-d43894d590f5</id>\n        <rootid>3</rootid>\n        <parentidentity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>absorb</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>1835048167</id>\n    <rootid>3</rootid>\n    <identity>85e91d1c-2a4a-4e43-9698-7c5977824364</identity>\n    <parentid>3</parentid>\n    <label>Steam</label>\n    <children>\n      <id>760293952</id>\n      <rootid>3</rootid>\n      <identity>dfbf5a30-9b47-42db-b214-96abe14321fc</identity>\n      <parentid>1835048167</parentid>\n      <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n      <label>Sprout</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>dae6517a-3d17-4607-946e-f610da9dfa0a</id>\n        <rootid>3</rootid>\n        <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>d52640e1-3ac8-4b39-acbb-b42cf6a3608a</id>\n        <rootid>3</rootid>\n        <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>transport</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>866745648</id>\n    <rootid>3</rootid>\n    <identity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</identity>\n    <parentid>3</parentid>\n    <label>Leaf</label>\n    <children>\n      <id>771245702</id>\n      <rootid>3</rootid>\n      <identity>ccbcdd3b-aae9-4020-8850-e4e8a9e6fdeb</identity>\n      <parentid>866745648</parentid>\n      <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n      <label>Shape</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <children>\n      <id>1960298587</id>\n      <rootid>3</rootid>\n      <identity>f337f7b5-9a0f-4858-abba-f3f78232a186</identity>\n      <parentid>866745648</parentid>\n      <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n      <label>Margin</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a517cb20-dc3f-422e-bb63-4fa41d7bede3</id>\n        <rootid>3</rootid>\n        <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>a60e835e-5cc8-49e7-8fbb-002cb0a226b0</id>\n        <rootid>3</rootid>\n        <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>evaporate</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n</children>', '2016-02-20 10:42:50', 0, 2),
	(10, 'Garden', 'Garden Concept', '2016-02-20 10:42:50', '<children>\n  <child>\n    <id>770196883</id>\n    <rootid>4</rootid>\n    <identity>40d1b808-c054-477e-acd1-c0d7f101b2fa</identity>\n    <parentid>4</parentid>\n    <label>Taiwan Cherry</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a357d2d3-3bcc-460f-911b-735854a9634b</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Aristate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>ebe8eef4-3295-4fce-8139-29a5e78c38da</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f4ada86f-b3a8-49bb-b467-6a9c0957ae1a</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Serrate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>72b3a8b4-05e6-4b99-83f2-73a1a1258af3</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arragement</attributename>\n        <attributevalue>Alternate</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>400134451</id>\n    <rootid>4</rootid>\n    <identity>949edac4-2518-4112-8763-fb5408feeca3</identity>\n    <parentid>4</parentid>\n    <label>Rhododendron</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a384613d-ba83-4421-b07a-01a8d2f5ba31</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Obtuse</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>ae09ed43-4ad3-4291-b531-a85557e16c5c</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>993339bf-a4f9-45e8-b807-f490eb534f75</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Entire</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f2a6a298-5d61-4bae-a821-0cfd1f7ea03b</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arrangement</attributename>\n        <attributevalue>Alternate</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>103604509</id>\n    <rootid>4</rootid>\n    <identity>927a05fc-1853-4bb6-a397-dd11c1c2c082</identity>\n    <parentid>4</parentid>\n    <label>Golden Dedron</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>7a9599af-c8b0-48db-a818-314ba3d60cfa</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Aristate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>688ee6d8-b882-4878-b79f-3ef698798f94</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>42f4d8ee-cc26-448f-a422-8111f767d289</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Entire</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>98dadb8b-2c2b-4f21-96e4-7dd14c94bd1b</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arrangement</attributename>\n        <attributevalue>Opposite</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>1836452959</id>\n    <rootid>4</rootid>\n    <identity>d330c54d-4397-462b-9a72-081c1e06a44c</identity>\n    <parentid>4</parentid>\n    <label>BroadLeaf Palm</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>601728ee-5c52-497c-93b3-7fe0043d87b1</id>\n        <rootid>4</rootid>\n        <parentidentity>d330c54d-4397-462b-9a72-081c1e06a44c</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue> Parallel</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n</children>', '2016-02-20 10:42:50', 0, 2);
/*!40000 ALTER TABLE `knowledgemap` ENABLE KEYS */;


-- Dumping structure for table otsdb.question
DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `QuestionId` int(11) NOT NULL AUTO_INCREMENT,
  `Text` text,
  `QuestionTypeId` int(11) DEFAULT NULL,
  `QuestionNatureType_id` int(11) DEFAULT NULL,
  `CognitiveLevelType_id` int(11) DEFAULT NULL,
  `CourseId` int(11) DEFAULT NULL,
  `TestId` int(11) DEFAULT NULL,
  PRIMARY KEY (`QuestionId`),
  KEY `QuestionTypeId` (`QuestionTypeId`),
  KEY `QuestionNatureType_id` (`QuestionNatureType_id`),
  KEY `CognitiveLevelType_id` (`CognitiveLevelType_id`),
  KEY `CourseId` (`CourseId`),
  KEY `TestId` (`TestId`),
  CONSTRAINT `FK1BCDAE6B3E8267BD` FOREIGN KEY (`CognitiveLevelType_id`) REFERENCES `cognitiveleveltype` (`CognitiveLevel`),
  CONSTRAINT `FK1BCDAE6B4562DB52` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`),
  CONSTRAINT `FK1BCDAE6B81C67016` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`),
  CONSTRAINT `FK1BCDAE6BA9464857` FOREIGN KEY (`QuestionTypeId`) REFERENCES `questiontype` (`QuestionType`),
  CONSTRAINT `FK1BCDAE6BCF2183A8` FOREIGN KEY (`QuestionNatureType_id`) REFERENCES `questionnaturetype` (`QuestionNatureType`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.question: ~0 rows (approximately)
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;


-- Dumping structure for table otsdb.questionlineitem
DROP TABLE IF EXISTS `questionlineitem`;
CREATE TABLE IF NOT EXISTS `questionlineitem` (
  `QuestionLineItemId` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(255) DEFAULT NULL,
  `IsCorrect` tinyint(1) DEFAULT NULL,
  `Question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`QuestionLineItemId`),
  KEY `Question_id` (`Question_id`),
  CONSTRAINT `FK2492265C2989C4BF` FOREIGN KEY (`Question_id`) REFERENCES `question` (`QuestionId`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.questionlineitem: ~0 rows (approximately)
/*!40000 ALTER TABLE `questionlineitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionlineitem` ENABLE KEYS */;


-- Dumping structure for table otsdb.questionnaturetype
DROP TABLE IF EXISTS `questionnaturetype`;
CREATE TABLE IF NOT EXISTS `questionnaturetype` (
  `QuestionNatureType` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`QuestionNatureType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.questionnaturetype: ~4 rows (approximately)
/*!40000 ALTER TABLE `questionnaturetype` DISABLE KEYS */;
INSERT INTO `questionnaturetype` (`QuestionNatureType`, `Name`) VALUES
	(1, 'Correct'),
	(2, 'Negative'),
	(3, 'Incorrect'),
	(4, 'Negative-Incorrect');
/*!40000 ALTER TABLE `questionnaturetype` ENABLE KEYS */;


-- Dumping structure for table otsdb.questiontype
DROP TABLE IF EXISTS `questiontype`;
CREATE TABLE IF NOT EXISTS `questiontype` (
  `QuestionType` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`QuestionType`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.questiontype: ~3 rows (approximately)
/*!40000 ALTER TABLE `questiontype` DISABLE KEYS */;
INSERT INTO `questiontype` (`QuestionType`, `Name`) VALUES
	(1, 'TrueOrFalse'),
	(2, 'MultipleChoice-SingleAnswer'),
	(3, 'MultipleChoice-MultipleAnwsers');
/*!40000 ALTER TABLE `questiontype` ENABLE KEYS */;


-- Dumping structure for table otsdb.studentcourseregistration
DROP TABLE IF EXISTS `studentcourseregistration`;
CREATE TABLE IF NOT EXISTS `studentcourseregistration` (
  `StudentCourseId` int(11) NOT NULL AUTO_INCREMENT,
  `Date` datetime DEFAULT NULL,
  `StudentId` int(11) NOT NULL,
  `CourseId` int(11) NOT NULL,
  PRIMARY KEY (`StudentCourseId`),
  KEY `StudentId` (`StudentId`),
  KEY `CourseId` (`CourseId`),
  CONSTRAINT `FK452DB2A94562DB52` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`),
  CONSTRAINT `FK452DB2A964D28624` FOREIGN KEY (`StudentId`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.studentcourseregistration: ~2 rows (approximately)
/*!40000 ALTER TABLE `studentcourseregistration` DISABLE KEYS */;
INSERT INTO `studentcourseregistration` (`StudentCourseId`, `Date`, `StudentId`, `CourseId`) VALUES
	(1, '2015-09-28 11:38:05', 3, 1),
	(2, '2015-11-24 15:27:48', 5, 1),
	(3, '2016-02-20 10:48:08', 3, 5),
	(4, '2016-02-20 10:52:26', 5, 5);
/*!40000 ALTER TABLE `studentcourseregistration` ENABLE KEYS */;


-- Dumping structure for table otsdb.studenttest
DROP TABLE IF EXISTS `studenttest`;
CREATE TABLE IF NOT EXISTS `studenttest` (
  `StudentTestId` int(11) NOT NULL AUTO_INCREMENT,
  `Mark` float DEFAULT NULL,
  `Grade` varchar(255) DEFAULT NULL,
  `IsTestCompleted` tinyint(1) DEFAULT NULL,
  `DateCompleted` datetime DEFAULT NULL,
  `StudentId` int(11) DEFAULT NULL,
  `CourseId` int(11) DEFAULT NULL,
  `TestId` int(11) DEFAULT NULL,
  PRIMARY KEY (`StudentTestId`),
  KEY `StudentId` (`StudentId`),
  KEY `CourseId` (`CourseId`),
  KEY `TestId` (`TestId`),
  CONSTRAINT `FKA65C85E94562DB52` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`),
  CONSTRAINT `FKA65C85E964D28624` FOREIGN KEY (`StudentId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `FKA65C85E981C67016` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.studenttest: ~0 rows (approximately)
/*!40000 ALTER TABLE `studenttest` DISABLE KEYS */;
/*!40000 ALTER TABLE `studenttest` ENABLE KEYS */;


-- Dumping structure for table otsdb.studenttestanswersheet
DROP TABLE IF EXISTS `studenttestanswersheet`;
CREATE TABLE IF NOT EXISTS `studenttestanswersheet` (
  `StudentTestAnswerSheetId` int(11) NOT NULL AUTO_INCREMENT,
  `A` tinyint(1) DEFAULT NULL,
  `B` tinyint(1) DEFAULT NULL,
  `C` tinyint(1) DEFAULT NULL,
  `D` tinyint(1) DEFAULT NULL,
  `IsCorrect` tinyint(1) DEFAULT NULL,
  `TotalCorrectAnswers` int(11) DEFAULT '0',
  `StudentId` int(11) NOT NULL,
  `TestItemId` int(11) NOT NULL,
  `TestId` int(11) NOT NULL,
  PRIMARY KEY (`StudentTestAnswerSheetId`),
  KEY `StudentId` (`StudentId`),
  KEY `TestItemId` (`TestItemId`),
  KEY `TestId` (`TestId`),
  CONSTRAINT `FKC5C874CB64D28624` FOREIGN KEY (`StudentId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `FKC5C874CB81C67016` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`),
  CONSTRAINT `FKC5C874CBE90F62C2` FOREIGN KEY (`TestItemId`) REFERENCES `testitem` (`TestItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.studenttestanswersheet: ~0 rows (approximately)
/*!40000 ALTER TABLE `studenttestanswersheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `studenttestanswersheet` ENABLE KEYS */;


-- Dumping structure for table otsdb.studenttesthistory
DROP TABLE IF EXISTS `studenttesthistory`;
CREATE TABLE IF NOT EXISTS `studenttesthistory` (
  `StudentTestHistoryId` int(11) NOT NULL AUTO_INCREMENT,
  `TestId` int(11) DEFAULT NULL,
  `StudentId` int(11) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `TotalMark` float DEFAULT NULL,
  PRIMARY KEY (`StudentTestHistoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.studenttesthistory: ~0 rows (approximately)
/*!40000 ALTER TABLE `studenttesthistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `studenttesthistory` ENABLE KEYS */;


-- Dumping structure for table otsdb.teachercoursetest
DROP TABLE IF EXISTS `teachercoursetest`;
CREATE TABLE IF NOT EXISTS `teachercoursetest` (
  `CourseTestId` int(11) NOT NULL AUTO_INCREMENT,
  `CreatedOn` datetime DEFAULT NULL,
  `CourseId` int(11) NOT NULL,
  `TestId` int(11) NOT NULL,
  `TeacherId` int(11) NOT NULL,
  PRIMARY KEY (`CourseTestId`),
  KEY `CourseId` (`CourseId`),
  KEY `TestId` (`TestId`),
  KEY `TeacherId` (`TeacherId`),
  CONSTRAINT `FK389946CC4562DB52` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`),
  CONSTRAINT `FK389946CC6002D0D1` FOREIGN KEY (`TeacherId`) REFERENCES `user` (`UserId`),
  CONSTRAINT `FK389946CC81C67016` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.teachercoursetest: ~0 rows (approximately)
/*!40000 ALTER TABLE `teachercoursetest` DISABLE KEYS */;
/*!40000 ALTER TABLE `teachercoursetest` ENABLE KEYS */;


-- Dumping structure for table otsdb.test
DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `TestId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `TotalMark` float DEFAULT NULL,
  `NumberOfQuestion` int(11) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `StartTime` varchar(50) DEFAULT NULL,
  `EndTime` varchar(50) DEFAULT NULL,
  `IsActivated` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`TestId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.test: ~0 rows (approximately)
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;


-- Dumping structure for table otsdb.testanswersheet
DROP TABLE IF EXISTS `testanswersheet`;
CREATE TABLE IF NOT EXISTS `testanswersheet` (
  `TestAnswerSheetId` int(11) NOT NULL AUTO_INCREMENT,
  `LineNumber` int(11) DEFAULT NULL,
  `A` tinyint(1) DEFAULT NULL,
  `B` tinyint(1) DEFAULT NULL,
  `C` tinyint(1) DEFAULT NULL,
  `D` tinyint(1) DEFAULT NULL,
  `TestId` int(11) DEFAULT NULL,
  `TestItemId` int(11) DEFAULT NULL,
  PRIMARY KEY (`TestAnswerSheetId`),
  KEY `TestId` (`TestId`),
  KEY `TestItemId` (`TestItemId`),
  CONSTRAINT `FKD0846DE081C67016` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`),
  CONSTRAINT `FKD0846DE0E90F62C2` FOREIGN KEY (`TestItemId`) REFERENCES `testitem` (`TestItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.testanswersheet: ~0 rows (approximately)
/*!40000 ALTER TABLE `testanswersheet` DISABLE KEYS */;
/*!40000 ALTER TABLE `testanswersheet` ENABLE KEYS */;


-- Dumping structure for table otsdb.testitem
DROP TABLE IF EXISTS `testitem`;
CREATE TABLE IF NOT EXISTS `testitem` (
  `TestItemId` int(11) NOT NULL AUTO_INCREMENT,
  `QuestionBankId` int(11) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  `Mark` float DEFAULT NULL,
  `CognitiveLevelTypeId` int(11) DEFAULT NULL,
  `QuestionNatureType` int(11) DEFAULT NULL,
  `QuestionType` int(11) DEFAULT NULL,
  `CourseId` int(11) DEFAULT NULL,
  `Test_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`TestItemId`),
  KEY `CognitiveLevelTypeId` (`CognitiveLevelTypeId`),
  KEY `QuestionNatureType` (`QuestionNatureType`),
  KEY `QuestionType` (`QuestionType`),
  KEY `CourseId` (`CourseId`),
  KEY `Test_id` (`Test_id`),
  CONSTRAINT `FKBB0E3B8B22D2888B` FOREIGN KEY (`CognitiveLevelTypeId`) REFERENCES `cognitiveleveltype` (`CognitiveLevel`),
  CONSTRAINT `FKBB0E3B8B4562DB52` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`),
  CONSTRAINT `FKBB0E3B8B4EDF946E` FOREIGN KEY (`Test_id`) REFERENCES `test` (`TestId`),
  CONSTRAINT `FKBB0E3B8B805A34E4` FOREIGN KEY (`QuestionNatureType`) REFERENCES `questionnaturetype` (`QuestionNatureType`),
  CONSTRAINT `FKBB0E3B8BA06C761D` FOREIGN KEY (`QuestionType`) REFERENCES `questiontype` (`QuestionType`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.testitem: ~0 rows (approximately)
/*!40000 ALTER TABLE `testitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `testitem` ENABLE KEYS */;


-- Dumping structure for table otsdb.testitemoption
DROP TABLE IF EXISTS `testitemoption`;
CREATE TABLE IF NOT EXISTS `testitemoption` (
  `TestItemOptionId` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(255) DEFAULT NULL,
  `IsCorrect` tinyint(1) DEFAULT NULL,
  `TestItem_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`TestItemOptionId`),
  KEY `TestItem_id` (`TestItem_id`),
  CONSTRAINT `FK91023753A3FAB5A2` FOREIGN KEY (`TestItem_id`) REFERENCES `testitem` (`TestItemId`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.testitemoption: ~0 rows (approximately)
/*!40000 ALTER TABLE `testitemoption` DISABLE KEYS */;
/*!40000 ALTER TABLE `testitemoption` ENABLE KEYS */;


-- Dumping structure for table otsdb.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `UserTypeId` int(11) DEFAULT NULL,
  `UserAccountId` int(11) NOT NULL,
  `FirstName` varchar(60) NOT NULL,
  `LastName` varchar(60) NOT NULL,
  `Number` varchar(50) DEFAULT NULL,
  `Street` varchar(100) DEFAULT NULL,
  `City` varchar(60) DEFAULT NULL,
  `Province` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `UserTypeId` (`UserTypeId`),
  KEY `UserAccountId` (`UserAccountId`),
  CONSTRAINT `FK7185C17C79C572D0` FOREIGN KEY (`UserAccountId`) REFERENCES `useraccount` (`UserAccountId`),
  CONSTRAINT `FK7185C17CFA5FD70D` FOREIGN KEY (`UserTypeId`) REFERENCES `usertype` (`UserTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.user: ~6 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`UserId`, `Email`, `Phone`, `UserTypeId`, `UserAccountId`, `FirstName`, `LastName`, `Number`, `Street`, `City`, `Province`) VALUES
	(1, NULL, NULL, 3, 1, 'Maiga', 'Chang', NULL, NULL, NULL, NULL),
	(2, 'ea@ad.ca', '4038987654', 3, 2, 'Ebenezer', 'Aggrey', NULL, NULL, NULL, NULL),
	(3, 's@ad.ca', '89088768', 2, 3, 'Robert ', 'Lee', NULL, NULL, NULL, NULL),
	(4, NULL, NULL, 1, 4, 'Robert', 'Jones', NULL, NULL, NULL, NULL),
	(5, 'jc@ac.ca', '4039808767', 2, 5, 'Johnny', 'Cash', NULL, NULL, NULL, NULL),
	(10, 'rk@ad.ca', '403897654', 3, 10, 'Rita', 'Kuo', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table otsdb.useraccount
DROP TABLE IF EXISTS `useraccount`;
CREATE TABLE IF NOT EXISTS `useraccount` (
  `UserAccountId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `IsLocked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`UserAccountId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.useraccount: ~6 rows (approximately)
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` (`UserAccountId`, `UserName`, `Password`, `IsLocked`) VALUES
	(1, 'maiga', 'maiga', 0),
	(2, 'eb', 'eb', 0),
	(3, 's', 's', 0),
	(4, 'a', 'a', 0),
	(5, 's1', 's1', 0),
	(10, 'rk@ad.ca', 'rk', 0);
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;


-- Dumping structure for table otsdb.usertype
DROP TABLE IF EXISTS `usertype`;
CREATE TABLE IF NOT EXISTS `usertype` (
  `UserTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `HomePageName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table otsdb.usertype: ~3 rows (approximately)
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` (`UserTypeId`, `Name`, `HomePageName`) VALUES
	(1, 'Administrator', 'Administrator'),
	(2, 'Student', 'Student'),
	(3, 'Teacher', 'Teacher');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
