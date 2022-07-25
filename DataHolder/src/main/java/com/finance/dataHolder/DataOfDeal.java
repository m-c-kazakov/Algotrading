package com.finance.dataHolder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Класс несет в себе всю необходимую информацию о сделке
 * <p>
 * Все свойства представлены целочисленными типами для удобства работы.
 * Информация о том, сколько знаков после запятой должно быть в числе храниться в валютной паре: CurrencyPair.class
 */
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataOfDeal {
    // TODO Доработать расчет по лотам:
    //  https://blog.roboforex.com/ru/blog/2019/09/17/kak-rasschitat-torgovyj-lot-dlja-otkry/
    long lot;

    int openingPrice;
    int stopLoss;
    int trailingStop;
    int takeProfit;

}
