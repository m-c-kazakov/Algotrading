package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers.ExchangeManager;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Методы скрещивания: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationCrossingManagerImpl implements PopulationCrossingManager {

    List<ExchangeManager> exchangeManagers;

    @Override
    public List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> population) {
        return Sets.combinations(Sets.newHashSet(population), 2)
                .stream()
                .flatMap(twoDescriptionOfStrategy -> exchangeManagers.stream()
                        .flatMap(exchangeManager -> exchangeManager.execute(twoDescriptionOfStrategy)))
                .distinct()
                .toList();
    }


}
