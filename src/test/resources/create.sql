CREATE TABLE customers(
    id integer not null auto_increment,
    name varchar(100) NOT NULL ,
    surname varchar(100) not null ,
    money integer not null ,
    password varchar(100) not null ,
    primary key (id)
);

create table goods(
    id integer not null auto_increment,
    name varchar(100) not null ,
    price integer not null ,
    primary key (id)
);

create table orders(
    id integer not null auto_increment,
    customer_id integer not null ,
    is_processed boolean not null default(false),
    primary key (id)
);

create table order_lines(
    id integer not null auto_increment,
    order_id integer not null ,
    good_id integer not null ,
    amount integer not null ,
    primary key (id)
);

insert into customers (name, surname, money, password) values ( 'Mark', 'Hey', 100, 'qwerty12');

insert into goods (name, price) VALUES ( 'first_good', 900 );

insert into order_lines (order_id, good_id, amount) VALUES ( 1, 1, 3 ),
                                                           (1, 3, 4),
                                                           (1, 6, 7);

