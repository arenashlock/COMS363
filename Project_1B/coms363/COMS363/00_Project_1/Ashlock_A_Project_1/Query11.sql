SELECT COUNT(students.snum)
FROM students
LEFT JOIN major ON students.snum=major.snum
LEFT JOIN minor ON students.snum=minor.snum
WHERE students.gender="F" AND (major.name="Software Engineering" OR minor.name="Software Engineering");