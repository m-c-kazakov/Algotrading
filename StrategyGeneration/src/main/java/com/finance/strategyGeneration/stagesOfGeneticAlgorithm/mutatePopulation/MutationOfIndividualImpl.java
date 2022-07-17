package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.ManagerOfSendingForVerification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Stream;

/**
 * Методы мутации: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MutationOfIndividualImpl implements MutationOfIndividual {

    ManagerOfSendingForVerification managerOfSendingForVerification;
    List<Mutation> mutations;
    Executor executor;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy specificationOfStrategy) {

        try {
            Set<String> uniqueIdentifiers = new HashSet<>();
            log.debug("START strategy mutation.size={}", 1);
            // TODO при создании стратегий с descriptionToCloseADeal - добавить мутацию
            List<SpecificationOfStrategy> specificationOfStrategies = mutations.stream()
                    .flatMap(mutation -> mutation.execute(specificationOfStrategy))
                    .filter(strategy -> uniqueIdentifiers.add(strategy.getHashCode()))
                    .toList();


            log.debug("END strategy mutation.size={}", specificationOfStrategies.size());
            return specificationOfStrategies.stream();
        } catch (Exception exception) {
            log.error("ERROR MutationOfIndividual={}", exception.getMessage(), exception);
            return Stream.empty();
        }
    }

    @Override
    public void execute(List<SpecificationOfStrategy> populationAfterCrossing) {

        try {
            log.info("START MutationOfIndividual populationBeforeMutation.size={}", populationAfterCrossing.size());
            Set<String> uniqueIdentifiers = new HashSet<>();
            // TODO при создании стратегий с descriptionToCloseADeal - добавить мутацию
            List<SpecificationOfStrategy> specificationOfStrategies = populationAfterCrossing.stream()
                    .map(specificationOfStrategy -> CompletableFuture.supplyAsync(() -> this.execute(specificationOfStrategy), executor))
                    .flatMap(this::getCompletableFutureResult)
                    .filter(strategy -> uniqueIdentifiers.add(strategy.getHashCode()))
                    .toList();

            managerOfSendingForVerification.execute(specificationOfStrategies);
            log.info("END MutationOfIndividual populationAfterMutation.size={}", specificationOfStrategies.size());
        } catch (Exception exception) {
            log.error("ERROR MutationOfIndividual={}", exception.getMessage(), exception);
            throw exception;
        }
    }

    @SneakyThrows
    private Stream<SpecificationOfStrategy> getCompletableFutureResult(CompletableFuture<Stream<SpecificationOfStrategy>> completableFuture) {
        return completableFuture.get();
    }
}
