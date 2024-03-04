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
