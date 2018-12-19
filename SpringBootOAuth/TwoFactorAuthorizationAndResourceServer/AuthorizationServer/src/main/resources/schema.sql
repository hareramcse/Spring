create table permission(id int primary key auto_increment, permission_name varchar(50) not null);
create table role(id int primary key auto_increment, role_name varchar(50) not null);
create table role_permission(id int primary key auto_increment, role_id int, foreign key(role_id) references role(id), permission_id int, foreign key(permission_id) references permission(id));

create table users(id int primary key auto_increment, first_name varchar(50) not null, last_name varchar(50), email_id varchar(50) not null, password varchar(1000), mobile varchar(20), country varchar(50), user_type varchar(20));
create table role_users( id int primary key auto_increment, role_id int, foreign key(role_id) references role(id), user_id int, foreign key(user_id) references users(id));

alter table users add column is_tfa_enabled varchar(1);
alter table users add column tfa_code varchar(45);
alter table users add column tfa_expire_time varchar(45);
alter table users add column tfa_default_type varchar(10);

