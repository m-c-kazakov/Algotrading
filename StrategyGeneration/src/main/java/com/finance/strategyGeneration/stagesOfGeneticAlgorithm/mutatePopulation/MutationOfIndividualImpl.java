package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Методы мутации: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MutationOfIndividualImpl implements MutationOfIndividual {

    List<Mutation> mutations;

    @Override
    public List<SpecificationOfStrategy> execute(List<SpecificationOfStrategy> populationAfterCrossing) {

        try {
            log.info("START MutationOfIndividual populationBeforeMutation.size={}", populationAfterCrossing.size());
            // TODO при создании стратегий с descriptionToCloseADeal - добавить мутацию
            List<SpecificationOfStrategy> specificationOfStrategies = populationAfterCrossing.stream()
                    .flatMap(dataOfStrategy -> mutations.stream()
                            .flatMap(mutation -> mutation.execute(dataOfStrategy)))
                    .distinct()
                    .toList();
            log.info("END MutationOfIndividual populationAfterMutation.size={}", specificationOfStrategies.size());
            return specificationOfStrategies;
        } catch (Exception exception) {
            log.error("ERROR MutationOfIndividual={}", exception.getMessage(), exception);
            throw exception;
        }
    }
}
