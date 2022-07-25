package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.ManagerOfSendingForVerification;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.exchangeManagers.ExchangeManager;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation.PopulationSelection;
import com.google.common.collect.Sets;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Методы скрещивания: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationCrossingManagerImpl implements PopulationCrossingManager {

    List<ExchangeManager> exchangeManagers;
    ManagerOfSendingForVerification managerOfSendingForVerification;
    PopulationSelection populationSelection;
    Executor executor;


    public List<SpecificationOfStrategy> execute(Set<SpecificationOfStrategy> specificationOfStrategies) {
        try {

            List<SpecificationOfStrategy> pairOfValue = new ArrayList<>(specificationOfStrategies);
            Assert.state(pairOfValue.size() >= 2, "В наборе должно содержаться >= 2 элемента");
            List<SpecificationOfStrategy> pairCrossingResult = exchangeManagers
                    .stream()
                    .flatMap(exchangeManager -> exchangeManager.execute(pairOfValue.get(0), pairOfValue.get(1)))
                    .toList();
            log.debug("<< PairOfValueCrossingResult.size={}", pairCrossingResult.size());
            return populationSelection.execute(pairCrossingResult);

        } catch (Exception exception) {
            log.error("ERROR PopulationCrossingManager errorMessage={}", exception.getMessage(), exception);
            return List.of();
        }
    }

    @Override
    public void execute(List<SpecificationOfStrategy> population) {
        try {
            log.info(">> START PopulationCrossingManager populationBeforeCrossing.size={}", population.size());
            Set<String> uniqueIdentifiers = new HashSet<>();
            List<SpecificationOfStrategy> specificationOfStrategies = Sets.combinations(Sets.newHashSet(population), 2)
                    .stream()
                    .map(pairOfValue -> CompletableFuture.supplyAsync(() -> this.execute(pairOfValue), executor))
                    .map(this::getCompletableFutureResult)
                    .flatMap(List::stream)
                    .filter(strategy -> uniqueIdentifiers.add(strategy.getHashCode()))
                    .toList();

            managerOfSendingForVerification.execute(specificationOfStrategies);
            log.info("<< END PopulationCrossingManager populationAfterCrossing.size={}",
                    specificationOfStrategies.size());
        } catch (Exception exception) {
            log.error("ERROR PopulationCrossingManager={}", exception.getMessage(), exception);
            throw exception;
        }
    }

    @SneakyThrows
    private List<SpecificationOfStrategy> getCompletableFutureResult(CompletableFuture<List<SpecificationOfStrategy>> completableFuture) {
        return completableFuture.get();
    }


}
