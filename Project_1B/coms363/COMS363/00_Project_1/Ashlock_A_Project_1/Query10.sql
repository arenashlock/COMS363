SELECT courses.number, courses.name, COUNT(T1.snum)
FROM courses
LEFT JOIN (SELECT students.snum, register.course_number FROM register JOIN students ON register.snum=students.snum) T1 ON courses.number=T1.course_number
GROUP BY courses.number, courses.name
ORDER BY courses.number;