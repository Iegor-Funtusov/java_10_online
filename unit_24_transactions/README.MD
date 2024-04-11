# About transactions #

## Init schema and table ##

### create schema ###
`CREATE SCHEMA `test_schema` DEFAULT CHARACTER SET utf8;`
`use test_schema;`

### create table ###
`create table user_balances(id bigint auto_increment primary key not null, name varchar(255) null, balance bigint null) engine=InnoDB;`

### check table ###
`describe user_balances;`

## Init values ##

### insert values ###
`insert into user_balances values (1, 'name1', 100);`
`insert into user_balances values (2, 'name2', 100);`
`insert into user_balances values (3, 'name3', 100);`

### check values ###
`select * from user_balances;`

## Check transactions status ##
`SELECT @@transaction_ISOLATION;`
`SELECT @@global.transaction_isolation;`

## About `READ UNCOMMITTED` ##
`set session transaction isolation level read uncommitted;`
