package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation;

import com.finance.strategyDescriptionParameters.TypeOfDeal;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.creator.SpecificationOfStrategyCreator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.RandomStrategyParams;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RandomPopulationCreationManagerImpl implements RandomPopulationCreationManager {

    static long startScore = 10000; // TODO Вынести в Property
    static int acceptableRisk = 50; // TODO Вынести в Property

    List<RandomStrategyParams> randomStrategyParams;

    @Override
    public SpecificationOfStrategy execute() {

        SpecificationOfStrategy.SpecificationOfStrategyBuilder dataOfStrategyBuilder = SpecificationOfStrategy.builder()
                .startScore(startScore)
                .acceptableRisk(acceptableRisk)
                .typeOfDeal(TypeOfDeal.getRandomTypeOfDeal());

        randomStrategyParams.forEach(randomStrategyParam -> randomStrategyParam.add(dataOfStrategyBuilder));
        SpecificationOfStrategy specificationOfStrategy = dataOfStrategyBuilder.build();
        return SpecificationOfStrategyCreator.createWithHashCodeAndDataOfCreation(specificationOfStrategy);
    }
}
