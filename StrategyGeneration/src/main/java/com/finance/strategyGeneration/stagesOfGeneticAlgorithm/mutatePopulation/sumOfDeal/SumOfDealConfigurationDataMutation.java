package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.sumOfDeal;

import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
import com.finance.strategyGeneration.model.SpecificationOfStrategy;
import com.finance.strategyGeneration.model.storage.ConfigurationStorage;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.SumOfDealRandomGenerator;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.Mutation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SumOfDealConfigurationDataMutation implements Mutation {

    SumOfDealRandomGenerator sumOfDealRandomGenerator;

    @Override
    public Stream<SpecificationOfStrategy> execute(SpecificationOfStrategy parentSpecificationOfStrategy) {

        ConfigurationStorage<SumOfDealConfigurationKey> randomParamsForSumOfDeal = sumOfDealRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(parentSpecificationOfStrategy.getSumOfDealType())
                .get();

        SpecificationOfStrategy childSpecificationOfStrategy = parentSpecificationOfStrategy
                .withSumOfDealConfigurationData(randomParamsForSumOfDeal);

        return Stream.of(parentSpecificationOfStrategy, childSpecificationOfStrategy);
    }
}
