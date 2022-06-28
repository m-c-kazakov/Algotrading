package com.finance.createIndicatorData.converter;

import com.finance.createIndicatorData.dto.DataOfCurrencyPair;
import com.finance.createIndicatorData.model.Candle;

import java.util.List;

public interface DataOfCurrencyPairConverter {

    DataOfCurrencyPair convertToDataOfCurrencyPair(List<Candle> candle);
}
