package com.finance.strategyGeneration.stagesOfGeneticAlgorithm;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.SpecificationOfStrategyService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.PopulationCreationManager;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.crossPopulation.PopulationCrossingManager;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.MutationOfIndividual;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation.PopulationSelection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneticAlgorithmImpl implements GeneticAlgorithm {

    PopulationCreationManager populationCreationManager;

    PopulationCrossingManager populationCrossingManager;

    MutationOfIndividual mutationOfIndividual;

    PopulationSelection populationSelection;

    SpecificationOfStrategyService specificationOfStrategyService;


    @Override
    public List<SpecificationOfStrategy> execute() {

        return Stream.of(populationCreationManager.execute())
                .map(populationCrossingManager::execute)
                .map(mutationOfIndividual::execute)
                // Эволюция
                // TODO Добавить блок с эволюцией
                .map(populationSelection::execute)
                .map(specificationOfStrategyService::saveAll)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Не созданы стратегии для проверки"));

    }


}
