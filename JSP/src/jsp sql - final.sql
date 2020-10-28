create database jsp_voteup;
use jsp_voteup;

create table users(
	u_id int NOT NULL AUTO_INCREMENT,
    uid varchar(10) not null,
    u_name varchar(40) not null,
    email varchar(30) not null,
    pword varchar(20) not null,
    primary key (u_id));
    
insert into users (uid,u_name,email,pword) values
	('2019450020','Manish Jha','manish@gm.com','0000');
    
select * from users;

-------------------
create table questiontable(
	q_id int NOT NULL AUTO_INCREMENT,
    question varchar(100) not null,
    primary key (q_id)); 
    
insert into questiontable(question) values ('Can we eat pasta?');

select * from questiontable;

-----------------------
create table answertable(
	u_id int NOT NULL REFERENCES users(u_id),
    q_id int not null REFERENCES questiontable(q_id),
    vote int not null); 
    
select * from answertable;

insert into answertable(u_id, q_id, vote) values (1,1,1);
insert into answertable(u_id, q_id, vote) values (1,1,0);
insert into answertable(u_id, q_id, vote) values (1,1,1);
insert into answertable(u_id, q_id, vote) values (1,1,1);
insert into answertable(u_id, q_id, vote) values (1,1,0);
insert into answertable(u_id, q_id, vote) values (1,1,1);

