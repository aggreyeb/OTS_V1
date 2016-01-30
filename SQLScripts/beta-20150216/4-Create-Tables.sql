
USE `otsdb`;


-- Dumping structure for table otsdb.academiccourse
CREATE TABLE IF NOT EXISTS `academiccourse` (
  `CourseTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Number` varchar(15) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CourseTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `cognitiveleveltype` (
  `CognitiveLevel` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CognitiveLevel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.questiontype
CREATE TABLE IF NOT EXISTS `questiontype` (
  `QuestionType` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`QuestionType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Dumping structure for table otsdb.testitemtype
CREATE TABLE IF NOT EXISTS `testitemtype` (
  `TestItemTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`TestItemTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Dumping structure for table otsdb.questionnaturetype
CREATE TABLE IF NOT EXISTS `questionnaturetype` (
  `QuestionNatureType` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`QuestionNatureType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.departmenttype
CREATE TABLE IF NOT EXISTS `departmenttype` (
  `DepartmentTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DepartmentTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.usertype
CREATE TABLE IF NOT EXISTS `usertype` (
  `UserTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `HomePageName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ******************************** Lookup tables ends ***********************************


-- Dumping structure for table otsdb.useraccount
CREATE TABLE IF NOT EXISTS `useraccount` (
  `UserAccountId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `IsLocked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`UserAccountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- Dumping structure for table otsdb.user
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
  `DepartmentTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `UserTypeId` (`UserTypeId`),
  KEY `UserAccountId` (`UserAccountId`),
  KEY `FK_rt6kd6fenjlljjlxny1st239u` (`DepartmentTypeId`),
  CONSTRAINT `FK7185C17C79C572D0` FOREIGN KEY (`UserAccountId`) REFERENCES `useraccount` (`UserAccountId`),
  CONSTRAINT `FK7185C17CFA5FD70D` FOREIGN KEY (`UserTypeId`) REFERENCES `usertype` (`UserTypeId`),
  CONSTRAINT `FK_rt6kd6fenjlljjlxny1st239u` FOREIGN KEY (`DepartmentTypeId`) REFERENCES `departmenttype` (`DepartmentTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- Dumping structure for table otsdb.teacherprofile
CREATE TABLE IF NOT EXISTS `teacherprofile` (
  `TeacherProfileId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL,
  `Title` varchar(60) DEFAULT NULL,
  `Rank` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TeacherProfileId`),
  KEY `FK_ifjh0u07x1kb6qhgyq98qfhmw` (`UserId`),
  CONSTRAINT `FK_ifjh0u07x1kb6qhgyq98qfhmw` FOREIGN KEY (`UserId`) REFERENCES `user` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ******************************** User and user accounts ends ***********************************

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


-- Dumping structure for table otsdb.courseregistration
CREATE TABLE IF NOT EXISTS `courseregistration` (
  `StudentId` int(11) NOT NULL,
  `CourseId` int(11) NOT NULL,
  `Date` datetime DEFAULT NULL,
  `Grade` varchar(255) DEFAULT NULL,
  `IsCompleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`StudentId`,`CourseId`),
  KEY `FK_ddegea191vgpjjtvpx25jlq74` (`CourseId`),
  CONSTRAINT `FK_ddegea191vgpjjtvpx25jlq74` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- Dumping structure for table otsdb.test
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.question
CREATE TABLE IF NOT EXISTS `question` (
  `QuestionId` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(100) DEFAULT NULL,
  `Mark` float DEFAULT NULL,
  `QuestionTypeId` int(11) DEFAULT NULL,
  `QuestionNatureType_id` int(11) DEFAULT NULL,
  `CognitiveLevelType_id` int(11) DEFAULT NULL,
  `Test_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`QuestionId`),
  KEY `QuestionTypeId` (`QuestionTypeId`),
  KEY `QuestionNatureType_id` (`QuestionNatureType_id`),
  KEY `CognitiveLevelType_id` (`CognitiveLevelType_id`),
  KEY `Test_id` (`Test_id`),
  CONSTRAINT `FK1BCDAE6B3E8267BD` FOREIGN KEY (`CognitiveLevelType_id`) REFERENCES `cognitiveleveltype` (`CognitiveLevel`),
  CONSTRAINT `FK1BCDAE6B4EDF946E` FOREIGN KEY (`Test_id`) REFERENCES `test` (`TestId`),
  CONSTRAINT `FK1BCDAE6BA9464857` FOREIGN KEY (`QuestionTypeId`) REFERENCES `questiontype` (`QuestionType`),
  CONSTRAINT `FK1BCDAE6BCF2183A8` FOREIGN KEY (`QuestionNatureType_id`) REFERENCES `questionnaturetype` (`QuestionNatureType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.answer
CREATE TABLE IF NOT EXISTS `answer` (
  `AnswerId` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(255) DEFAULT NULL,
  `Question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`AnswerId`),
  KEY `Question_id` (`Question_id`),
  CONSTRAINT `FK2644B9D2989C4BF` FOREIGN KEY (`Question_id`) REFERENCES `question` (`QuestionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.questionlineitem
CREATE TABLE IF NOT EXISTS `questionlineitem` (
  `QuestionLineItemId` int(11) NOT NULL AUTO_INCREMENT,
  `Text` varchar(255) DEFAULT NULL,
  `IsCorrect` tinyint(1) DEFAULT NULL,
  `Question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`QuestionLineItemId`),
  KEY `Question_id` (`Question_id`),
  CONSTRAINT `FK2492265C2989C4BF` FOREIGN KEY (`Question_id`) REFERENCES `question` (`QuestionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Dumping structure for table otsdb.studentcourseregistration
CREATE TABLE IF NOT EXISTS `studentcourseregistration` (
  `StudentId` int(11) NOT NULL,
  `CourseId` int(11) NOT NULL,
  `Date` datetime DEFAULT NULL,
  PRIMARY KEY (`StudentId`,`CourseId`),
  KEY `CourseId` (`CourseId`),
  CONSTRAINT `FK452DB2A94562DB52` FOREIGN KEY (`CourseId`) REFERENCES `academiccourse` (`CourseTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table otsdb.studenttest
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

-- Data exporting was unselected.


-- Dumping structure for table otsdb.teachercoursetest
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






