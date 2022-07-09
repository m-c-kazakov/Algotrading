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
public class IndicatorsToCloseADealExchangeManager implements ExchangeManager {

    TypesOfCrosses typesOfCrosses;
    SeparatorCreator createSeparator;

    @Override
    public Stream<DescriptionOfStrategy> execute(Set<DescriptionOfStrategy> dataOfStrategies) {

        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        List<Indicator> firstIndicators = firstParent.getIndicatorsDescriptionToCloseADeal();

        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);
        List<Indicator> secondIndicators = secondParent.getIndicatorsDescriptionToCloseADeal();

        if (!firstIndicators.isEmpty() && !secondIndicators.isEmpty()) {
            int separator = createSeparator.execute(firstIndicators, secondIndicators);

            List<Indicator> firstDescriptionToCloseADeal = typesOfCrosses.singlePointCrossing(firstIndicators,
                    secondIndicators, separator);

            List<Indicator> secondDescriptionToCloseADeal = typesOfCrosses.singlePointCrossing(
                    secondIndicators, firstIndicators, separator);

            DescriptionOfStrategy firstChild = firstParent
                    .withDescriptionToCloseADeal(firstDescriptionToCloseADeal);

            DescriptionOfStrategy secondChild = secondParent
                    .withDescriptionToCloseADeal(secondDescriptionToCloseADeal);

            return Stream.of(firstParent, secondParent, firstChild, secondChild);
        }

        return Stream.of(firstParent, secondParent);
    }
}
