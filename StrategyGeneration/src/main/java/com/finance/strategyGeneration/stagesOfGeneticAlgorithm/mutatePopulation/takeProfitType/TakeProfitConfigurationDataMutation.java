package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.takeProfitType;

import com.finance.strategyDescriptionParameters.TakeProfitConfigurationKey;
import com.finance.strategyDescriptionParameters.TakeProfitType;
import com.finance.strategyGeneration.model.ConfigurationStorage;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.TakeProfitRandomGenerator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TakeProfitConfigurationDataMutation implements Mutation {

    TakeProfitRandomGenerator takeProfitRandomGenerator;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        TakeProfitType randomTakeProfitType = TakeProfitType.getRandomTakeProfitType();

        ConfigurationStorage<TakeProfitConfigurationKey> randomParamsForTakeProfit = takeProfitRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(randomTakeProfitType)
                .get();

        SpecificationOfStrategy childSpecificationOfStrategy = parentSpecificationOfStrategy
                .withTakeProfitType(randomTakeProfitType)
                .withTakeProfitConfigurationData(randomParamsForTakeProfit);

        return Stream.of(parentSpecificationOfStrategy, childSpecificationOfStrategy);
    }
}
