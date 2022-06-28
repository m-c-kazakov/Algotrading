package com.finance.strategyDescriptionParameters;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

/**
 * ХАРАКТЕРИСТИКИ ИНСТРУМЕНТА EUR/USD НА РЫНКЕ FOREX: https://onthemoney.ru/instrumenty_rynka_foreks/valyutnye_pary/valyutnaya_para_eur_usd/
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CurrencyPair {

    // TODO добавить возможность делать стратегии с использованием данных с разных валютных пар, акций и т.д.
    EURUSD(5, BigDecimal.valueOf(Math.pow(10, 5)));


    int numberOfDigitsAfterTheDecimalPoint;
    BigDecimal multiplicand;

}
