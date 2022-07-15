package com.finance.utils.converter;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.utils.model.Candle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CandleConverterImplTest {

    CandleConverterImpl candleConverter = new CandleConverterImpl();

    @Test
    void convertBigDecimalPriceToIntPrice() {
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        assertThat(candleConverter.convertBigDecimalPriceToIntPrice(price, currencyPair))
                .isEqualTo(100001);
    }

    @Test
    void convertToIntOpenPrice() {
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        Candle candle = new Candle(1L, currencyPair, 1, null, null, price, price, price, price, 1);

        List<Integer> integers = candleConverter.convertToIntOpenPrice(List.of(candle));
        assertFalse(integers.isEmpty());
        assertThat(integers.get(0)).isEqualTo(100001);
    }

    @Test
    void convertToIntClosePrice() {
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        Candle candle = new Candle(1L, currencyPair, 1, null, null, price, price, price, price, 1);

        List<Integer> integers = candleConverter.convertToIntClosePrice(List.of(candle));
        assertFalse(integers.isEmpty());
        assertThat(integers.get(0)).isEqualTo(100001);
    }

    @Test
    void convertToIntHighPrice() {
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        Candle candle = new Candle(1L, currencyPair, 1, null, null, price, price, price, price, 1);

        List<Integer> integers = candleConverter.convertToIntHighPrice(List.of(candle));
        assertFalse(integers.isEmpty());
        assertThat(integers.get(0)).isEqualTo(100001);
    }

    @Test
    void convertToIntLowPrice() {
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        Candle candle = new Candle(1L, currencyPair, 1, null, null, price, price, price, price, 1);

        List<Integer> integers = candleConverter.convertToIntLowPrice(List.of(candle));
        assertFalse(integers.isEmpty());
        assertThat(integers.get(0)).isEqualTo(100001);
    }
}