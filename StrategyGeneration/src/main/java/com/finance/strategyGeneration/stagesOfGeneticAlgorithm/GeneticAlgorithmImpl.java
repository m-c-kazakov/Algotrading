package com.finance.strategyGeneration.stagesOfGeneticAlgorithm;

import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.PopulationCreationManager;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.PopulationCrossingManager;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.MutationOfIndividual;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Slf4j
@Component
@Value
public class GeneticAlgorithmImpl implements GeneticAlgorithm {

    PopulationCreationManager populationCreationManager;
    PopulationCrossingManager populationCrossingManager;
    MutationOfIndividual mutationOfIndividual;

    @Override
    public void execute() {
        try {
            log.info("START: Запуск генетического алгоритма.");
            Stream.of(populationCreationManager.execute())
                    .peek(populationCrossingManager::execute)
                    .forEach(mutationOfIndividual::execute);
            // TODO Добавить блок с эволюцией


            log.info("END: Завершение генетического алгоритма.");
        } catch (Exception exception) {
            log.error("Ошибка при создании стратегий.", exception);
        }

    }


}
