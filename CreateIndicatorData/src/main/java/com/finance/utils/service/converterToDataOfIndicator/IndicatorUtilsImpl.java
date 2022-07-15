package com.finance.utils.service.converterToDataOfIndicator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndicatorUtilsImpl implements IndicatorUtils {
    @Override
    public List<Integer> trimTheArray(int period, List<Integer> dataOfCandle) {
        return dataOfCandle.subList(period, dataOfCandle.size());
    }
}
