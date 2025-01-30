create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

create table address
(
    client_id   bigint not null references client (id),
    street varchar(50)
);

create table phone
(
    id   bigserial not null primary key,
    number varchar(50) not null,
    client_id bigint not null references client (id)
);


--  INSERT INTO address (id, street) VALUES (1, 'newmoscow1');
--  INSERT INTO address (id, street) VALUES (2, 'newmoscow2');
--  INSERT INTO address (id, street) VALUES (3, 'newmoscow3');
--
--  INSERT INTO client (id, name, address_id) VALUES (1, 'kateFirst', 3);
--  INSERT INTO client (id, name, address_id) VALUES (2, 'kateSEcond', 1);
-- INSERT INTO phone (number, client_id) VALUES ('123', 1);
-- INSERT INTO client (name, address_id) VALUES ('kate2', 1);
-- INSERT INTO phone (number, client_id) VALUES ('1233333', 2);
--  INSERT INTO client (name, address_id) VALUES ('kate3', 1);


