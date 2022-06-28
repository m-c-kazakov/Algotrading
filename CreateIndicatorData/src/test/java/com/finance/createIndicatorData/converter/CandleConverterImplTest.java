package com.finance.createIndicatorData.converter;

import com.finance.createIndicatorData.model.Candle;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CandleConverterImplTest {

    CandleConverterImpl candleConverter = new CandleConverterImpl();


    @Test
    void convertBigDecimalPriceToIntPrice() {
        System.out.println("hashCode: " + this.hashCode());
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        assertThat(candleConverter.convertBigDecimalPriceToIntPrice(price, currencyPair))
                .isEqualTo(100001);
    }

    @Test
    void convertToIntOpenPrice() {
        System.out.println("hashCode: " + this.hashCode());
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        Candle candle = new Candle(1L, currencyPair, 1, null, null, price, price, price, price, 1);

        List<Integer> integers = candleConverter.convertToIntOpenPrice(List.of(candle));
        assertFalse(integers.isEmpty());
        assertThat(integers.get(0)).isEqualTo(100001);
    }

    @Test
    void convertToIntClosePrice() {
        System.out.println("hashCode: " + this.hashCode());
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        Candle candle = new Candle(1L, currencyPair, 1, null, null, price, price, price, price, 1);

        List<Integer> integers = candleConverter.convertToIntClosePrice(List.of(candle));
        assertFalse(integers.isEmpty());
        assertThat(integers.get(0)).isEqualTo(100001);
    }

    @Test
    void convertToIntHighPrice() {
        System.out.println("hashCode: " + this.hashCode());
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        Candle candle = new Candle(1L, currencyPair, 1, null, null, price, price, price, price, 1);

        List<Integer> integers = candleConverter.convertToIntHighPrice(List.of(candle));
        assertFalse(integers.isEmpty());
        assertThat(integers.get(0)).isEqualTo(100001);
    }

    @Test
    void convertToIntLowPrice() {
        System.out.println("hashCode: " + this.hashCode());
        BigDecimal price = new BigDecimal("1.00001");
        CurrencyPair currencyPair = CurrencyPair.EURUSD;

        Candle candle = new Candle(1L, currencyPair, 1, null, null, price, price, price, price, 1);

        List<Integer> integers = candleConverter.convertToIntLowPrice(List.of(candle));
        assertFalse(integers.isEmpty());
        assertThat(integers.get(0)).isEqualTo(100001);
    }
}