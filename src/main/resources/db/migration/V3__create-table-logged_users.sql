create table logged_users(
    id uuid primary key,
    email varchar(255) not null,
    password varchar(255) not null
);