package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.creator.IndicatorsDescriptionStorageCreator;
import com.finance.strategyGeneration.model.creator.InformationOfCandlesStorageCreator;
import com.finance.strategyGeneration.model.storage.IndicatorsDescriptionStorage;
import com.finance.strategyGeneration.model.storage.InformationOfCandlesStorage;
import com.finance.strategyGeneration.service.InformationOfCandleService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.GeneratorOfRandomIndicators;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DescriptionOfDealRandomGenerator implements RandomStrategyParams {

    GeneratorOfRandomIndicators generatorOfRandomIndicators;
    InformationOfCandleService informationOfCandleService;

    @NonFinal
    @Value("${app.populationCreation.isNeedToCreateDescriptionToCloseADeal}")
    Boolean isNeedToCreateDescriptionToCloseADeal;

    @Override
    public void add(SpecificationOfStrategy.SpecificationOfStrategyBuilder specificationOfStrategyBuilder) {

        CurrencyPair currencyPair = CurrencyPair.getRandomCurrencyPair();


        IndicatorsDescriptionStorage descriptionToOpenADeal = generateIndicators(currencyPair);


        IndicatorsDescriptionStorage descriptionToCloseADeal =
                isNeedToCreateDescriptionToCloseADeal ? generateIndicators(
                        currencyPair) : IndicatorsDescriptionStorageCreator.create();

        specificationOfStrategyBuilder
                .descriptionToOpenADeal(descriptionToOpenADeal)
                .descriptionToCloseADeal(descriptionToCloseADeal);

        TimeFrame timeFrame = findMinimalTimeFrame(descriptionToOpenADeal, descriptionToCloseADeal);

        InformationOfCandles informationOfCandles = informationOfCandleService.create(timeFrame, currencyPair);
        specificationOfStrategyBuilder.informationOfCandles(
                InformationOfCandlesStorageCreator.create(informationOfCandles));

    }

    private TimeFrame findMinimalTimeFrame(IndicatorsDescriptionStorage descriptionToOpenADeal,
                                           IndicatorsDescriptionStorage descriptionToCloseADeal) {
        List<TimeFrame> timeFrames = Stream.of(descriptionToOpenADeal, descriptionToCloseADeal)
                .map(IndicatorsDescriptionStorage::getInformationOfIndicators)
                .filter(indicators -> !isEmpty(indicators))
                .flatMap(List::stream)
                .map(InformationOfIndicator::getInformationOfCandles)
                .map(InformationOfCandlesStorage::receiveTimeFrame)
                .toList();

        return TimeFrame.getMinimalTimeFrame(timeFrames);
    }

    private IndicatorsDescriptionStorage generateIndicators(@NonNull CurrencyPair currencyPair) {

        int numberOfIndicators = ThreadLocalRandom.current()
                .nextInt(1, 6);

        List<InformationOfIndicator> informationOfIndicators =
                Stream.iterate(0, integer -> integer < numberOfIndicators, integer -> integer + 1)
                        .map(integer -> generatorOfRandomIndicators.createRandomIndicator(currencyPair))
                        .toList();


        return IndicatorsDescriptionStorageCreator.create(informationOfIndicators);
    }
}
