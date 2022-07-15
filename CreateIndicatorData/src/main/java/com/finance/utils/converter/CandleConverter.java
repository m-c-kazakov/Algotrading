package com.finance.utils.converter;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.utils.model.Candle;

import java.math.BigDecimal;
import java.util.List;

public interface CandleConverter {
    Integer convertBigDecimalPriceToIntPrice(BigDecimal price, CurrencyPair currencyPair);

    List<Integer> convertToIntOpenPrice(List<Candle> candles);

    List<Integer> convertToIntClosePrice(List<Candle> candles);

    List<Integer> convertToIntHighPrice(List<Candle> candles);

    List<Integer> convertToIntLowPrice(List<Candle> candles);
}
