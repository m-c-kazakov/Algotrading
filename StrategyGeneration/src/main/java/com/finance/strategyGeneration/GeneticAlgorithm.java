package com.finance.strategyGeneration;

import com.finance.strategyGeneration.dataHolder.DataOfStrategy;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.RandomPopulationCreationManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GeneticAlgorithm {

    static int NUMBER_OF_RANDOM_INDIVIDUALS = 100; // TODO вынести в property
    static int NUMBER_OF_THE_BEST_INDIVIDUALS = 100; // TODO вынести в property
    RandomPopulationCreationManager randomPopulationCreationManager;
    PopulationService populationService;


    PopulationCrossingManager populationCrossingManager;

    public void execute() {


        // Создать популяцию
        List<DataOfStrategy> randomPopulation = createRandomPopulation(NUMBER_OF_RANDOM_INDIVIDUALS);
        List<DataOfStrategy> theBestIndividual = populationService.findTheBestIndividual(NUMBER_OF_THE_BEST_INDIVIDUALS);
        List<DataOfStrategy> population = Stream.of(randomPopulation, theBestIndividual).flatMap(List::stream).toList();

        // Скрещивание
        List<DataOfStrategy> populationAfterCrossing = populationCrossingManager.execute(population);

        // Мутация

        // Эволюция
    }

    private List<DataOfStrategy> createRandomPopulation(int numberOfIndividuals) {
        return Stream.iterate(0, integer -> integer < numberOfIndividuals, integer -> integer+1)
                .map(integer -> randomPopulationCreationManager.execute())
                .toList();
    }
}
