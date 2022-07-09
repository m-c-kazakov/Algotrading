package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

@Component
public class TypeOfDealExchangeManager implements ExchangeManager {

    @Override
    public Stream<DescriptionOfStrategy> execute(Set<DescriptionOfStrategy> dataOfStrategies) {

        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        UnaryOperator<TypeOfDeal> opposite = typeOfDeal -> typeOfDeal == TypeOfDeal.BUY ?
                TypeOfDeal.SELL :
                TypeOfDeal.BUY;

        DescriptionOfStrategy firstChild = firstParent
                .withTypeOfDeal(opposite.apply(firstParent.getTypeOfDeal()));

        DescriptionOfStrategy secondChild = secondParent
                .withTypeOfDeal(opposite.apply(secondParent.getTypeOfDeal()));

        return Stream.of(firstParent, secondParent, firstChild, secondChild);
    }
}
