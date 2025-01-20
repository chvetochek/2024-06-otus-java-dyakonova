create table address
(
    id   bigint not null primary key,
    street varchar(50)
);

create table client
(
    id   bigint not null primary key,
    name varchar(50),
    address_id   bigint,
    constraint fk_user_account_address_id foreign key (address_id) references address (id)
);

--create table phone
--(
--    id   bigint not null primary key,
--    number varchar(50),
--    client_id bigint
--);

 INSERT INTO address (id, street) VALUES (1, 'moscow');
 INSERT INTO client (id, name) VALUES (1, 'kate1');
  INSERT INTO client (id, name, address_id) VALUES (2, 'kate2', 1);
