package com.finance.strategyGeneration.stagesOfGeneticAlgorithm;

import com.finance.dataHolder.DescriptionOfStrategy;
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
import java.util.Set;

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
    public Set<DescriptionOfStrategy> execute() {

        List<DescriptionOfStrategy> population = populationCreationManager.execute();


        // Скрещивание
        List<DescriptionOfStrategy> populationAfterCrossing = populationCrossingManager.execute(population);

        // Мутация
        List<DescriptionOfStrategy> populationAfterMutation = mutationOfIndividual.execute(populationAfterCrossing);

        // Эволюция
        // TODO Добавить блок с эволюцией

        // Отбор
        Set<DescriptionOfStrategy> populationAfterSelection = populationSelection.execute(populationAfterMutation);

        specificationOfStrategyService.saveAll(populationAfterSelection);


        return populationAfterSelection;

    }


}
