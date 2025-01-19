create table client
(
    id   bigint not null primary key,
    address_id   bigint,
    name varchar(50)
);

create table address
(
    id   bigint not null primary key,
    street varchar(50)
);

create table phone
(
    id   bigint not null primary key,
    number varchar(50),
    client_id bigint
);
