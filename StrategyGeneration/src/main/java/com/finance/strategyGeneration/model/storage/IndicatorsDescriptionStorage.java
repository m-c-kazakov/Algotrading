package com.finance.strategyGeneration.model.storage;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
@EqualsAndHashCode
public class IndicatorsDescriptionStorage {

    List<InformationOfIndicator> informationOfIndicators;

    public List<String> getStringIds() {
        return informationOfIndicators.stream()
                .map(InformationOfIndicator::getId)
                .map(String::valueOf)
                .toList();
    }

    public String getHashCodes() {
        return informationOfIndicators
                .stream()
                .map(InformationOfIndicator::getHashCode)
                .collect(Collectors.joining("_"));
    }
}
