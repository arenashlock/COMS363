SET GLOBAL local_infile=1;

LOAD DATA LOCAL INFILE 'Users/arenashlock/Documents/COMS363_data/data/students.csv'
INTO TABLE students
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(snum,ssn,name,gender,dob,c_addr,c_phone,p_addr,p_phone);

LOAD DATA LOCAL INFILE 'Users/arenashlock/Documents/COMS363_data/data/departments.csv'
INTO TABLE departments
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(code,name,phone,college);

LOAD DATA LOCAL INFILE 'Users/arenashlock/Documents/COMS363_data/data/degrees.csv'
INTO TABLE degrees
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(name,level,department_code);

LOAD DATA LOCAL INFILE 'Users/arenashlock/Documents/COMS363_data/data/major.csv'
INTO TABLE major
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(snum,name,level);

LOAD DATA LOCAL INFILE 'Users/arenashlock/Documents/COMS363_data/data/minor.csv'
INTO TABLE minor
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(snum,name,level);

LOAD DATA LOCAL INFILE 'Users/arenashlock/Documents/COMS363_data/data/courses.csv'
INTO TABLE courses
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(number,name,description,credithours,level,department_code);

LOAD DATA LOCAL INFILE 'Users/arenashlock/Documents/COMS363_data/data/register.csv'
INTO TABLE register
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 ROWS
(snum,course_number,regtime,grade);