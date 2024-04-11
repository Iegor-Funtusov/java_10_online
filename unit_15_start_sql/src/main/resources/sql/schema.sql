# create database
# CREATE SCHEMA `java_10_online` DEFAULT CHARACTER SET utf8;

# create table
create table students
(
    id bigint auto_increment primary key not null,
    created datetime(6) null,
    first_name varchar(255) null,
    last_name varchar(255) null,
    age int null
);

create table user_balances(id bigint auto_increment primary key not null, name varchar(255) null, balance bigint null) engine=InnoDB;
describe user_balances;

SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;

set transaction isolation level read uncommitted;

update user_balances set balance = 110 where id = 1;