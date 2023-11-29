create database distributor;
use distributor;

create table user
(id int primary key auto_increment,
name varchar(100),
surname varchar(100),
usertype varchar(100));

insert user(name, surname, usertype)
values
('Kim','Nguyen','private'),
('Generation Italy',' ','business');


create table contract
(id int primary key auto_increment,
startdate date,
enddate date,
contracttype varchar(100),
userid int,
foreign key(userid) references user(id));

insert contract(startdate, enddate,contracttype, userid)
values
('2023-11-28','2024-11-28','gas',1),
('2023-11-20','2033-11-20','both',2),
('2024-01-01','2024-12-31','electricity',1);
