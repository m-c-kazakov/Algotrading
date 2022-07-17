package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class TakeProfitExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy firstParent, SpecificationOfStrategy secondParent) {

        SpecificationOfStrategy firstChild = firstParent
                .withTakeProfitType(secondParent.getTakeProfitType())
                .withTakeProfitConfigurationData(secondParent.getTakeProfitConfigurationData());

        SpecificationOfStrategy secondChild = secondParent
                .withTakeProfitType(firstParent.getTakeProfitType())
                .withTakeProfitConfigurationData(firstParent.getTakeProfitConfigurationData());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
