create table candles
(
    TICKER varchar(50) not null,
    PER smallint not null,
    DATE date not null,
    TIME time not null,
    OPEN numeric not null,
    HIGH numeric not null,
    LOW numeric not null,
    CLOSE numeric not null,
    VOL integer not null
)