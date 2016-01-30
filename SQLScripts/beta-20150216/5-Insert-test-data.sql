
USE `otsdb`;

INSERT INTO `academiccourse` (`CourseTypeId`, `Number`, `Name`, `Description`) VALUES
	(1, '100', 'Introduction to Botany', NULL),
	(2, '101', 'Advance Level Art', NULL);


INSERT INTO `cognitiveleveltype` (`CognitiveLevel`, `Name`, `Description`) VALUES
	(1, 'List', NULL),
	(2, 'Describe', NULL),
	(3, 'Summarize', NULL),
	(4, 'Classify', NULL);


INSERT  INTO `questionnaturetype` (`QuestionNatureType`, `Name`) VALUES
	(1, 'Correct'),
	(2, 'Negative'),
	(3, 'Incorrect'),
	(4, 'Negative-Incorrect');
	
	
INSERT  INTO `questiontype` (`QuestionType`, `Name`) VALUES
	(1, 'TrueOrFalse'),
	(2, 'Multiple Choice'),
	(3, 'Short Answer');
		
	
	
INSERT INTO `courseassignment` (`TeacherId`, `CourseId`, `AssignOn`, `EndDate`, `IsCompleted`) VALUES
	(1, 1, '2014-09-30 21:18:08', '0001-01-01 00:00:00', 0),
	(1, 2, '2014-09-30 21:18:08', '0001-01-01 00:00:00', 0);



INSERT INTO `courseknowledgemap` (`CourseId`, `KnowledgeMapId`, `AssignBy`, `AssignOn`, `ModifyOn`) VALUES
	(1, 4, 1, '2014-10-11 19:28:21', NULL),
	(1, 5, 1, '2014-10-12 09:19:14', NULL),
	(1, 82, 1, '2014-10-11 19:39:55', NULL),
	(1, 83, 1, '2014-10-12 09:19:16', NULL),
	(1, 84, 1, '2014-10-12 09:32:06', NULL),
	(2, 3, 1, '2014-10-11 13:22:33', NULL),
	(2, 5, 1, '2014-10-11 19:41:31', NULL),
	(2, 37, 1, '2014-10-11 17:51:30', NULL),
	(2, 38, 1, '2014-10-11 17:51:24', NULL),
	(2, 57, 1, '2014-10-11 19:41:32', NULL),
	(2, 58, 1, '2014-10-11 19:41:33', NULL),
	(2, 75, 1, '2014-10-11 19:41:36', NULL);




INSERT INTO `useraccount` (`UserAccountId`, `UserName`, `Password`, `IsLocked`) VALUES
	(1, 'maiga', 'maiga', 0),
	(2, 'eb', 'eb', 0);


INSERT INTO `usertype` (`UserTypeId`, `Name`, `HomePageName`) VALUES
	(1, 'Administrator', 'Administrator'),
	(2, 'Student', 'Student'),
	(3, 'Teacher', 'Teacher');


INSERT INTO `user` (`UserId`, `Email`, `Phone`,  `UserTypeId`, `UserAccountId`, `FirstName`, `LastName`, `Number`, `Street`, `City`, `Province`) VALUES
	(1, NULL, NULL,  3, 1, 'Maiga', 'Chang', NULL, NULL, NULL, NULL),
	(2, NULL, NULL,  3, 2, 'Ebenezer', 'Aggrey', NULL, NULL, NULL, NULL);





