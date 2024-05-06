create table messages(
    id UUID primary key,
    sender varchar(255) not null,
    target varchar(255) not null,
    message varchar(255) not null,
    created_at timestamp default current_timestamp
);