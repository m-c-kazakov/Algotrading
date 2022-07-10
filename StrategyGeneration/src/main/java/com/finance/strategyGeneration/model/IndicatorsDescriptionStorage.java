package com.finance.strategyGeneration.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;

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
}
