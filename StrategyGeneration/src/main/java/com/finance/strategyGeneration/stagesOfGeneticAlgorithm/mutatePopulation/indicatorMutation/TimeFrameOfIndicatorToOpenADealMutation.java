package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.indicatorMutation;

import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.creator.IndicatorsDescriptionStorageCreator;
import com.finance.strategyGeneration.model.creator.InformationOfCandlesStorageCreator;
import com.finance.strategyGeneration.service.InformationOfCandleService;
import com.finance.strategyGeneration.service.InformationOfIndicatorService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeFrameOfIndicatorToOpenADealMutation implements Mutation {

    InformationOfIndicatorService informationOfIndicatorService;
    InformationOfCandleService informationOfCandleService;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        List<InformationOfIndicator> indicators = new ArrayList<>(informationOfIndicatorService.findAllById(
                parentSpecificationOfStrategy.receiveDescriptionToOpenADealStringIds()));

        int bound = Math.max(indicators.size() / 2, 1);
        int numberOfReplacedItems = Math.max(ThreadLocalRandom.current()
                .nextInt(bound), 1);

        for (int i = 0; i < numberOfReplacedItems; i++) {

            int replacedIndex = ThreadLocalRandom.current()
                    .nextInt(indicators.size());
            InformationOfIndicator indicator = indicators.get(replacedIndex).toBuilder().build();
            Assert.notNull(indicator.receiveCurrencyPair(), "CurrencyPair не может быть null");
            Assert.notNull(indicator.receiveTimeFrame(), "TimeFrame не может быть null");

            InformationOfCandles informationOfCandles = informationOfCandleService.create(
                    indicator
                            .getInformationOfCandles()
                            .withTimeFrame(TimeFrame.getRandomTimeFrame())
                            .getInformationOfCandles());

            InformationOfIndicator informationOfIndicator =
                    informationOfIndicatorService.create(
                            indicator.withInformationOfCandles(InformationOfCandlesStorageCreator.create(informationOfCandles)));

            indicators.set(replacedIndex, informationOfIndicator);
        }

        SpecificationOfStrategy specificationOfStrategyAfterMutation =
                parentSpecificationOfStrategy.withDescriptionToOpenADeal(
                        IndicatorsDescriptionStorageCreator.create(indicators));

        return Stream.of(parentSpecificationOfStrategy, specificationOfStrategyAfterMutation);
    }
}
