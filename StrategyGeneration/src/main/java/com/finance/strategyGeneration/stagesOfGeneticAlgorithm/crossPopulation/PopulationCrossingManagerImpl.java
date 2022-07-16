package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers.ExchangeManager;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Методы скрещивания: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationCrossingManagerImpl implements PopulationCrossingManager {

    List<ExchangeManager> exchangeManagers;

    @Override
    public List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> population) {
        try {
            log.info("START PopulationCrossingManager populationBeforeCrossing.size={}", population.size());
            List<SpecificationOfStrategy> specificationOfStrategies = Sets.combinations(Sets.newHashSet(population), 2)
                    .stream()
                    .flatMap(twoSpecificationOfStrategy -> exchangeManagers.stream()
                            .flatMap(exchangeManager -> exchangeManager.execute(twoSpecificationOfStrategy)))
                    .distinct()
                    .limit(population.size()* 10L)
                    .toList();
            log.info("END PopulationCrossingManager populationAfterCrossing.size={}", specificationOfStrategies.size());
            return specificationOfStrategies;
        } catch (Exception exception) {
            log.error("ERROR PopulationCrossingManager={}", exception.getMessage(), exception);
            throw exception;
        }
    }


}
