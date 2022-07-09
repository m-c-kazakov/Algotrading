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
    public List<DescriptionOfStrategy> execute() {
        return List.of();
        // TODO

//        return Stream.of(populationCreationManager.execute())
//                .peek(descriptionOfStrategies -> {
//                    descriptionOfStrategies.stream().forEach(descriptionOfStrategy -> {
//                        if (descriptionOfStrategy.getStopLossConfigurationData().isEmpty()) {
//                            throw new RuntimeException("Постая мапа конфигурации");
//                        }
//                    });
//                })
//                .map(populationCrossingManager::execute)
//                .peek(descriptionOfStrategies -> {
//                    descriptionOfStrategies.stream().forEach(descriptionOfStrategy -> {
//                        if (descriptionOfStrategy.getStopLossConfigurationData().isEmpty()) {
//                            throw new RuntimeException("Постая мапа конфигурации");
//                        }
//                    });
//                })
//                .map(mutationOfIndividual::execute)
//                .peek(descriptionOfStrategies -> {
//                    descriptionOfStrategies.stream().forEach(descriptionOfStrategy -> {
//                        if (descriptionOfStrategy.getStopLossConfigurationData().isEmpty()) {
//                            throw new RuntimeException("Постая мапа конфигурации");
//                        }
//                    });
//                })
//                // Эволюция
//                // TODO Добавить блок с эволюцией
//                .map(populationSelection::execute)
//                .peek(descriptionOfStrategies -> {
//                    descriptionOfStrategies.stream().forEach(descriptionOfStrategy -> {
//                        if (descriptionOfStrategy.getStopLossConfigurationData().isEmpty()) {
//                            throw new RuntimeException("Постая мапа конфигурации");
//                        }
//                    });
//                })
//                .peek(specificationOfStrategyService::saveAll)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Не созданы стратегии для проверки"));

    }


}
