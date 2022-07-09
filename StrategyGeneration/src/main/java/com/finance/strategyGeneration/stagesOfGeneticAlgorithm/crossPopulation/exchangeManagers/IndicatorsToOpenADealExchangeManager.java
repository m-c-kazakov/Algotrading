package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.TypesOfCrosses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorsToOpenADealExchangeManager implements ExchangeManager {

    TypesOfCrosses typesOfCrosses;
    SeparatorCreator createSeparator;

    @Override
    public Stream<DescriptionOfStrategy> execute(Set<DescriptionOfStrategy> dataOfStrategies) {

        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        List<Indicator> firstIndicators = firstParent.getIndicatorsDescriptionToOpenADeal();

        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);
        List<Indicator> secondIndicators = secondParent.getIndicatorsDescriptionToOpenADeal();


        if (!firstIndicators.isEmpty() && !secondIndicators.isEmpty()) {

            int separator = createSeparator.execute(firstIndicators, secondIndicators);

            List<Indicator> firstDescriptionToOpenADeal = typesOfCrosses.singlePointCrossing(firstIndicators,
                    secondIndicators, separator);

            List<Indicator> secondDescriptionToOpenADeal = typesOfCrosses.singlePointCrossing(
                    secondIndicators, firstIndicators, separator);

            DescriptionOfStrategy firstChild = firstParent
                    .withDescriptionToOpenADeal(firstDescriptionToOpenADeal);

            DescriptionOfStrategy secondChild = secondParent
                    .withDescriptionToOpenADeal(secondDescriptionToOpenADeal);

            return Stream.of(firstParent, secondParent, firstChild, secondChild);
        }

        return Stream.of(firstParent, secondParent);
    }
}
