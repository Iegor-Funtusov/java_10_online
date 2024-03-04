#create
insert into students values (default, current_timestamp(), 'Iehor', 'Funtusov', 38);
#read
# select * from students;
# select id from students;
select s.id, s.created from students as s;
#update
update students set first_name = 'Iegor' where id = 1;
#delete
delete from students where id = 2;