package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation;

import com.finance.dataHolder.DescriptionOfStrategy;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Методы мутации: https://proproprogs.ru/ga/ga-obzor-metodov-otbora-skreshchivaniya-i-mutacii
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MutationOfIndividualImpl implements MutationOfIndividual {

    List<Mutation> mutations;

    @Override
    public List<DescriptionOfStrategy> execute(List<DescriptionOfStrategy> populationAfterCrossing) {

        // TODO при создании стратегий с descriptionToCloseADeal - добавить мутацию
        return populationAfterCrossing.stream()
                .flatMap(dataOfStrategy -> mutations.stream()
                        .flatMap(mutation -> mutation.execute(dataOfStrategy)))
                .toList();
    }
}
