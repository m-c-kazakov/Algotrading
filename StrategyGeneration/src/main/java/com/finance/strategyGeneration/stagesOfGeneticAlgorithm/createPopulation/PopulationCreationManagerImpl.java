package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation;

import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.service.SpecificationOfStrategyService;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.RandomPopulationCreationManager;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.selectionPopulation.PopulationSelection;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PopulationCreationManagerImpl implements PopulationCreationManager {
    RandomPopulationCreationManager randomPopulationCreationManager;
    SpecificationOfStrategyService specificationOfStrategyService;
    @NonFinal
    @Value("${app.populationCreation.numberOfRandomIndividual}")
    Integer NUMBER_OF_RANDOM_INDIVIDUALS;
    @NonFinal
    @Value("${app.populationCreation.numberOfTheBestIndividual}")
    Integer NUMBER_OF_THE_BEST_INDIVIDUALS;

    PopulationSelection populationSelection;

    @Override
    public List<SpecificationOfStrategy> execute() {
        try {
            log.info("START PopulationCreationManager");

            List<SpecificationOfStrategy> randomPopulation = createRandomPopulation(NUMBER_OF_RANDOM_INDIVIDUALS);
            List<SpecificationOfStrategy> theBestIndividual = specificationOfStrategyService.findTheBestIndividual(
                    NUMBER_OF_THE_BEST_INDIVIDUALS);
            List<SpecificationOfStrategy> maximumScoreStrategy = specificationOfStrategyService.findMaximumScoreStrategy(
                    NUMBER_OF_THE_BEST_INDIVIDUALS);

            List<SpecificationOfStrategy> specificationOfStrategies = Stream.of(randomPopulation, theBestIndividual, maximumScoreStrategy)
                    .flatMap(List::stream)
                    .toList();

            log.info(
                    "PopulationCreationManager NUMBER_OF_RANDOM_INDIVIDUALS={}; NUMBER_OF_THE_BEST_INDIVIDUALS={}; theBestIndividual={}; Actual={}",
                    NUMBER_OF_RANDOM_INDIVIDUALS, NUMBER_OF_THE_BEST_INDIVIDUALS, theBestIndividual.size(),
                    specificationOfStrategies.size());
            Assert.state(
                    specificationOfStrategies.size() <= NUMBER_OF_RANDOM_INDIVIDUALS + NUMBER_OF_THE_BEST_INDIVIDUALS,
                    "Количество созданных стратегий не соответствует ожидаемому значению");
            log.info("END PopulationCreationManager populationAfterCreation.size={}", specificationOfStrategies.size());
            return populationSelection.execute(specificationOfStrategies);
        } catch (Exception exception) {
            log.error("ERROR PopulationCreationManager={}", exception.getMessage(), exception);
            throw exception;
        }
    }

    private List<SpecificationOfStrategy> createRandomPopulation(int numberOfIndividuals) {
        return Stream.iterate(0, integer -> integer < numberOfIndividuals, integer -> integer + 1)
                .map(integer -> randomPopulationCreationManager.execute())
                .toList();
    }
}
