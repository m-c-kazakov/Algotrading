package com.finance.createIndicatorData.service.converterToDataOfIndicator;

import java.util.List;

public interface IndicatorUtils {
    List<Integer> trimTheArray(int period, List<Integer> dataOfCandle);
}
