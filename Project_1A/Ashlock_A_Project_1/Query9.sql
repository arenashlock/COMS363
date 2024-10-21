SELECT students.name, students.snum, students.ssn
FROM students
WHERE students.name>="Amy" AND students.name<="Christopher"
ORDER BY students.name;