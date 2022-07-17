package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class TrailingStopExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy firstParent, SpecificationOfStrategy secondParent) {

        SpecificationOfStrategy firstChild = firstParent
                .withTrailingStopType(secondParent.getTrailingStopType())
                .withTrailingStopConfigurationData(secondParent.getTrailingStopConfigurationData());

        SpecificationOfStrategy secondChild = secondParent
                .withTrailingStopType(firstParent.getTrailingStopType())
                .withTrailingStopConfigurationData(firstParent.getTrailingStopConfigurationData());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
