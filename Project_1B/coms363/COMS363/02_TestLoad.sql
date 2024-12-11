SET GLOBAL local_infile=1;

CREATE TABLE students (
	snum int,
	ssn int,
	PRIMARY KEY (ssn)
);

LOAD DATA LOCAL INFILE 'Users/arenashlock/Documents/COMS363_data/data/students.csv'
INTO TABLE students
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(snum,ssn,@col3,@col4, @col5,@col6,@col7,@col7,@col8,@col9);

DROP TABLE Students;