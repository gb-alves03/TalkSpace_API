create table users(
    id UUID primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    avatar varchar(255)
);