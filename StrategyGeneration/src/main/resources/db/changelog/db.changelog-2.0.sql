--liquibase formatted sql

--changeset kazakov:1
create table users
(
    id        serial PRIMARY KEY,
    user_name text unique not null,
    password  text        not null,
    roles     text[] not null,
    enabled   boolean     not null
);