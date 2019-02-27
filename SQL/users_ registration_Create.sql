create table users_registration (
id int primary key,
name varchar2(100),
email varchar2(100) unique,
phone varchar2(10) unique,
message varchar2(4000)
);