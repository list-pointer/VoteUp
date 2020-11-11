create database html_voteup;
use html_voteup;

create table users(
	u_id int NOT NULL AUTO_INCREMENT,
    uid varchar(10) not null,
    u_name varchar(40) not null,
    email varchar(30) not null,
    pword varchar(20) not null,
    primary key (u_id));

insert into users (uid,u_name,email,pword) values
	('2019450020','Manish Jha','manish@gm.com','0000');
    
insert into users (uid,u_name,email,pword) values
	('2019450017','Abhishek Gupta','abhi@gm.com','0000');
     
select * from users;

----------
create table questiontable(
	q_id int NOT NULL AUTO_INCREMENT,
    question varchar(100) not null,
    v_yes int not null,
    v_no int not null,
    primary key (q_id)); 

insert into questiontable(question, v_yes, v_no) values ("Will we eat pasta?", 13, 7);

select * from questiontable;