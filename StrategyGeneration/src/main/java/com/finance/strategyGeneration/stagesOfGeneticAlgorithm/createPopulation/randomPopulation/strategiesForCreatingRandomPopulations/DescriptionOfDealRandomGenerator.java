package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations;

import com.finance.strategyDescriptionParameters.CurrencyPair;
import com.finance.strategyDescriptionParameters.TimeFrame;
import com.finance.strategyGeneration.model.InformationOfCandles;
import com.finance.strategyGeneration.model.InformationOfIndicator;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.InformationOfCandleService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.generatorRandomIndicators.GeneratorOfRandomIndicators;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    // TODO вынести в property
    Boolean isNeedToCreateDescriptionToCloseADeal = false;

    @Override
    public void add(SpecificationOfStrategy.SpecificationOfStrategyBuilder specificationOfStrategyBuilder) {

        CurrencyPair currencyPair = CurrencyPair.getRandomCurrencyPair();


        List<InformationOfIndicator> descriptionToOpenADeal = generateIndicators(currencyPair);
        List<InformationOfIndicator> descriptionToCloseADeal = isNeedToCreateDescriptionToCloseADeal ? generateIndicators(
                currencyPair) : List.of();
        specificationOfStrategyBuilder
                .descriptionToOpenADeal(getIndicatorsId(descriptionToOpenADeal))
                .descriptionToCloseADeal(getIndicatorsId(descriptionToCloseADeal));

        TimeFrame timeFrame = findMinimalTimeFrame(descriptionToOpenADeal, descriptionToCloseADeal);

        InformationOfCandles informationOfCandles = informationOfCandleService.create(timeFrame, currencyPair);
        specificationOfStrategyBuilder.informationOfCandlesId(String.valueOf(informationOfCandles.getId()));

    }

    private List<String> getIndicatorsId(List<InformationOfIndicator> descriptionADeal) {
        return descriptionADeal.stream().map(InformationOfIndicator::getId).map(String::valueOf).toList();
    }

    private TimeFrame findMinimalTimeFrame(List<InformationOfIndicator> descriptionToOpenADeal,
                                           List<InformationOfIndicator> descriptionToCloseADeal) {
        List<TimeFrame> timeFrames = Stream.of(descriptionToOpenADeal, descriptionToCloseADeal)
                .filter(indicators -> !isEmpty(indicators))
                .flatMap(List::stream)
                .map(InformationOfIndicator::getInformationOfCandlesId)
                .map(Long::valueOf)
                .map(informationOfCandleService::findById)
                .map(InformationOfCandles::getTimeFrame)
                .toList();

        return TimeFrame.getMinimalTimeFrame(timeFrames);
    }

    private List<InformationOfIndicator> generateIndicators(CurrencyPair currencyPair) {
        int numberOfIndicators = ThreadLocalRandom.current()
                .nextInt(1, 6);
        return Stream.iterate(0, integer -> integer < numberOfIndicators, integer -> integer + 1)
                .map(integer -> generatorOfRandomIndicators.getRandomIndicator(currencyPair))
                .toList();
    }
}
