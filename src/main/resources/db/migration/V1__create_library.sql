create table if not exists category(
    id int generated always as identity primary key,
    name varchar(50)
);
create table if not exists author(
    id int generated always as identity primary key,
    name varchar(50),
    surname varchar(50),
    address varchar(50)
);
create table if not exists book(
                                   id int generated always as identity primary key ,
                                   title varchar(50),
                                   year int,
                                   language varchar(50),
                                   author_id int references author(id) on delete cascade,
                                   category_id int references category(id) on delete cascade
);

