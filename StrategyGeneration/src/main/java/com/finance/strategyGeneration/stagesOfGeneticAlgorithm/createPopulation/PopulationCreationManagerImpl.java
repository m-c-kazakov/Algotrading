package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.SpecificationOfStrategyService;
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
    SpecificationOfStrategyService specificationOfStrategyService;

    @Override
    public List<SpecificationOfStrategy> execute() {

        List<SpecificationOfStrategy> randomPopulation = createRandomPopulation(NUMBER_OF_RANDOM_INDIVIDUALS);
        List<SpecificationOfStrategy> theBestIndividual = specificationOfStrategyService.findTheBestIndividual(
                NUMBER_OF_THE_BEST_INDIVIDUALS);

        return Stream.of(randomPopulation, theBestIndividual)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    private List<SpecificationOfStrategy> createRandomPopulation(int numberOfIndividuals) {
        return Stream.iterate(0, integer -> integer < numberOfIndividuals, integer -> integer + 1)
                .map(integer -> randomPopulationCreationManager.execute())
                .toList();
    }
}
