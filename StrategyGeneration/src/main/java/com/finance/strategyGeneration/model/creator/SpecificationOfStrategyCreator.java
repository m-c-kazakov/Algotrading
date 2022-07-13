package com.finance.strategyGeneration.model.creator;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpecificationOfStrategyCreator {

    public static SpecificationOfStrategy createWithHashCodeAndDataOfCreation(SpecificationOfStrategy specificationOfStrategy) {

        // TODO найти более правильный cпособ формирования hashCode. Реализация Lombok дает не уникальный результат.

        String uniqueIdentifier = Stream.of(
                specificationOfStrategy.hashCode(),
                specificationOfStrategy.getSumOfDealConfigurationData().toString(),
                specificationOfStrategy.getStopLossConfigurationData().toString(),
                specificationOfStrategy.getTrailingStopConfigurationData().toString(),
                specificationOfStrategy.getTakeProfitConfigurationData().toString(),
                specificationOfStrategy.getInformationOfCandles().receiveHashCode(),
                specificationOfStrategy.getDescriptionToOpenADeal().receiveHashCodes(),
                specificationOfStrategy.getDescriptionToCloseADeal().receiveHashCodes()
                )
                .map(String::valueOf)
                .collect(Collectors.joining("_"));
        return specificationOfStrategy
                .withHashCode(uniqueIdentifier)
                .withDateOfCreation(new Date());
    }
}
