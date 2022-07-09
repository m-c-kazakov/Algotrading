package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.sumOfDeal;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.SumOfDealRandomGenerator;
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
public class SumOfDealConfigurationDataMutation implements Mutation {

    SumOfDealRandomGenerator sumOfDealRandomGenerator;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        Map<SumOfDealConfigurationKey, Object> randomParamsForSumOfDeal = sumOfDealRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(parentDescriptionOfStrategy.getSumOfDealType())
                .get();

        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withSumOfDealConfigurationData(randomParamsForSumOfDeal);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
