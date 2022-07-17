package com.finance.strategyGeneration.model.creator;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpecificationOfStrategyCreator {

    public static SpecificationOfStrategy createWithHashCodeAndDataOfCreation(SpecificationOfStrategy specificationOfStrategy) {

        return specificationOfStrategy
                .withHashCode(generateHashCode(specificationOfStrategy))
                .withDateOfCreation(new Date());
    }


    public static String generateHashCode(SpecificationOfStrategy specificationOfStrategy) {
        // TODO найти более правильный cпособ формирования hashCode. Реализация Lombok дает не уникальный результат.

        return Stream.of(
                        specificationOfStrategy.hashCode(),
                        specificationOfStrategy.getSumOfDealConfigurationData().toString(),
                        specificationOfStrategy.getStopLossConfigurationData().toString(),
                        specificationOfStrategy.getTrailingStopConfigurationData().toString(),
                        specificationOfStrategy.getTakeProfitConfigurationData().toString(),
                        specificationOfStrategy.receiveInformationOfCandles().getHashCode(),
                        specificationOfStrategy.getDescriptionToOpenADeal().receiveHashCodes(),
                        specificationOfStrategy.getDescriptionToCloseADeal().receiveHashCodes()
                )
                .map(String::valueOf)
                .collect(Collectors.joining("_"));
    }
}
