UPDATE students SET students.name="Scott" WHERE students.ssn=144673371;
UPDATE major SET major.name="Computer Science", major.level="MS" WHERE major.snum=(SELECT students.snum FROM students WHERE students.ssn=144673371);
DELETE FROM register WHERE register.regtime="Summer2024";