package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.dataHolder.DescriptionOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class SymOfDealTypeExchangeManager implements ExchangeManager {

    @Override
    public Stream<DescriptionOfStrategy> execute(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        DescriptionOfStrategy firstChild = firstParent
                .withSumOfDealType(secondParent.getSumOfDealType())
                .withSumOfDealConfigurationData(secondParent.getSumOfDealConfigurationData());

        DescriptionOfStrategy secondChild = secondParent
                .withSumOfDealType(firstParent.getSumOfDealType())
                .withSumOfDealConfigurationData(firstParent.getSumOfDealConfigurationData());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
