//add a single node in degree
create (n:degree {name:'Data Science', level:'BS'});

//add a single node in department
create (m:department {dcode:401, name:"Computer Science", phone: 5152982801, college:"LAS"})

//add an edge
create (n)<-[:administer]-(m);

//delete everything
match (n) detach delete n;


//create nodes by csv import
LOAD CSV with headers FROM 'https://docs.google.com/spreadsheets/d/e/2PACX-1vSZjB-njgyoNAS7hhgvyqPMKs7gSEAzRzj55XrDsS4Ly_Q5pS2XWIWe-Qnx_UsszAxs7GZDT8z9N6l6/pub?gid=1270533085&single=true&output=csv' AS line
CREATE (:department { dcode:toInteger(line.dcode),name:line.name,phone:toInteger(line.phone),college:line.college});


//create nodes and edges by csv import
LOAD CSV with headers FROM 'https://docs.google.com/spreadsheets/d/e/2PACX-1vQmzlXmZkQbRaV4gcSaMRINRlEypaOZjMNBNutAHj2Eij4CgpfYni54oj5pFtNAb1k5vCu6pQlsuSBN/pub?gid=1817942933&single=true&output=csv' AS line
CREATE (dgr:degree {name:line.name,level:line.level})
WITH dgr, line
MATCH (dep: department {dcode:toInteger(line.dcode)})
CREATE (dgr)<-[:administer]-(dep);


//add an edge for Data Science with Computer Science department
match (n:degree {name:'Data Science'})
with n
match (m:department)
where m.name='Computer Science'
create (n)<-[:administer]-(m)
//another way to add edge, but will get warning
match (n:degree {name:'Data Science'}), (m:department {name: 'Computer Science'})
create (n)<-[:administer]-(m)



//search on node
MATCH (d:degree)
where d.level='PhD'
return d.name

//search on path
MATCH (dg:degree)<-[:administer]-(d:department)
WHERE d.name="Computer Science"
RETURN dg.name, dg.level;

//with
MATCH (dg:degree)<-[:administer]-(d:department)
with min(d.dcode) as minDC
MATCH (dg1:degree)<-[a:administer]-(d1:department)
where d1.dcode=minDC
return dg1.name, dg1.level;

//count
MATCH (dg:degree)<-[:administer]-(d:department)
return d.name, count(dg);


//delete edge
match (n:degree {name:'Data Science'})<-[r1:administer]-(m:department {name: 'Computer Science'})
delete r1
return n, m;

//optional match
match (n:degree)
optional match (n)--(m:department)
return n, m;
//see different with the following
match (n:degree)--(m:department)
return n, m;

//delete nodes
match (n :degree) detach delete n;




//more complex search
//find students who are classmates
match p=(a:student)-[r1:register]->(:course)<-[r2:register]-(b:student)
where r1.regtime=r2.regtime
return p;

//find the most popular class
match (a:student)-[r1:register]->(c:course)
return c.cname as course_name, count(a) as popularity
order by popularity DESC limit 1;

//find the most popular department based on their courses
match (a:student)-[r1:register]->(c:course)<-[:offers]-(d:department)
return d.name as dept_name, count(d) as popularity
order by popularity DESC limit 1;

//count the number of students in each degree (regardless major or minor)
match (a:student)-->(d:degree)
return d.name, count(a)
order by count(a) desc;

// count the number of students related to each department (take a course, or take a major)
match (a:student)-[*..2]-(d:department)
return d.name, count(a)

//how did Victor know Becky?
MATCH (a:student {name:"Victor"}),(b:student {name:"Becky"}), 
p = shortestPath((a)-[*..]-(b)) RETURN p

//think about how you would write those queries in MySQL
