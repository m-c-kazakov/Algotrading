package com.finance.strategyGeneration;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationCrossingManagerImpl implements PopulationCrossingManager {


    static List<Function<Set<DataOfStrategy>, List<DataOfStrategy>>> functions = new ArrayList<>();

    static {
        functions.add(PopulationCrossingManagerImpl::exchangeSymOfDealType);
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

    private static List<DataOfStrategy> exchangeSymOfDealType(Set<DataOfStrategy> dataOfStrategies) {

        List<DataOfStrategy> dataOfStrategyElements = dataOfStrategies.stream().toList();

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
