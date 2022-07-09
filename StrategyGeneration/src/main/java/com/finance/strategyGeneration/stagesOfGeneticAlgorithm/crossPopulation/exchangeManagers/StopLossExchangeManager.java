package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.dataHolder.DescriptionOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class StopLossExchangeManager implements ExchangeManager {

    @Override
    public Stream<DescriptionOfStrategy> execute(Set<DescriptionOfStrategy> dataOfStrategies) {

        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        DescriptionOfStrategy firstChild = firstParent
                .withStopLossType(secondParent.getStopLossType())
                .withStopLossConfigurationData(secondParent.getStopLossConfigurationData());

        DescriptionOfStrategy secondChild = secondParent
                .withStopLossType(firstParent.getStopLossType())
                .withStopLossConfigurationData(firstParent.getStopLossConfigurationData());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
