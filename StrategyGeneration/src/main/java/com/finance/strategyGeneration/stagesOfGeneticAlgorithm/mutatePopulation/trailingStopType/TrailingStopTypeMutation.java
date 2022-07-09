package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.trailingStopType;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyDescriptionParameters.TrailingStopType;
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
public class TrailingStopTypeMutation implements Mutation {

    TrailingStopRandomGenerator trailingStopRandomGenerator;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        TrailingStopType randomTrailingStopType = TrailingStopType.getRandomTrailingStopType();

        Map<TrailingStopConfigurationKey, Object> randomParamsForTrailingStop = trailingStopRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(randomTrailingStopType)
                .get();

        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withTrailingStopType(randomTrailingStopType)
                .withTrailingStopConfigurationData(randomParamsForTrailingStop);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
