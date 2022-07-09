package com.finance.strategyGeneration.stagesOfGeneticAlgorithm.mutatePopulation.takeProfitType;

import com.finance.dataHolder.DescriptionOfStrategy;
import com.finance.strategyDescriptionParameters.TakeProfitConfigurationKey;
import com.finance.strategyDescriptionParameters.TakeProfitType;
import com.finance.strategyGeneration.stagesOfGeneticAlgorithm.createPopulation.randomPopulation.strategiesForCreatingRandomPopulations.TakeProfitRandomGenerator;
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
public class TakeProfitConfigurationDataMutation implements Mutation {

    TakeProfitRandomGenerator takeProfitRandomGenerator;

    @Override
    public Stream<DescriptionOfStrategy> execute(DescriptionOfStrategy parentDescriptionOfStrategy) {

        TakeProfitType randomTakeProfitType = TakeProfitType.getRandomTakeProfitType();

        Map<TakeProfitConfigurationKey, Object> randomParamsForTakeProfit = takeProfitRandomGenerator
                .getMapWithSupplierGeneratedRandomParams()
                .get(randomTakeProfitType)
                .get();

        DescriptionOfStrategy childDescriptionOfStrategy = parentDescriptionOfStrategy
                .withTakeProfitType(randomTakeProfitType)
                .withTakeProfitConfigurationData(randomParamsForTakeProfit);

        return Stream.of(parentDescriptionOfStrategy, childDescriptionOfStrategy);
    }
}
