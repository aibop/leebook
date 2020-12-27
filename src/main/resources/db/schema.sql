create table if not exists user (
id int not null primary key auto_increment,
account_no varchar(100),
name varchar(50),
token char(36),
created_at DATETIME,
updated_at DATETIME);