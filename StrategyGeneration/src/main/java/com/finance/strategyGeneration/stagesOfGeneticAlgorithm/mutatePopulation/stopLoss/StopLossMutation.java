package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.stopLoss;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.StopLossConfigurationKey;
import com.finance.strategyDescriptionParameters.StopLossType;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.StopLossRandomGenerator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StopLossMutation implements Mutation {

    StopLossRandomGenerator stopLossRandomGenerator;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        StopLossType randomStopLossType = StopLossType.getRandomStopLossType();
        Map<StopLossConfigurationKey, Object> randomParamsForStopLoss = stopLossRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(randomStopLossType)
                .get();


        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withStopLossType(randomStopLossType)
                .withStopLossConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
