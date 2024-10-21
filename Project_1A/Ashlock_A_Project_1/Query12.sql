SELECT major.name, major.level, COUNT(major.snum) as most_enrolled
FROM major
GROUP BY name, level
HAVING COUNT(major.snum) = (SELECT MAX(T1.num_students) FROM
	(SELECT COUNT(major.snum) as num_students FROM major GROUP BY major.name, major.level) T1)
ORDER BY name;