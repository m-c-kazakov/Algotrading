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
public class ReplacedIndicatorToOpeningDeal implements Mutation {

    GeneratorOfRandomIndicators generatorOfRandomIndicators;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        List<InformationOfIndicator> indicators = parentSpecificationOfStrategy.getOpenADealInformationOfIndicators();

        int bound = Math.max(indicators.size() / 2, 1);
        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current().nextInt(bound), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current().nextInt(indicators.size());
            InformationOfIndicator randomIndicator =
                    generatorOfRandomIndicators.createRandomIndicator(CurrencyPair.getRandomCurrencyPair());
            indicators.set(replacedIndex, randomIndicator);
        }


        SpecificationOfStrategy SpecificationOfStrategyAfterMutation =
                parentSpecificationOfStrategy.withDescriptionToOpenADeal(
                        IndicatorsDescriptionStorageCreator.create(indicators));

        return Stream.of(parentSpecificationOfStrategy, SpecificationOfStrategyAfterMutation);
    }
}
