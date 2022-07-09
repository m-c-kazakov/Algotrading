package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.trailingStopType;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.TrailingStopRandomGenerator;
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
public class TrailingStopConfigurationDataMutation implements Mutation {

    TrailingStopRandomGenerator trailingStopRandomGenerator;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        Map<TrailingStopConfigurationKey, Object> randomParamsForStopLoss = trailingStopRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(parentDescriptionOfStrategy.getTrailingStopType())
                .get();

        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withTrailingStopConfigurationData(randomParamsForStopLoss);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
