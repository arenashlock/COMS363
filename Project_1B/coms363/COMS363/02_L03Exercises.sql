-- Exercise #1 --
SELECT *
FROM
(SELECT s.sname, s.sid
FROM Sailors s, Boats b, Reserves r
WHERE b.bid = r.bid AND s.sid = r.sid AND b.color = 'red') t1
INNER JOIN
(SELECT s.sname, s.sid
FROM Sailors s, Boats b, Reserves r
WHERE b.bid = r.bid AND s.sid = r.sid AND b.color = 'green') t2
using (sid);

-- Oldest sailors (best for ties) --
SELECT s.sname, s.age
FROM Sailors s
WHERE s.age IN(SELECT MAX(s.age) FROM Sailors s);

-- Number of sailors --
SELECT COUNT(*)
FROM Sailors;