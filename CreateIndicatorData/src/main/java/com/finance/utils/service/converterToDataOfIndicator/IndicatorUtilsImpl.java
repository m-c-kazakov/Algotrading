package com.finance.utils.service.converterToDataOfIndicator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class IndicatorUtilsImpl implements IndicatorUtils {
    @Override
    public List<Integer> trimTheArray(int period, List<Integer> dataOfCandle) {
        return dataOfCandle.subList(period, dataOfCandle.size());
    }

    @Override
    public List<Integer> increaseIndicatorResultSize(List<Integer> smaResult, int size) {
        Integer templateValue = smaResult.get(0);
        List<Integer> templateList =
                Stream.iterate(0, i -> i < size - smaResult.size(), i -> i + 1).map(i -> templateValue).collect(
                        Collectors.toCollection(ArrayList::new));
        templateList.addAll(smaResult);

        return templateList;
    }

    @Override
    public List<Integer> trimPercentageOfArray(List<Integer> finalDecision, int percentage) {

        int numberRemoverElements = finalDecision.size() * percentage / 100;

        return finalDecision.subList(numberRemoverElements, finalDecision.size());
    }
}
