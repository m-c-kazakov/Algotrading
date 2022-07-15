package com.finance.utils.service.converterToDataOfIndicator;

import java.util.List;

public interface IndicatorUtils {
    List<Integer> trimTheArray(int period, List<Integer> dataOfCandle);

    List<Integer> increaseIndicatorResultSize(List<Integer> smaResult, int size);

    List<Integer> trimPercentageOfArray(List<Integer> finalDecision, int i);
}
