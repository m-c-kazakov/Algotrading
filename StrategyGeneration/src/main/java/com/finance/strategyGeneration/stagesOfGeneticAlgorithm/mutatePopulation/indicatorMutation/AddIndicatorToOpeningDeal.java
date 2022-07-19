package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.indicatorMutation;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.creator.IndicatorsDescriptionStorageCreator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.GeneratorOfRandomIndicators;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;


@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddIndicatorToOpeningDeal implements Mutation {

    GeneratorOfRandomIndicators generatorOfRandomIndicators;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        List<InformationOfIndicator> indicators = parentSpecificationOfStrategy.getOpenADealInformationOfIndicators();


        int bound = Math.max(indicators.size() / 3, 1);
        int numberOfAddedItems = Math.max(ThreadLocalRandom.current()
                .nextInt(bound), 1);

        for (int i = 0; i < numberOfAddedItems; i++) {

            if (indicators.size() < 6) {
                InformationOfIndicator randomIndicator =
                        generatorOfRandomIndicators.createRandomIndicator(CurrencyPair.getRandomCurrencyPair());
                indicators.add(randomIndicator);
            }
        }


        SpecificationOfStrategy SpecificationOfStrategyAfterMutation =
                parentSpecificationOfStrategy.withDescriptionToOpenADeal(
                        IndicatorsDescriptionStorageCreator.create(indicators));

        return Stream.of(parentSpecificationOfStrategy, SpecificationOfStrategyAfterMutation);
    }
}
