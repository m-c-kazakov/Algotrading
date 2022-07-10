package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
public class DescriptionToOpenADealExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(Set<SpecificationOfStrategy> dataOfStrategies) {

        List<SpecificationOfStrategy> SpecificationOfStrategyElements = dataOfStrategies.stream()
                .toList();

        SpecificationOfStrategy firstParent = SpecificationOfStrategyElements.get(0);
        SpecificationOfStrategy secondParent = SpecificationOfStrategyElements.get(1);

        SpecificationOfStrategy firstChild = firstParent
                .withDescriptionToOpenADeal(secondParent.getDescriptionToOpenADeal());

        SpecificationOfStrategy secondChild = secondParent
                .withDescriptionToOpenADeal(firstParent.getDescriptionToOpenADeal());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
