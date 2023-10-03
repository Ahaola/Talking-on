create database java_chatroom charset utf8;
use java_chatroom;
create table user(
    userId int primary key auto_increment,
    username varchar(20) unique,
    password varchar(20)
);
insert into user values(null,'zhangsan','123');
insert into user values(null,'lisi','123');