package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@Component
public class TypeOfDealExchangeManager implements ExchangeManager {

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy firstParent, SpecificationOfStrategy secondParent) {

        UnaryOperator<TypeOfDeal> opposite = typeOfDeal -> typeOfDeal == TypeOfDeal.BUY ?
                TypeOfDeal.SELL :
                TypeOfDeal.BUY;

        SpecificationOfStrategy firstChild = firstParent
                .withTypeOfDeal(opposite.apply(firstParent.getTypeOfDeal()));

        SpecificationOfStrategy secondChild = secondParent
                .withTypeOfDeal(opposite.apply(secondParent.getTypeOfDeal()));

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
