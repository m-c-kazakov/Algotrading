package com.finance.utils.service.converterToDataOfIndicator;

import java.util.List;

public interface IndicatorUtils {
    List<Integer> trimTheArray(int period, List<Integer> dataOfCandle);

    List<Integer> increaseIndicatorResultSize(List<Integer> smaResult, int size);

    List<Byte> trimPercentageOfArray(List<Byte> finalDecision, int i);
}
