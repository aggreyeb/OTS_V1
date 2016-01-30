
INSERT  INTO `useraccount` (`UserAccountId`, `UserName`, `Password`, `IsLocked`) VALUES
	(21, 'st@ots.com', 'st', 0),
	(33, 'admin@ots.org', 'admin', 0);

INSERT  INTO `user` (`UserId`, `Email`, `Phone`, `UserTypeId`, `UserAccountId`, `FirstName`, `LastName`, `Number`, `Street`, `City`, `Province`, `DepartmentTypeId`) VALUES
	(21, 'st@ots.com', '908-987-7865', 2, 21, 'Mark', 'Smitty', NULL, NULL, NULL, NULL, NULL),
	(33, 'admin@ots.org', '209-098-0987', 1, 33, 'admin', 'admin', NULL, NULL, NULL, NULL, NULL);

