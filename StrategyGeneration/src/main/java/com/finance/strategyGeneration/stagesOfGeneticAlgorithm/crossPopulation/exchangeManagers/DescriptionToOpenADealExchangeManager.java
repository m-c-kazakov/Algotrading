package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class DescriptionToOpenADealExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy firstParent, SpecificationOfStrategy secondParent) {

        SpecificationOfStrategy firstChild = firstParent
                .withDescriptionToOpenADeal(secondParent.getDescriptionToOpenADeal());

        SpecificationOfStrategy secondChild = secondParent
                .withDescriptionToOpenADeal(firstParent.getDescriptionToOpenADeal());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
