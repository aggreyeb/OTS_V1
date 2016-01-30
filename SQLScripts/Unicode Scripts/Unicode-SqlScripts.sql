ALTER DATABASE otsdb CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE test_table (text varchar(100) NOT NULL);
INSERT INTO test_table VALUES ('l\u2019testing');
INSERT INTO test_table VALUES ('中国话不用彁字');
INSERT INTO test_table VALUES ('者');
SELECT * FROM test_table;

