create table stocks_d (
    ticker varchar not null,
    time timestamp without time zone not null,
    volume bigint not null,
    open double precision not null,
    close double precision not null,
    high double precision not null,
    low double precision not null,
    primary key (ticker, time)
);

COPY stocks_d (time, ticker, volume, open, close, high, low)
FROM '/docker-entrypoint-initdb.d/stocks.csv' DELIMITER ',' CSV HEADER;
