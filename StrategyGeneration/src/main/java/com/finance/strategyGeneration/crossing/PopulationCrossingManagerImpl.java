package com.finance.strategyGeneration.crossing;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.strategyDescriptionParameters.DescriptionToCloseADeal;
import com.finance.strategyGeneration.strategyDescriptionParameters.DescriptionToOpenADeal;
import com.finance.strategyGeneration.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyGeneration.strategyDescriptionParameters.indicators.Indicator;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Методы скрещивания: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationCrossingManagerImpl implements PopulationCrossingManager {


    static List<Function<Set<DataOfStrategy>, List<DataOfStrategy>>> functions = new ArrayList<>();

    static {
        functions.add(PopulationCrossingManagerImpl::exchangeSymOfDealType);
        functions.add(PopulationCrossingManagerImpl::exchangeStopLoss);
        functions.add(PopulationCrossingManagerImpl::exchangeTrailingStop);
        functions.add(PopulationCrossingManagerImpl::exchangeTakeProfit);
        functions.add(PopulationCrossingManagerImpl::exchangeTypeOfDeal);
        functions.add(PopulationCrossingManagerImpl::exchangeDescriptionToOpenADeal);
        functions.add(PopulationCrossingManagerImpl::exchangeIndicatorsToOpenADeal);
        functions.add(PopulationCrossingManagerImpl::exchangeDescriptionToCloseADeal);
        functions.add(PopulationCrossingManagerImpl::exchangeIndicatorsToCloseADeal);
    }

    @Override
    public List<DataOfStrategy> execute(List<DataOfStrategy> population) {
        return Sets.combinations(Sets.newHashSet(population), 2)
                .stream()
                .flatMap(dataOfStrategies -> functions.stream()
                        .flatMap(setListFunction -> setListFunction.apply(dataOfStrategies)
                                .stream()))
                .toList();
    }

    private static List<DataOfStrategy> exchangeIndicatorsToCloseADeal(Set<DataOfStrategy> dataOfStrategies) {
        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        List<Indicator> firstIndicators = firstParent.getIndicatorsDescriptionToCloseADeal();

        DataOfStrategy secondParent = dataOfStrategyElements.get(1);
        List<Indicator> secondIndicators = secondParent.getIndicatorsDescriptionToCloseADeal();

        int separator = createSeparator(firstIndicators, secondIndicators);

        List<Indicator> firstDescriptionToCloseADeal = TypesOfCrosses.singlePointCrossing(firstIndicators,
                secondIndicators, separator);

        List<Indicator> secondDescriptionToCloseADeal = TypesOfCrosses.singlePointCrossing(
                secondIndicators, firstIndicators, separator);

        DataOfStrategy firstChild = firstParent
                .withDescriptionToCloseADeal(new DescriptionToCloseADeal(firstDescriptionToCloseADeal));

        DataOfStrategy secondChild = secondParent
                .withDescriptionToCloseADeal(new DescriptionToCloseADeal(secondDescriptionToCloseADeal));

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private static List<DataOfStrategy> exchangeIndicatorsToOpenADeal(Set<DataOfStrategy> dataOfStrategies) {
        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        List<Indicator> firstIndicators = firstParent.getIndicatorsDescriptionToOpenADeal();

        DataOfStrategy secondParent = dataOfStrategyElements.get(1);
        List<Indicator> secondIndicators = secondParent.getIndicatorsDescriptionToOpenADeal();

        int separator = createSeparator(firstIndicators, secondIndicators);

        List<Indicator> firstDescriptionToOpenADeal = TypesOfCrosses.singlePointCrossing(firstIndicators,
                secondIndicators, separator);

        List<Indicator> secondDescriptionToOpenADeal = TypesOfCrosses.singlePointCrossing(
                secondIndicators, firstIndicators, separator);

        DataOfStrategy firstChild = firstParent
                .withDescriptionToOpenADeal(new DescriptionToOpenADeal(firstDescriptionToOpenADeal));

        DataOfStrategy secondChild = secondParent
                .withDescriptionToOpenADeal(new DescriptionToOpenADeal(secondDescriptionToOpenADeal));

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private static int createSeparator(List<Indicator> firstIndicators, List<Indicator> secondIndicators) {
        int firstListSize = firstIndicators.size();
        int secondListSize = secondIndicators.size();

        if (firstListSize < secondListSize) {
            return ThreadLocalRandom.current()
                    .nextInt(1, firstListSize);
        } else {
            return ThreadLocalRandom.current()
                    .nextInt(1, secondListSize);
        }
    }

    private static List<DataOfStrategy> exchangeDescriptionToCloseADeal(Set<DataOfStrategy> dataOfStrategies) {
        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        DataOfStrategy secondParent = dataOfStrategyElements.get(1);

        DataOfStrategy firstChild = firstParent
                .withDescriptionToCloseADeal(secondParent.getDescriptionToCloseADeal());

        DataOfStrategy secondChild = secondParent
                .withDescriptionToCloseADeal(firstParent.getDescriptionToCloseADeal());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private static List<DataOfStrategy> exchangeDescriptionToOpenADeal(Set<DataOfStrategy> dataOfStrategies) {
        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        DataOfStrategy secondParent = dataOfStrategyElements.get(1);

        DataOfStrategy firstChild = firstParent
                .withDescriptionToOpenADeal(secondParent.getDescriptionToOpenADeal());

        DataOfStrategy secondChild = secondParent
                .withDescriptionToOpenADeal(firstParent.getDescriptionToOpenADeal());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private static List<DataOfStrategy> exchangeTypeOfDeal(Set<DataOfStrategy> dataOfStrategies) {
        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        DataOfStrategy secondParent = dataOfStrategyElements.get(1);

        UnaryOperator<TypeOfDeal> opposite = typeOfDeal -> typeOfDeal == TypeOfDeal.BUY ?
                TypeOfDeal.SELL :
                TypeOfDeal.BUY;

        DataOfStrategy firstChild = firstParent
                .withTypeOfDeal(opposite.apply(firstParent.getTypeOfDeal()));

        DataOfStrategy secondChild = secondParent
                .withTypeOfDeal(opposite.apply(secondParent.getTypeOfDeal()));

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private static List<DataOfStrategy> exchangeTakeProfit(Set<DataOfStrategy> dataOfStrategies) {
        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        DataOfStrategy secondParent = dataOfStrategyElements.get(1);

        DataOfStrategy firstChild = firstParent
                .withTakeProfitType(secondParent.getTakeProfitType())
                .withTakeProfitConfigurationData(secondParent.getTakeProfitConfigurationData());

        DataOfStrategy secondChild = secondParent
                .withTakeProfitType(firstParent.getTakeProfitType())
                .withTakeProfitConfigurationData(firstParent.getTakeProfitConfigurationData());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private static List<DataOfStrategy> exchangeTrailingStop(Set<DataOfStrategy> dataOfStrategies) {
        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        DataOfStrategy secondParent = dataOfStrategyElements.get(1);

        DataOfStrategy firstChild = firstParent
                .withTrailingStopType(secondParent.getTrailingStopType())
                .withTrailingStopConfigurationData(secondParent.getTrailingStopConfigurationData());

        DataOfStrategy secondChild = secondParent
                .withTrailingStopType(firstParent.getTrailingStopType())
                .withTrailingStopConfigurationData(firstParent.getTrailingStopConfigurationData());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private static List<DataOfStrategy> exchangeStopLoss(Set<DataOfStrategy> dataOfStrategies) {
        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        DataOfStrategy secondParent = dataOfStrategyElements.get(1);

        DataOfStrategy firstChild = firstParent
                .withStopLossType(secondParent.getStopLossType())
                .withStopLossConfigurationData(secondParent.getStopLossConfigurationData());

        DataOfStrategy secondChild = secondParent
                .withStopLossType(firstParent.getStopLossType())
                .withStopLossConfigurationData(firstParent.getStopLossConfigurationData());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private static List<DataOfStrategy> exchangeSymOfDealType(Set<DataOfStrategy> dataOfStrategies) {

        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DataOfStrategy firstParent = dataOfStrategyElements.get(0);
        DataOfStrategy secondParent = dataOfStrategyElements.get(1);

        DataOfStrategy firstChild = firstParent
                .withSumOfDealType(secondParent.getSumOfDealType())
                .withSumOfDealConfigurationData(secondParent.getSumOfDealConfigurationData());

        DataOfStrategy secondChild = secondParent
                .withSumOfDealType(firstParent.getSumOfDealType())
                .withSumOfDealConfigurationData(firstParent.getSumOfDealConfigurationData());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }


}
