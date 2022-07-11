package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.stopLoss;

import com.finance.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyDescriptionParameters.StopLossType;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.storage.ConfigurationStorage;
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
public class StopLossMutation implements Mutation {

    StopLossRandomGenerator stopLossRandomGenerator;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        StopLossType randomStopLossType = StopLossType.getRandomStopLossType();

        ConfigurationStorage<StopLossConfigurationKey> randomParamsForStopLoss = stopLossRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(randomStopLossType)
                .get();


        SpecificationOfStrategy childSpecificationOfStrategy = parentSpecificationOfStrategy
                .withStopLossType(randomStopLossType)
                .withStopLossConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentSpecificationOfStrategy, childSpecificationOfStrategy);
    }
}
