package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class StopLossExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy firstParent, SpecificationOfStrategy secondParent) {

        SpecificationOfStrategy firstChild = firstParent
                .withStopLossType(secondParent.getStopLossType())
                .withStopLossConfigurationData(secondParent.getStopLossConfigurationData());

        SpecificationOfStrategy secondChild = secondParent
                .withStopLossType(firstParent.getStopLossType())
                .withStopLossConfigurationData(firstParent.getStopLossConfigurationData());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
