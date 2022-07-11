package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.trailingStopType;

import com.finance.strategyDescriptionParameters.TrailingStopConfigurationKey;
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
public class TrailingStopConfigurationDataMutation implements Mutation {

    TrailingStopRandomGenerator trailingStopRandomGenerator;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {


        ConfigurationStorage<TrailingStopConfigurationKey> randomParamsForStopLoss = trailingStopRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(parentSpecificationOfStrategy.getTrailingStopType())
                .get();

        SpecificationOfStrategy childSpecificationOfStrategy = parentSpecificationOfStrategy.withTrailingStopConfigurationData(
                randomParamsForStopLoss);

        return Stream.of(parentSpecificationOfStrategy, childSpecificationOfStrategy);
    }
}
