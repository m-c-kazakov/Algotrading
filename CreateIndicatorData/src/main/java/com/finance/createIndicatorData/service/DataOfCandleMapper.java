package com.finance.createIndicatorData.service;

import com.finance.dataHolder.DataOfCandle;
import com.finance.strategyDescriptionParameters.TypeOfBar;

import java.util.List;
import java.util.Map;

public interface DataOfCandleMapper {
    List<DataOfCandle> mapTo(Map<TypeOfBar, List<Integer>> candles);
}
