--liquibase formatted sql

--changeset kazakov:1
create table candles
(
    id     serial PRIMARY KEY,
    TICKER varchar(50) not null,
    PER    smallint    not null,
    DATE   date        not null,
    TIME   time        not null,
    OPEN   numeric     not null,
    HIGH   numeric     not null,
    LOW    numeric     not null,
    CLOSE  numeric     not null,
    VOL    integer     not null
);

--changeset kazakov:2
create table data_of_indicators
(
    id                serial PRIMARY KEY,
    unique_identifier text unique not null,
    type_of_deal      varchar(50) not null,
    decision_by_deal  integer[]   not null,
    indicator_type    varchar(50) not null,
    currency_pair     varchar(50) not null,
    time_frame        varchar(50) not null

)

