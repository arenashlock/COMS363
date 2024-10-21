SELECT name, level, COUNT(snum) as most_enrolled
FROM (SELECT * FROM minor UNION SELECT * FROM major) T1
GROUP BY name, level
HAVING COUNT(snum) = (SELECT MAX(num_students) FROM
	(SELECT COUNT(snum) as num_students FROM (SELECT * FROM minor UNION SELECT * FROM major) T3 GROUP BY name, level) T2)
ORDER BY most_enrolled;