
USE `otsdb`;
-- Dumping structure for table otsdb.academiccourse
CREATE TABLE IF NOT EXISTS `academiccourse` (
  `CourseTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Number` varchar(15) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CourseTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



-- Dumping structure for table otsdb.cognitiveleveltype
CREATE TABLE IF NOT EXISTS `cognitiveleveltype` (
  `CognitiveLevel` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CognitiveLevel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.courseassignment
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



-- Dumping structure for table otsdb.courseknowledgemap
CREATE TABLE IF NOT EXISTS `courseknowledgemap` (
  `CourseId` int(11) NOT NULL,
  `KnowledgeMapId` int(11) NOT NULL,
  `AssignBy` int(11) DEFAULT NULL,
  `AssignOn` datetime DEFAULT NULL,
  `ModifyOn` datetime DEFAULT NULL,
  PRIMARY KEY (`CourseId`,`KnowledgeMapId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- Dumping structure for table otsdb.courseregistration
CREATE TABLE IF NOT EXISTS `courseregistration` (
  `StudentId` int(11) NOT NULL,
  `CourseId` int(11) NOT NULL,
  `Date` datetime DEFAULT NULL,
  `Grade` varchar(255) DEFAULT NULL,
  `IsCompleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`StudentId`,`CourseId`),
  KEY `CourseId` (`CourseId`),
  CONSTRAINT `FK4BF8A09A4562DB52` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- Dumping structure for table otsdb.coursetest
CREATE TABLE IF NOT EXISTS `coursetest` (
  `StudentId` varchar(255) NOT NULL,
  `TestId` varchar(255) NOT NULL,
  `TakenOn` datetime DEFAULT NULL,
  `TakenAt` varchar(100) DEFAULT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime NOT NULL,
  PRIMARY KEY (`StudentId`,`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- Dumping structure for table otsdb.departmenttype
CREATE TABLE IF NOT EXISTS `departmenttype` (
  `DepartmentTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DepartmentTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;





-- Dumping structure for table otsdb.testitemtype
CREATE TABLE IF NOT EXISTS `testitemtype` (
  `TestItemTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`TestItemTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.useraccount
CREATE TABLE IF NOT EXISTS `useraccount` (
  `UserAccountId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `IsLocked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`UserAccountId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



-- Dumping structure for table otsdb.usertype
CREATE TABLE IF NOT EXISTS `usertype` (
  `UserTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `HomePageName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



-- Dumping structure for table otsdb.user
CREATE TABLE IF NOT EXISTS `user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `DepartmentTypeId` int(11) DEFAULT NULL,
  `UserTypeId` int(11) DEFAULT NULL,
  `UserAccountId` int(11) NOT NULL,
  `FirstName` varchar(60) NOT NULL,
  `LastName` varchar(60) NOT NULL,
  `Number` varchar(50) DEFAULT NULL,
  `Street` varchar(100) DEFAULT NULL,
  `City` varchar(60) DEFAULT NULL,
  `Province` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `DepartmentTypeId` (`DepartmentTypeId`),
  KEY `UserTypeId` (`UserTypeId`),
  KEY `UserAccountId` (`UserAccountId`),
  CONSTRAINT `FK7185C17C79C572D0` FOREIGN KEY (`UserAccountId`) REFERENCES `useraccount` (`UserAccountId`),
  CONSTRAINT `FK7185C17C9A0D8B8F` FOREIGN KEY (`DepartmentTypeId`) REFERENCES `departmenttype` (`DepartmentTypeId`),
  CONSTRAINT `FK7185C17CFA5FD70D` FOREIGN KEY (`UserTypeId`) REFERENCES `usertype` (`UserTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.teacherprofile
CREATE TABLE IF NOT EXISTS `teacherprofile` (
  `TeacherProfileId` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(60) DEFAULT NULL,
  `Rank` varchar(100) DEFAULT NULL,
  `UserId` int(11) DEFAULT NULL,
  PRIMARY KEY (`TeacherProfileId`),
  KEY `UserId` (`UserId`),
  CONSTRAINT `FK788874404F43D6AA` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





-- Dumping structure for table otsdb.knowledgemap
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
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;


