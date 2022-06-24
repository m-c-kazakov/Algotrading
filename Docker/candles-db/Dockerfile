FROM postgres:14.4

ENV POSTGRES_USER algotrading
ENV POSTGRES_PASSWORD parol
ENV POSTGRES_DB candles-db
ENV PGDATA /home/maxim/Yandex.Disk/algotrading/data/candles-db/pgdata

VOLUME /home/maxim/Yandex.Disk/algotrading/data/candles-db


# Собрать image
# docker build -t candles-db:candles-db .

# Запустить контейнер
# docker run -d -p 5433:5432 -t --name candles-db candles-db:candles-db

# Посмотреть логи
# docker logs -f candles-db