package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class SymOfDealTypeExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy firstParent, SpecificationOfStrategy secondParent) {

        SpecificationOfStrategy firstChild = firstParent
                .withSumOfDealType(secondParent.getSumOfDealType())
                .withSumOfDealConfigurationData(secondParent.getSumOfDealConfigurationData());

        SpecificationOfStrategy secondChild = secondParent
                .withSumOfDealType(firstParent.getSumOfDealType())
                .withSumOfDealConfigurationData(firstParent.getSumOfDealConfigurationData());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
