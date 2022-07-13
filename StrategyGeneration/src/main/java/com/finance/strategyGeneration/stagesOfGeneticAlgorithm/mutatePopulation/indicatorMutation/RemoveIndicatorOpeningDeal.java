package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.indicatorMutation;

import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.creator.IndicatorsDescriptionStorageCreator;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
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
public class RemoveIndicatorOpeningDeal implements Mutation {

    InformationOfIndicatorService informationOfIndicatorService;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        List<InformationOfIndicator> indicators =
                new ArrayList<>(informationOfIndicatorService.findAllById(
                        parentSpecificationOfStrategy.receiveDescriptionToOpenADealStringIds()));

        int numberOfDeletedItems = indicators.size() / 2;

        for (int i = 0; i < numberOfDeletedItems; i++) {

            if (indicators.size() / 2 >= 1) {
                int removeIndex = ThreadLocalRandom.current()
                        .nextInt(indicators.size());
                indicators.remove(removeIndex);
            }
        }


        SpecificationOfStrategy SpecificationOfStrategyAfterMutation =
                parentSpecificationOfStrategy.withDescriptionToOpenADeal(IndicatorsDescriptionStorageCreator.create(indicators));

        return Stream.of(parentSpecificationOfStrategy, SpecificationOfStrategyAfterMutation);
    }
}
