create table if not exists person(
                                     id int auto_increment primary key ,
                                     name varchar(200) not null ,
                                     age int not null ,
                                     email varchar(200) not null unique ,
                                     contact varchar(15) not null unique
);
create table if not exists user(
                                   user_name varchar(200)primary key,
                                   password varchar(200) not null,
                                   role enum('USER','ADMIN') default 'USER'
);

