package com.finance.strategyGeneration.crossing;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyDescriptionParameters.indicators.Indicator;
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

    List<Function<Set<DescriptionOfStrategy>, List<DescriptionOfStrategy>>> functions = new ArrayList<>();
    TypesOfCrossesImpl typesOfCrosses;

    {
        functions.add(this::exchangeSymOfDealType);
        functions.add(this::exchangeStopLoss);
        functions.add(this::exchangeTrailingStop);
        functions.add(this::exchangeTakeProfit);
        functions.add(this::exchangeTypeOfDeal);
        functions.add(this::exchangeDescriptionToOpenADeal);
        functions.add(this::exchangeIndicatorsToOpenADeal);
        functions.add(this::exchangeDescriptionToCloseADeal);
        functions.add(this::exchangeIndicatorsToCloseADeal);
    }

    private List<DescriptionOfStrategy> exchangeIndicatorsToCloseADeal(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        List<Indicator> firstIndicators = firstParent.getIndicatorsDescriptionToCloseADeal();

        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);
        List<Indicator> secondIndicators = secondParent.getIndicatorsDescriptionToCloseADeal();

        if (!firstIndicators.isEmpty() && !secondIndicators.isEmpty()) {
            int separator = createSeparator(firstIndicators, secondIndicators);

            List<Indicator> firstDescriptionToCloseADeal = typesOfCrosses.singlePointCrossing(firstIndicators,
                    secondIndicators, separator);

            List<Indicator> secondDescriptionToCloseADeal = typesOfCrosses.singlePointCrossing(
                    secondIndicators, firstIndicators, separator);

            DescriptionOfStrategy firstChild = firstParent
                    .withDescriptionToCloseADeal(firstDescriptionToCloseADeal);

            DescriptionOfStrategy secondChild = secondParent
                    .withDescriptionToCloseADeal(secondDescriptionToCloseADeal);

            return List.of(firstParent, secondParent, firstChild, secondChild);
        }

        return List.of(firstParent, secondParent);
    }

    private List<DescriptionOfStrategy> exchangeIndicatorsToOpenADeal(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        List<Indicator> firstIndicators = firstParent.getIndicatorsDescriptionToOpenADeal();

        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);
        List<Indicator> secondIndicators = secondParent.getIndicatorsDescriptionToOpenADeal();

        int separator = createSeparator(firstIndicators, secondIndicators);

        List<Indicator> firstDescriptionToOpenADeal = typesOfCrosses.singlePointCrossing(firstIndicators,
                secondIndicators, separator);

        List<Indicator> secondDescriptionToOpenADeal = typesOfCrosses.singlePointCrossing(
                secondIndicators, firstIndicators, separator);

        DescriptionOfStrategy firstChild = firstParent
                .withDescriptionToOpenADeal(firstDescriptionToOpenADeal);

        DescriptionOfStrategy secondChild = secondParent
                .withDescriptionToOpenADeal(secondDescriptionToOpenADeal);

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private int createSeparator(List<Indicator> firstIndicators, List<Indicator> secondIndicators) {

        int bound = Math.min(firstIndicators.size(), secondIndicators.size());

        if (bound <= 1) {
            return 1;
        } else {
            return ThreadLocalRandom.current().nextInt(1, bound);
        }
    }

    private List<DescriptionOfStrategy> exchangeDescriptionToCloseADeal(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        DescriptionOfStrategy firstChild = firstParent
                .withDescriptionToCloseADeal(secondParent.getDescriptionToCloseADeal());

        DescriptionOfStrategy secondChild = secondParent
                .withDescriptionToCloseADeal(firstParent.getDescriptionToCloseADeal());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private List<DescriptionOfStrategy> exchangeDescriptionToOpenADeal(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        DescriptionOfStrategy firstChild = firstParent
                .withDescriptionToOpenADeal(secondParent.getDescriptionToOpenADeal());

        DescriptionOfStrategy secondChild = secondParent
                .withDescriptionToOpenADeal(firstParent.getDescriptionToOpenADeal());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private List<DescriptionOfStrategy> exchangeTypeOfDeal(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        UnaryOperator<TypeOfDeal> opposite = typeOfDeal -> typeOfDeal == TypeOfDeal.BUY ?
                TypeOfDeal.SELL :
                TypeOfDeal.BUY;

        DescriptionOfStrategy firstChild = firstParent
                .withTypeOfDeal(opposite.apply(firstParent.getTypeOfDeal()));

        DescriptionOfStrategy secondChild = secondParent
                .withTypeOfDeal(opposite.apply(secondParent.getTypeOfDeal()));

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private List<DescriptionOfStrategy> exchangeTakeProfit(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        DescriptionOfStrategy firstChild = firstParent
                .withTakeProfitType(secondParent.getTakeProfitType())
                .withTakeProfitConfigurationData(secondParent.getTakeProfitConfigurationData());

        DescriptionOfStrategy secondChild = secondParent
                .withTakeProfitType(firstParent.getTakeProfitType())
                .withTakeProfitConfigurationData(firstParent.getTakeProfitConfigurationData());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private List<DescriptionOfStrategy> exchangeTrailingStop(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        DescriptionOfStrategy firstChild = firstParent
                .withTrailingStopType(secondParent.getTrailingStopType())
                .withTrailingStopConfigurationData(secondParent.getTrailingStopConfigurationData());

        DescriptionOfStrategy secondChild = secondParent
                .withTrailingStopType(firstParent.getTrailingStopType())
                .withTrailingStopConfigurationData(firstParent.getTrailingStopConfigurationData());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private List<DescriptionOfStrategy> exchangeStopLoss(Set<DescriptionOfStrategy> dataOfStrategies) {
        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        DescriptionOfStrategy firstChild = firstParent
                .withStopLossType(secondParent.getStopLossType())
                .withStopLossConfigurationData(secondParent.getStopLossConfigurationData());

        DescriptionOfStrategy secondChild = secondParent
                .withStopLossType(firstParent.getStopLossType())
                .withStopLossConfigurationData(firstParent.getStopLossConfigurationData());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    private List<DescriptionOfStrategy> exchangeSymOfDealType(Set<DescriptionOfStrategy> dataOfStrategies) {

        List<DescriptionOfStrategy> descriptionOfStrategyElements = dataOfStrategies.stream()
                .toList();

        DescriptionOfStrategy firstParent = descriptionOfStrategyElements.get(0);
        DescriptionOfStrategy secondParent = descriptionOfStrategyElements.get(1);

        DescriptionOfStrategy firstChild = firstParent
                .withSumOfDealType(secondParent.getSumOfDealType())
                .withSumOfDealConfigurationData(secondParent.getSumOfDealConfigurationData());

        DescriptionOfStrategy secondChild = secondParent
                .withSumOfDealType(firstParent.getSumOfDealType())
                .withSumOfDealConfigurationData(firstParent.getSumOfDealConfigurationData());

        return List.of(firstParent, secondParent, firstChild, secondChild);
    }

    @Override
    public List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> population) {
        return Sets.combinations(Sets.newHashSet(population), 2)
                .stream()
                .flatMap(dataOfStrategies -> functions.stream()
                        .flatMap(setListFunction -> setListFunction.apply(dataOfStrategies)
                                .stream()))
                .toList();
    }


}
