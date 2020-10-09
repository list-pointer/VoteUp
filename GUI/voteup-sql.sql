create database voteup;
use voteup;

create table votetable(
	v_id int NOT NULL AUTO_INCREMENT,
    uid varchar(10) not null,
    u_name varchar(20) not null,
    email varchar(20) not null,
    vote boolean not null,
    primary key (v_id));

insert into votetable (uid,u_name,email,vote) values
	('2019450020','Manish Jha','manish@gm.com',1);
    
select * from votetable;

select
(select count(*) from votetable where vote=1) as TrueVotes,
(select count(*) from votetable where vote=0) as FalseVotes
from dual;
----------
-- select count(*) from votetable where vote=0;
-- select v_yes from votetable where v_id=1;
-- update votetable set v_yes=14 where v_id=1;
-- truncate table votetable;