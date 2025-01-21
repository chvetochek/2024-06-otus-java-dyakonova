create table address
(
    id   bigserial not null primary key,
    street varchar(50)
);

create table client
(
    id   bigserial not null primary key,
    name varchar(50),
    address_id   bigint references address (id)
);

create table phone
(
    id   bigserial not null primary key,
    number varchar(50),
    client_id bigint references client (id)
);

-- INSERT INTO address (id, street) VALUES (1, 'moscow');
-- INSERT INTO client (id, name) VALUES (1, 'kate1');
-- INSERT INTO phone (id, number, client_id) VALUES (1, '123', 1);
-- INSERT INTO client (id, name, address_id) VALUES (2, 'kate2', 1);
-- INSERT INTO phone (id, number, client_id) VALUES (3, '1233333', 2);
