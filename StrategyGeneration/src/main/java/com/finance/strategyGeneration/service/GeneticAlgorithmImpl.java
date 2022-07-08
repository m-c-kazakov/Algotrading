package com.finance.strategyGeneration.service;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.crossing.PopulationCrossingManager;
import com.finance.strategyGeneration.mutation.MutationOfIndividual;
import com.finance.strategyGeneration.populationSelection.PopulationSelection;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.RandomPopulationCreationManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneticAlgorithmImpl implements GeneticAlgorithm {

    static int NUMBER_OF_RANDOM_INDIVIDUALS = 10; // TODO вынести в property
    static int NUMBER_OF_THE_BEST_INDIVIDUALS = 10; // TODO вынести в property

    RandomPopulationCreationManager randomPopulationCreationManager;
    PopulationService populationService;

    PopulationCrossingManager populationCrossingManager;

    MutationOfIndividual mutationOfIndividual;

    PopulationSelection populationSelection;

    SpecificationOfStrategyService specificationOfStrategyService;


    @Override
    public Set<DescriptionOfStrategy> execute() {

        // Создать популяцию
        List<DescriptionOfStrategy> randomPopulation = createRandomPopulation(NUMBER_OF_RANDOM_INDIVIDUALS);
        List<DescriptionOfStrategy> theBestIndividual = populationService.findTheBestIndividual(
                NUMBER_OF_THE_BEST_INDIVIDUALS);
        List<DescriptionOfStrategy> population = Stream.of(randomPopulation, theBestIndividual)
                .flatMap(List::stream)
                .toList();


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

    private List<DescriptionOfStrategy> createRandomPopulation(int numberOfIndividuals) {
        return Stream.iterate(0, integer -> integer < numberOfIndividuals, integer -> integer + 1)
                .map(integer -> randomPopulationCreationManager.execute())
                .toList();
    }
}