INSERT INTO `knowledgemap` (`KnowledgeMapId`, `Name`, `Description`, `CreateOn`, `Concepts`, `LastUpdated`, `IsPublic`, `CreatedBy`) VALUES
	(3, 'Plant', 'Plant Concept', '2014-07-09 08:10:44', '<children>\n  <child>\n    <id>465089421</id>\n    <rootid>3</rootid>\n    <identity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</identity>\n    <parentid>3</parentid>\n    <label>Root</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>47045517-cedd-44e4-b5a5-597aa569d6b7</id>\n        <rootid>3</rootid>\n        <parentidentity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f1130a8b-9fc9-4d85-a9e0-d43894d590f5</id>\n        <rootid>3</rootid>\n        <parentidentity>1fc3bb18-79ea-4c13-ab94-1a0c07e8d9c0</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>absorb</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>1835048167</id>\n    <rootid>3</rootid>\n    <identity>85e91d1c-2a4a-4e43-9698-7c5977824364</identity>\n    <parentid>3</parentid>\n    <label>Steam</label>\n    <children>\n      <id>760293952</id>\n      <rootid>3</rootid>\n      <identity>dfbf5a30-9b47-42db-b214-96abe14321fc</identity>\n      <parentid>1835048167</parentid>\n      <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n      <label>Sprout</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>dae6517a-3d17-4607-946e-f610da9dfa0a</id>\n        <rootid>3</rootid>\n        <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>d52640e1-3ac8-4b39-acbb-b42cf6a3608a</id>\n        <rootid>3</rootid>\n        <parentidentity>85e91d1c-2a4a-4e43-9698-7c5977824364</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>transport</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>866745648</id>\n    <rootid>3</rootid>\n    <identity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</identity>\n    <parentid>3</parentid>\n    <label>Leaf</label>\n    <children>\n      <id>771245702</id>\n      <rootid>3</rootid>\n      <identity>ccbcdd3b-aae9-4020-8850-e4e8a9e6fdeb</identity>\n      <parentid>866745648</parentid>\n      <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n      <label>Shape</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <children>\n      <id>1960298587</id>\n      <rootid>3</rootid>\n      <identity>f337f7b5-9a0f-4858-abba-f3f78232a186</identity>\n      <parentid>866745648</parentid>\n      <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n      <label>Margin</label>\n      <relationtype>PartOf</relationtype>\n      <conceptschemas/>\n    </children>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a517cb20-dc3f-422e-bb63-4fa41d7bede3</id>\n        <rootid>3</rootid>\n        <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>vegetative organ</conceptname>\n        <conceptaction>transport</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>a60e835e-5cc8-49e7-8fbb-002cb0a226b0</id>\n        <rootid>3</rootid>\n        <parentidentity>86610b67-c9c9-4ae6-8b0a-88817e9bedf8</parentidentity>\n        <relationname>can</relationname>\n        <conceptname>water</conceptname>\n        <conceptaction>evaporate</conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n</children>', '2014-07-09 08:28:34', 1, 1),
	(4, 'Garden', 'Garden Concept', '2014-07-09 08:30:29', '<children>\n  <child>\n    <id>770196883</id>\n    <rootid>4</rootid>\n    <identity>40d1b808-c054-477e-acd1-c0d7f101b2fa</identity>\n    <parentid>4</parentid>\n    <label>Taiwan Cherry</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a357d2d3-3bcc-460f-911b-735854a9634b</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Aristate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>ebe8eef4-3295-4fce-8139-29a5e78c38da</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f4ada86f-b3a8-49bb-b467-6a9c0957ae1a</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Serrate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>72b3a8b4-05e6-4b99-83f2-73a1a1258af3</id>\n        <rootid>4</rootid>\n        <parentidentity>40d1b808-c054-477e-acd1-c0d7f101b2fa</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arragement</attributename>\n        <attributevalue>Alternate</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>400134451</id>\n    <rootid>4</rootid>\n    <identity>949edac4-2518-4112-8763-fb5408feeca3</identity>\n    <parentid>4</parentid>\n    <label>Rhododendron</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>a384613d-ba83-4421-b07a-01a8d2f5ba31</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Obtuse</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>ae09ed43-4ad3-4291-b531-a85557e16c5c</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>993339bf-a4f9-45e8-b807-f490eb534f75</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Entire</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>f2a6a298-5d61-4bae-a821-0cfd1f7ea03b</id>\n        <rootid>4</rootid>\n        <parentidentity>949edac4-2518-4112-8763-fb5408feeca3</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arrangement</attributename>\n        <attributevalue>Alternate</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>103604509</id>\n    <rootid>4</rootid>\n    <identity>927a05fc-1853-4bb6-a397-dd11c1c2c082</identity>\n    <parentid>4</parentid>\n    <label>Golden Dedron</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>7a9599af-c8b0-48db-a818-314ba3d60cfa</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Shape</attributename>\n        <attributevalue>Aristate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>688ee6d8-b882-4878-b79f-3ef698798f94</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Venation</attributename>\n        <attributevalue>Palmate</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>42f4d8ee-cc26-448f-a422-8111f767d289</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Margin</attributename>\n        <attributevalue>Entire</attributevalue>\n      </conceptschema>\n      <conceptschema>\n        <id>98dadb8b-2c2b-4f21-96e4-7dd14c94bd1b</id>\n        <rootid>4</rootid>\n        <parentidentity>927a05fc-1853-4bb6-a397-dd11c1c2c082</parentidentity>\n        <relationname>has</relationname>\n        <conceptname>Leaf</conceptname>\n        <conceptaction></conceptaction>\n        <attributename>Arrangement</attributename>\n        <attributevalue>Opposite</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n  <child>\n    <id>1836452959</id>\n    <rootid>4</rootid>\n    <identity>d330c54d-4397-462b-9a72-081c1e06a44c</identity>\n    <parentid>4</parentid>\n    <label>Broad Leaf Rocks</label>\n    <relationtype>PartOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>c27eb12f-a313-43e0-bf78-e7d2a197bf6c</id>\n        <rootid>4</rootid>\n        <parentidentity>d330c54d-4397-462b-9a72-081c1e06a44c</parentidentity>\n        <relationname>Broad leaf</relationname>\n        <conceptname>Is </conceptname>\n        <conceptaction>rocking </conceptaction>\n        <attributename>Start</attributename>\n        <attributevalue>but not tasty</attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n</children>', '2014-10-12 09:17:17', 1, 1),
	(5, 'Car ', 'Car Concept', '2014-10-11 12:29:51', NULL, NULL, 1, 1),
	(84, 'Vegetable', 'Vegetable domain concept', '2014-10-12 09:20:41', '<children>\n  <child>\n    <id>371534842</id>\n    <rootid>84</rootid>\n    <identity>9ae4d0f1-bdfd-449c-8f51-9f7f489872dc</identity>\n    <parentid>84</parentid>\n    <label>Kale</label>\n    <relationtype>TypeOf</relationtype>\n    <conceptschemas>\n      <conceptschema>\n        <id>d3bc71a9-e340-4b89-a745-54a583ed8359</id>\n        <rootid>84</rootid>\n        <parentidentity>9ae4d0f1-bdfd-449c-8f51-9f7f489872dc</parentidentity>\n        <relationname>is</relationname>\n        <conceptname>kind</conceptname>\n        <conceptaction></conceptaction>\n        <attributename></attributename>\n        <attributevalue></attributevalue>\n      </conceptschema>\n    </conceptschemas>\n  </child>\n</children>', '2014-10-12 09:21:52', 1, 1);
