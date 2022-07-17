package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class DescriptionToCloseADealExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy firstParent, SpecificationOfStrategy secondParent) {


        SpecificationOfStrategy firstChild = firstParent
                .withDescriptionToCloseADeal(secondParent.getDescriptionToCloseADeal());

        SpecificationOfStrategy secondChild = secondParent
                .withDescriptionToCloseADeal(firstParent.getDescriptionToCloseADeal());

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
