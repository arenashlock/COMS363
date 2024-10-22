-- SELECT number, name, AVG(register.grade) 
-- FROM courses 
-- JOIN register ON register.course_number = courses.number
-- GROUP BY number, name;

-- SELECT COUNT(students.snum)
-- FROM students
-- JOIN (SELECT snum FROM major JOIN (SELECT degrees.name, degrees.level FROM degrees JOIN departments ON degrees.department_code=departments.code WHERE departments.college="LAS") T1 ON major.name=T1.name AND major.level=T1.level) T2 ON students.snum=T2.snum
-- JOIN (SELECT snum FROM minor JOIN (SELECT degrees.name, degrees.level FROM degrees JOIN departments ON degrees.department_code=departments.code WHERE departments.college="LAS") T3 ON minor.name=T3.name AND minor.level=T3.level) T4 ON students.snum=T4.snum
-- WHERE students.gender="F";

-- CREATE INDEX idx_gender ON students (gender);
-- DROP INDEX idx_gender ON students;

-- SELECT MALE.name, MALE.level
-- FROM
-- (SELECT T4.name, T4.level, COUNT(T4.snum) as male_count
-- FROM (
-- SELECT students.snum, T3.name, T3.level
-- FROM students
-- JOIN (SELECT snum, T1.name, T1.level
-- FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN major ON degrees.name=major.name AND degrees.level=major.level) T1
-- UNION
-- SELECT snum, T2.name, T2.level
-- FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN minor ON degrees.name=minor.name AND degrees.level=minor.level) T2) T3 ON students.snum=T3.snum
-- WHERE students.gender="M") T4
-- GROUP BY T4.name, T4.level) MALE
-- JOIN
-- (SELECT T9.name, T9.level, COUNT(T9.snum) as female_count
-- FROM (
-- SELECT students.snum, T8.name, T8.level
-- FROM students
-- JOIN (SELECT snum, T6.name, T6.level
-- FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN major ON degrees.name=major.name AND degrees.level=major.level) T6
-- UNION
-- SELECT snum, T7.name, T7.level
-- FROM (SELECT snum, degrees.name, degrees.level FROM degrees JOIN minor ON degrees.name=minor.name AND degrees.level=minor.level) T7) T8 ON students.snum=T8.snum
-- WHERE students.gender="F") T9
-- GROUP BY T9.name, T9.level) FEMALE
-- ON MALE.name=FEMALE.name AND MALE.level=FEMALE.level
-- WHERE male_count > female_count;