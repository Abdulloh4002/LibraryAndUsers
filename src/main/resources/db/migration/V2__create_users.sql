create type role as enum (
    'user','admin'
);
create table users(
    id int generated always as identity primary key,
    username varchar(50) unique,
    password varchar(50),
    role_name role default 'user'
);
insert into users(username, password, role_name)
values('Abdulloh4002@', 'abdulloh2004@','admin');