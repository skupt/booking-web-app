create table event (
id bigint auto_increment primary key,
date timestamp,
price numeric(19,2),
title varchar(255)
);

create table user_impl (
id bigint auto_increment primary key,
email varchar(255) unique,
name varchar(255)
);

create table user_account (
user_id bigint primary key,
money_amount numeric(19,2),
    CONSTRAINT fk_user_account
    FOREIGN KEY (user_id)
        REFERENCES user_impl(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

create table ticket (
id bigint auto_increment primary key,
category varchar(255),
place integer,
event_id bigint,
user_id bigint,
    CONSTRAINT fk_event
    FOREIGN KEY (event_id)
        REFERENCES event(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT fk_user
    FOREIGN KEY (user_id)
        REFERENCES user_impl(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);
