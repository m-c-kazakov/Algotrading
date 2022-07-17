package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.TypesOfCrosses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IndicatorsToOpenADealExchangeManager implements ExchangeManager {

    TypesOfCrosses typesOfCrosses;
    SeparatorCreator createSeparator;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy firstParent, SpecificationOfStrategy secondParent) {

        List<InformationOfIndicator> firstIndicators = firstParent.receiveDescriptionToOpenADealIndicators();
        List<InformationOfIndicator> secondIndicators = secondParent.receiveDescriptionToOpenADealIndicators();


        if (!firstIndicators.isEmpty() && !secondIndicators.isEmpty()) {

            int separator = createSeparator.execute(firstIndicators, secondIndicators);

            SpecificationOfStrategy firstChild = generateChild(firstIndicators, secondIndicators, separator, firstParent);

            SpecificationOfStrategy secondChild = generateChild(secondIndicators, firstIndicators, separator, secondParent);

            return Stream.of(firstParent, secondParent, firstChild, secondChild);
        }

        return Stream.of(firstParent, secondParent);
    }

    private SpecificationOfStrategy generateChild(List<InformationOfIndicator> secondIndicators, List<InformationOfIndicator> firstIndicators, int separator, SpecificationOfStrategy parent) {
        List<InformationOfIndicator> secondDescriptionToOpenADeal = typesOfCrosses
                .singlePointCrossing(secondIndicators, firstIndicators, separator);

        return parent.withDescriptionToOpenADealIndicators(secondDescriptionToOpenADeal);
    }
}
