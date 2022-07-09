package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.sumOfDeal;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.SumOfDealConfigurationKey;
import com.finance.strategyDescriptionParameters.SumOfDealType;
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
public class SumOfDealTypeMutation implements Mutation {

    SumOfDealRandomGenerator sumOfDealRandomGenerator;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        SumOfDealType randomSumOfDealType = SumOfDealType.getRandomSumOfDealType();

        Map<SumOfDealConfigurationKey, Object> randomParamsForSumOfDeal = sumOfDealRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(randomSumOfDealType)
                .get();

        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withSumOfDealType(randomSumOfDealType)
                .withSumOfDealConfigurationData(randomParamsForSumOfDeal);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
