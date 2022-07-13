package com.finance.strategyGeneration.model.creator;

import com.finance.strategyDescriptionParameters.indicators.IndicatorType;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.storage.IndicatorParametersConfigurationStorage;
import com.finance.strategyGeneration.model.storage.InformationOfCandlesStorage;
import lombok.NonNull;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InformationOfIndicatorCreator {

    public static InformationOfIndicator create(@NonNull IndicatorType indicatorType,
                                                @NonNull InformationOfCandles informationOfCandles,
                                                @NonNull Map<String, String> parameters) {
        Assert.notEmpty(parameters, "parameters у индикатора не может быть пустым");
        InformationOfIndicator informationOfIndicator = InformationOfIndicator.builder()
                .indicatorType(indicatorType)
                .informationOfCandles(new InformationOfCandlesStorage(informationOfCandles))
                .parameters(new IndicatorParametersConfigurationStorage(parameters))
                .build();
        return InformationOfIndicatorCreator.createWithHashCode(informationOfIndicator);
    }

    public static InformationOfIndicator createWithHashCode(InformationOfIndicator informationOfIndicator) {
        String uniqueIdentifier = Stream.of(
                informationOfIndicator.hashCode(),
                informationOfIndicator.receiveIndicatorTypeHashCode(),
                informationOfIndicator.receiveInformationOfCandlesHashCode(),
                informationOfIndicator.receiveParametersHashCode()
        ).map(String::valueOf).collect(Collectors.joining("_"));
        return informationOfIndicator
                .withHashCode(uniqueIdentifier);
    }
}
