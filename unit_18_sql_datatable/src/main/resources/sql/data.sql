insert into departments values (1, 'JAVA');
insert into departments values (2, 'JS');
insert into departments values (3, 'DEV_OPS');

insert into employees values (default, 'qq', 'qqq', 20);
insert into employees values (default, 'ww', 'www', 21);
insert into employees values (default, 'ee', 'eee', 22);
insert into employees values (default, 'rr', 'rrr', 23);
insert into employees values (default, 'tt', 'ttt', 23);
insert into employees values (default, 'yy', 'yyy', 24);
insert into employees values (default, 'uu', 'uuu', 24);
insert into employees values (default, 'ii', 'iii', 24);
insert into employees values (default, 'oo', 'ooo', 22);
insert into employees values (default, 'pp', 'ppp', 22);
insert into employees values (default, 'aa', 'aaa', 22);
insert into employees values (default, 'ss', 'sss', 19);
insert into employees values (default, 'dd', 'ddd', 19);

insert into dep_emp values (1, 1);
insert into dep_emp values (1, 2);
insert into dep_emp values (1, 3);
insert into dep_emp values (1, 4);
insert into dep_emp values (1, 5);
insert into dep_emp values (1, 6);
insert into dep_emp values (1, 7);

insert into dep_emp values (2, 1);
insert into dep_emp values (2, 2);
insert into dep_emp values (2, 3);
insert into dep_emp values (2, 9);
insert into dep_emp values (2, 10);
insert into dep_emp values (2, 11);

insert into dep_emp values (3, 1);
insert into dep_emp values (3, 12);
insert into dep_emp values (3, 13);


select * from employees as emp where emp.first_name like 'ee' order by emp.age desc limit 0, 10;
select * from employees as emp where emp.first_name like '%e%' order by emp.age desc limit 0, 10;
select * from employees as emp where emp.first_name like 'e%' order by emp.age desc limit 0, 10;
select * from employees as emp where emp.first_name like '%e' order by emp.age desc limit 0, 10;

select count(*) as count from employees as emp where emp.first_name like '%e%' or emp.age >= 24 order by emp.age desc limit 0, 10;
select distinct (emp.age) from employees as emp where emp.first_name like '%e%' or emp.age >= 24 order by emp.age desc limit 0, 10;

select * from employees where age in (20, 23);
select * from employees where age = 20 or age = 23;

select * from employees left join dep_emp de on employees.id = de.emp_id where dep_id is not null;
select * from departments left join dep_emp de on departments.id = de.dep_id where dep_id is not null;

select d.name, count(dep_id) as count_employee
from departments as d left join dep_emp de on d.id = de.dep_id
where dep_id is not null
group by d.name;

select id, first_name, last_name, age, count(id) as count_departments
from employees left join dep_emp de on employees.id = de.emp_id
group by id;

select e.id, e.first_name, e.last_name, e.age, COALESCE(dep_id, 0) as dep_id, COALESCE(emp_id, 0) as emp_id
from employees  as e left join dep_emp de on e.id = de.emp_id;
