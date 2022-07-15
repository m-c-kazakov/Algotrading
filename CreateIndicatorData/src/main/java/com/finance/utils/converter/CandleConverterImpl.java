package com.finance.utils.converter;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.utils.model.Candle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CandleConverterImpl implements CandleConverter {

    @Override
    public Integer convertBigDecimalPriceToIntPrice(BigDecimal price, CurrencyPair currencyPair) {
        return price.multiply(currencyPair.getMultiplicand()).intValue();
    }

    @Override
    public List<Integer> convertToIntOpenPrice(List<Candle> candles) {
        CurrencyPair currencyPair = candles.get(0).getTicker();
        return candles.stream()
                .map(candle -> this.convertBigDecimalPriceToIntPrice(candle.getOpen(), currencyPair))
                .toList();
    }

    @Override
    public List<Integer> convertToIntClosePrice(List<Candle> candles) {
        CurrencyPair currencyPair = candles.get(0).getTicker();
        return candles.stream()
                .map(candle -> this.convertBigDecimalPriceToIntPrice(candle.getClose(), currencyPair))
                .toList();
    }

    @Override
    public List<Integer> convertToIntHighPrice(List<Candle> candles) {
        CurrencyPair currencyPair = candles.get(0).getTicker();
        return candles.stream()
                .map(candle -> this.convertBigDecimalPriceToIntPrice(candle.getHigh(), currencyPair))
                .toList();
    }

    @Override
    public List<Integer> convertToIntLowPrice(List<Candle> candles) {
        CurrencyPair currencyPair = candles.get(0).getTicker();
        return candles.stream()
                .map(candle -> this.convertBigDecimalPriceToIntPrice(candle.getLow(), currencyPair))
                .toList();
    }
}
