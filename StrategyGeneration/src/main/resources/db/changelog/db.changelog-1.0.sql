--liquibase formatted sql

--changeset kazakov:1
create table information_of_candles
(
    id            serial PRIMARY KEY,
    hash_code     integer unique not null,
    currency_pair text           not null,
    time_frame    text           not null
);

--changeset kazakov:2
create table information_of_indicator
(
    id                     serial PRIMARY KEY,
    hash_code              integer unique not null,
    indicator_type         text           not null,
    information_of_candles text           not null,
    parameters             jsonb          not null
);

--changeset kazakov:3
create table specification_of_strategy
(

    id                               serial PRIMARY KEY,
    statistics_of_strategy_id        text,
    hash_code                        integer unique not null,
    start_score                      integer        not null,
    acceptable_risk                  integer        not null,
    sum_of_deal_type                 text           not null,
    sum_of_deal_configuration_data   jsonb          not null,

    stop_loss_type                   text           not null,
    stop_loss_configuration_data     jsonb          not null,

    trailing_stop_type               text           not null,
    trailing_stop_configuration_data jsonb          not null,

    take_profit_type                 text           not null,
    take_profit_configuration_data   jsonb          not null,

    type_of_deal                     text           not null,

    information_of_candles           text           not null,

    description_to_open_a_deal       text[]         not null,

    description_to_close_a_deal      text[]         not null

);

--changeset kazakov:4
create table statistics_of_strategy
(
    id                                        serial PRIMARY KEY,
    specification_of_strategy_id              integer not null,
    score                                     integer not null,
    value_of_acceptable_risk                  integer not null,
    maximum_percent_drawdown_from_start_score integer not null,
    average_percent_drawdown_of_score         integer not null,
    maximum_value_from_score                  integer not null,
    number_of_winning_deals                   integer not null,
    number_of_losing_deals                    integer not null
);



