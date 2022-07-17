package com.finance.strategyGeneration.model.storage;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Value
@EqualsAndHashCode
public class IndicatorsDescriptionStorage {

    List<InformationOfIndicator> informationOfIndicators;

    public List<String> receiveStringIds() {
        return informationOfIndicators.stream()
                .map(InformationOfIndicator::getId)
                .peek(id -> Assert.notNull(id, "Indicator id не может быть null"))
                .map(String::valueOf)
                .toList();
    }

    public String receiveHashCodes() {
        return informationOfIndicators
                .stream()
                .map(InformationOfIndicator::getHashCode)
                .collect(Collectors.joining("_"));
    }
}
