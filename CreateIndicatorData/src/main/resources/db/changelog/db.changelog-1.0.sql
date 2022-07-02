--liquibase formatted sql

--changeset kazakov:1
create table candles
(
    id   serial PRIMARY KEY,
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
    id serial PRIMARY KEY,
    decision_by_deal integer[],
    indicator_type varchar(50),
    currency_pair varchar(50),
    time_frame varchar(50)

)

