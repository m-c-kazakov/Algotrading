package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
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
    public Stream<SpecificationOfStrategy> execute(Set<SpecificationOfStrategy> dataOfStrategies) {

        List<SpecificationOfStrategy> SpecificationOfStrategyElements = dataOfStrategies.stream()
                .toList();

        SpecificationOfStrategy firstParent = SpecificationOfStrategyElements.get(0);
        List<InformationOfIndicator> firstIndicators = firstParent.receiveDescriptionToCloseADealIndicators();

        SpecificationOfStrategy secondParent = SpecificationOfStrategyElements.get(1);
        List<InformationOfIndicator> secondIndicators = secondParent.receiveDescriptionToCloseADealIndicators();

        if (!firstIndicators.isEmpty() && !secondIndicators.isEmpty()) {
            int separator = createSeparator.execute(firstIndicators, secondIndicators);

            SpecificationOfStrategy firstChild =
                    generateSpecificationOfStrategy(firstIndicators, secondIndicators, separator, firstParent);

            SpecificationOfStrategy secondChild =
                    generateSpecificationOfStrategy(secondIndicators, firstIndicators, separator, secondParent);

            return Stream.of(firstParent, secondParent, firstChild, secondChild);
        }

        return Stream.of(firstParent, secondParent);
    }

    private SpecificationOfStrategy generateSpecificationOfStrategy(List<InformationOfIndicator> secondIndicators, List<InformationOfIndicator> firstIndicators, int separator, SpecificationOfStrategy parent) {
        List<InformationOfIndicator> secondDescriptionToCloseADeal = typesOfCrosses.singlePointCrossing(
                secondIndicators, firstIndicators, separator);
        return parent.withDescriptionToCloseADealIndicators(secondDescriptionToCloseADeal);
    }
}
