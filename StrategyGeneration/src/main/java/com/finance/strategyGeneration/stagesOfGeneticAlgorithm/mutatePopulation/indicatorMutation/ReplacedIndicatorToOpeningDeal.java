package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.indicatorMutation;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.GeneratorOfRandomIndicators;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReplacedIndicatorToOpeningDeal implements Mutation {

    GeneratorOfRandomIndicators generatorOfRandomIndicators;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {
        List<Indicator> indicators = new ArrayList<>(parentDescriptionOfStrategy.getIndicatorsDescriptionToOpenADeal());

        int bound = Math.max(indicators.size() / 2, 1);
        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current().nextInt(bound), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current().nextInt(indicators.size());
            // TODO Добавить ограничение на стратегии где для анализа используется только 1 валюта
            Indicator randomIndicator = generatorOfRandomIndicators.getRandomIndicator(CurrencyPair.getRandomCurrencyPair());
            indicators.set(replacedIndex, randomIndicator);
        }


        DescriptionOfStrategy descriptionOfStrategyAfterMutation = parentDescriptionOfStrategy.withDescriptionToOpenADeal(
                indicators);

        return Stream.of(parentDescriptionOfStrategy, descriptionOfStrategyAfterMutation);
    }
}
