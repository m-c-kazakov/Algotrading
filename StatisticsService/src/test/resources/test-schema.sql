

create table if not exists users
(
    id        serial PRIMARY KEY,
    user_name text unique not null,
    password  text        not null,
    roles     text[] not null,
    enabled   boolean     not null
);

create table if not exists statistics_of_strategy
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