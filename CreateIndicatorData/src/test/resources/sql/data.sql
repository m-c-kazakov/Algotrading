SET datestyle = DMY;
COPY candles (ticker, per, date, time, open, high, low, close, vol) FROM PROGRAM 'cat /var/lib/candles-data/M1/EURUSD_220601_220630.txt' DELIMITER ';' CSV HEADER;
COPY candles (ticker, per, date, time, open, high, low, close, vol) FROM PROGRAM 'cat /var/lib/candles-data/H1/EURUSD_220601_220630.txt' DELIMITER ';' CSV HEADER;
-- COPY candles(ticker, per, date, time, open, high, low, close, vol) FROM PROGRAM 'cat /home/maxim/Yandex.Disk/algotrading/data/candles-data/M1/*.txt' DELIMITER ';' CSV HEADER;
-- COPY candles(ticker, per, date, time, open, high, low, close, vol) FROM PROGRAM 'cat /home/maxim/Yandex.Disk/algotrading/data/candles-data/H1/*.txt' DELIMITER ';' CSV HEADER;


-- insert into candles(TICK ER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:01:00', 1.0736000, 1.0736000, 1.0734200, 1.0734200, 9);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:02:00', 1.0733900, 1.0735000, 1.0731800, 1.0734600, 7);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:03:00', 1.0733800, 1.0734500, 1.0733300, 1.0734000, 8);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:04:00', 1.0733200, 1.0734300, 1.0732700, 1.0734200, 17);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:05:00', 1.0733200, 1.0736000, 1.0733000, 1.0733900, 65);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:06:00', 1.0734400, 1.0735500, 1.0732500, 1.0733100, 93);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:07:00', 1.0733100, 1.0734200, 1.0731200, 1.0731200, 111);
--
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:01:00', 1.0736000, 1.0736000, 1.0734200, 1.0734200, 9);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:02:00', 1.0733900, 1.0735000, 1.0731800, 1.0734600, 7);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:03:00', 1.0733800, 1.0734500, 1.0733300, 1.0734000, 8);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:04:00', 1.0733200, 1.0734300, 1.0732700, 1.0734200, 17);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:05:00', 1.0733200, 1.0736000, 1.0733000, 1.0733900, 65);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:06:00', 1.0734400, 1.0735500, 1.0732500, 1.0733100, 93);
-- insert into candles(TICKER, PER, DATE, TIME, OPEN, HIGH, LOW, CLOSE, VOL) values ('EURUSD', 1, '30/05/22', '00:07:00', 1.0733100, 1.0734200, 1.0731200, 1.0731200, 111);


-- insert into data_of_indicators(id, decision_by_deal, indicator_type, currency_pair, time_frame) values(1, '{99999, 7777777}', 'SMA', 'EURUSD', 'M1');