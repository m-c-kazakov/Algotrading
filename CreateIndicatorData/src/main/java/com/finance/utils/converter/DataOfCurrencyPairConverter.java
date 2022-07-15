package com.finance.utils.converter;

import com.finance.utils.dto.DataOfCurrencyPair;
import com.finance.utils.model.Candle;

import java.util.List;

public interface DataOfCurrencyPairConverter {

    DataOfCurrencyPair convertToDataOfCurrencyPair(List<Candle> candle);
}
