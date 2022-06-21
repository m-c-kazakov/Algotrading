package com.finance.createIndicatorData.service.converterToDataOfIndicator.indicators;

import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;

import java.util.List;

public interface SMAIndicator {
    int calculateSma(int period, List<Integer> dataOfCandle, int cursor);

    List<Integer> sequentialGenerationSmaResult(int period, List<Integer> dataOfCandle);

    List<Integer> parallelGenerationSmaResult(int period, List<Integer> dataOfCandle);

    List<Integer> getDecision(TypeOfDeal typeOfDeal, Indicator indicator, List<Integer> dataOfCandle, List<Integer> smaResult);
}
