package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyGeneration.service.PopulationService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationCreationManagerImpl implements PopulationCreationManager {

    static int NUMBER_OF_RANDOM_INDIVIDUALS = 10; // TODO вынести в property
    static int NUMBER_OF_THE_BEST_INDIVIDUALS = 10; // TODO вынести в property

    RandomPopulationCreationManager randomPopulationCreationManager;
    PopulationService populationService;

    @Override
    public List<DescriptionOfStrategy> execute() {

        List<DescriptionOfStrategy> randomPopulation = createRandomPopulation(NUMBER_OF_RANDOM_INDIVIDUALS);
        List<DescriptionOfStrategy> theBestIndividual = populationService.findTheBestIndividual(
                NUMBER_OF_THE_BEST_INDIVIDUALS);

        return Stream.of(randomPopulation, theBestIndividual)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    private List<DescriptionOfStrategy> createRandomPopulation(int numberOfIndividuals) {
        return Stream.iterate(0, integer -> integer < numberOfIndividuals, integer -> integer + 1)
                .map(integer -> randomPopulationCreationManager.execute())
                .toList();
    }
}
