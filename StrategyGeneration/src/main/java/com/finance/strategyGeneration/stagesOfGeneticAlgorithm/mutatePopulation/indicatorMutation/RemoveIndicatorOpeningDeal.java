package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.indicatorMutation;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
public class RemoveIndicatorOpeningDeal implements Mutation {

    InformationOfIndicatorService informationOfIndicatorService;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        List<InformationOfIndicator> indicators =
                new ArrayList<>(informationOfIndicatorService.findAllById(
                        parentSpecificationOfStrategy.getDescriptionToOpenADeal()));

        int numberOfDeletedItems = indicators.size() / 2;

        for (int i = 0; i < numberOfDeletedItems; i++) {

            if (indicators.size() / 2 >= 1) {
                int removeIndex = ThreadLocalRandom.current()
                        .nextInt(indicators.size());
                indicators.remove(removeIndex);
            }
        }


        SpecificationOfStrategy SpecificationOfStrategyAfterMutation =
                parentSpecificationOfStrategy.withDescriptionToOpenADeal(
                        indicators.stream().map(InformationOfIndicator::getId).map(String::valueOf).toList());

        return Stream.of(parentSpecificationOfStrategy, SpecificationOfStrategyAfterMutation);
    }
}
