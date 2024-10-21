SELECT T1.name, T1.snum
FROM register
JOIN courses ON register.course_number=courses.number
JOIN (SELECT students.snum, students.name FROM students JOIN major ON students.snum=major.snum WHERE major.level!="BS") T1 ON register.snum=T1.snum
WHERE courses.name="database"
ORDER BY T1.snum;