package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.trailingStopType;

import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
import com.finance.strategyDescriptionParameters.TrailingStopType;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.storage.ConfigurationStorage;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.TrailingStopRandomGenerator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TrailingStopTypeMutation implements Mutation {

    TrailingStopRandomGenerator trailingStopRandomGenerator;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        TrailingStopType randomTrailingStopType = TrailingStopType.getRandomTrailingStopType();


        ConfigurationStorage<TrailingStopConfigurationKey> randomParamsForTrailingStop = trailingStopRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(randomTrailingStopType).get();

        SpecificationOfStrategy childSpecificationOfStrategy = parentSpecificationOfStrategy.withTrailingStopType(
                randomTrailingStopType).withTrailingStopConfigurationData(randomParamsForTrailingStop);

        return Stream.of(parentSpecificationOfStrategy, childSpecificationOfStrategy);
    }
}
