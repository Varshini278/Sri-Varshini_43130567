show databases;
create database sathyabama;
use sathyabama;
create table students(
sid int,
sname varchar(30),
smarks int);
show tables from sathyabama;
desc students;
select * from students;
insert into students( sid , sname, smarks)
values(001,'Akshaya',80),
(002,'Sabitha',95),
(003,'Shreya',85),
(004,'Thirsha',70),
(005,'Yazhini',98),
(006,'Navya',100),
(007,'Jasmitha',99);
ALTER TABLE students
ADD semail varchar(30);
DELETE from students WHERE sid=004;
ALTER TABLE students 
rename column semail to smailid;
UPDATE students  SET smailid='akshaya27@email.com' WHERE sid=001;
