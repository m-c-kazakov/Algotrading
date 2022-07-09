package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.stopLoss;

import com.finance.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyGeneration.model.ConfigurationStorage;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.StopLossRandomGenerator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StopLossConfigurationDataMutation implements Mutation {

    StopLossRandomGenerator stopLossRandomGenerator;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        ConfigurationStorage<StopLossConfigurationKey> randomParamsForStopLoss = stopLossRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(parentSpecificationOfStrategy.getStopLossType())
                .get();

        SpecificationOfStrategy childSpecificationOfStrategy = parentSpecificationOfStrategy
                .withStopLossConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentSpecificationOfStrategy, childSpecificationOfStrategy);
    }
}
